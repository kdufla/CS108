import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class WebFrame extends JFrame {

    private Panel panel;
    private JButton single;
    private JButton concurrent;
    private JTextField field;
    private JLabel running;
    private JLabel completed;
    private JLabel elapsed;
    private JProgressBar bar;
    private JButton stop;
    private String file;
    private JTable table;
    private DefaultTableModel model;
    private Launcher l;
    private long startTime;

    public WebFrame ( String file ) {
        super ( "WebLoader" );
        this.file = file;

        decompOne ( );
        decompTwo ( );
        listen ( );

        add ( panel );
        setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
        pack ( );
        setVisible ( true );
    }

    private void decompOne ( ) {
        panel = new Panel ( );
        panel.setLayout ( new BoxLayout ( panel, BoxLayout.Y_AXIS ) );

        model = new DefaultTableModel ( new String[] { "url" , "status" }, 0 );
        getURL ( );
        table = new JTable ( model );
        table.setAutoResizeMode ( JTable.AUTO_RESIZE_ALL_COLUMNS );
        JScrollPane scrollPane = new JScrollPane ( table );
        scrollPane.setPreferredSize ( new Dimension ( 600, 300 ) );
        panel.add ( scrollPane );

        single = new JButton ( "Single Thread Fetch" );
        panel.add ( single );
    }

    private void decompTwo ( ) {
        concurrent = new JButton ( "Concurrent Fetch" );
        panel.add ( concurrent );

        field = new JTextField ( "4" );
        panel.add ( field );

        running = new JLabel ( "Running:0" );
        panel.add ( running );

        completed = new JLabel ( "Completed:0" );
        panel.add ( completed );

        elapsed = new JLabel ( "Elapsed:" );
        panel.add ( elapsed );

        bar = new JProgressBar ( 0, model.getRowCount ( ) );
        bar.setValue ( 0 );
        bar.setStringPainted ( true );
        panel.add ( bar );

        stop = new JButton ( "Stop" );
        stop.setEnabled ( false );
        panel.add ( stop );

    }

    // add listeners to buttons
    private void listen ( ) {
        stop.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent actionEvent ) {
                stateToNotRunning ( );
                l.interuptAll ( );
                l.interrupt ( );
            }
        } );

        concurrent.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent actionEvent ) {
                try{
                    launch ( Integer.parseInt ( field.getText ( ) ) );
                }catch ( NumberFormatException e){
                    launch ( 1 );
                }
            }
        } );

        single.addActionListener ( new ActionListener ( ) {
            @Override
            public void actionPerformed ( ActionEvent actionEvent ) {
                launch ( 1 );
            }
        } );
    }

    // launch Launcher class
    private void launch ( int maxThreads ) {
        startTime = System.currentTimeMillis ( );
        stateToRunning ( );
        l = new Launcher ( maxThreads );
        l.start ( );
    }

    public class Launcher extends Thread {
        private WebWorker[] workers;
        private Semaphore maxThreads;
        private AtomicInteger runningTh;
        private AtomicInteger completeTh;

        public Launcher ( int threads ) {
            if(threads == 0) threads=1;
            maxThreads = new Semaphore ( threads );
            workers = new WebWorker[ model.getRowCount ( ) ];
            runningTh = new AtomicInteger ( 0 );
            completeTh = new AtomicInteger ( 0 );
        }

        // prepare and run limited amount of worker threads in paralel
        private void runWorkers(){
            try {
                for ( int i = 0; i < model.getRowCount ( ); i++ ) {
                    workers[ i ] = new WebWorker ( ( String ) model.getValueAt ( i, 0 ), i, this );
                    maxThreads.acquire ( );
                    if ( isInterrupted ( ) ) throw new InterruptedException ( );
                    runningTh.incrementAndGet ( );
                    workers[ i ].start ( );

                }
            } catch ( InterruptedException e ) {
                interuptAll ( );
            }
        }

        // wait for worker threads to finish their work
        private void waitWorkers(){
            for ( WebWorker w : workers ) {
                try {
                    w.join ( );
                } catch ( InterruptedException e ) {
                    interuptAll ( );
                } catch ( NullPointerException ignore) {}
            }
        }

        @Override
        public void run ( ) {
            runWorkers ();

            waitWorkers ();

            SwingUtilities.invokeLater ( new Runnable ( ) {
                public void run ( ) {
                    stateToNotRunning ( );
                }
            } );
        }

        public void interuptAll ( ) {
            for ( WebWorker w : workers ) {
                try{
                    w.interrupt ( );
                }catch(NullPointerException ignore){}
            }
        }

        // update GUI with info provided by worker
        public void update ( String resultStatus, int row ) {
            maxThreads.release ( );
            model.setValueAt ( resultStatus, row, 1 );
            final long elapsedT = System.currentTimeMillis ( ) - startTime;
            runningTh.decrementAndGet ( );
            completeTh.incrementAndGet ( );
            SwingUtilities.invokeLater ( new Runnable ( ) {
                public void run ( ) {
                    running.setText ( "Running:" + runningTh );
                    completed.setText ( "Complete:" + completeTh );
                    bar.setValue ( completeTh.get ( ) );
                    elapsed.setText ( "Elapsed:" + elapsedT );
                }
            } );
        }

    }

    // change state to running
    private void stateToRunning ( ) {
        stop.setEnabled ( true );
        single.setEnabled ( false );
        concurrent.setEnabled ( false );
        bar.setMaximum ( model.getRowCount ( ) );
        running.setText ( "Running:0" );
        completed.setText ( "Completed:0" );
        elapsed.setText ( "Elapsed:" );
        for ( int i = 0; i < model.getRowCount ( ); i++ ) {
            model.setValueAt ( "", i, 1 );
        }
    }

    // change state to not running
    private void stateToNotRunning ( ) {
        stop.setEnabled ( false );
        single.setEnabled ( true );
        concurrent.setEnabled ( true );
        bar.setValue ( 0 );
    }

    //http://stackoverflow.com/questions/16265693/how-to-use-buffered-reader-in-java
    private void getURL ( ) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader ( new FileReader ( new File ( file ) ) );

            String line;
            while ( ( line = reader.readLine ( ) ) != null ) {
                Object[] rowData = { line , "" };
                model.addRow ( rowData );
            }
        } catch ( IOException e ) {
            e.printStackTrace ( );
        } finally {
            try {
                if ( reader != null ) reader.close ( );
            } catch ( IOException e ) {
                e.printStackTrace ();
            }
        }


    }

    public static void main ( String[] args ) {
        SwingUtilities.invokeLater ( new Runnable ( ) {
            public void run ( ) {
                new WebFrame ( "/home/kdufla/IdeaProjects/ass4practice/src/links2.txt" );
            }
        } );
    }

}

