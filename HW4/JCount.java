import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JCount extends JPanel {

    private static final int HEIGHT=400;
    private static final int WIDTH=100;
    private static final int NUM_JCOUNT = 4;

    private JLabel label;
    private JTextField field;
    private JCount_Worker worker;

    public JCount(){
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        add(Box.createRigidArea(new Dimension(0, 40)));


        field = new JTextField("100000000");
        add(field);

        label = new JLabel("0");
        add(label);

        JButton start = new JButton("start");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(worker==null){
                    worker=new JCount_Worker(field.getText(),label);
                    worker.start();
                }else{
                    worker.interrupt();
                    worker = new JCount_Worker(field.getText(), label);
                    worker.start();
                }
            }
        });
        add(start);

        JButton stop = new JButton("stop");
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(worker!=null){
                    worker.interrupt();
                    worker=null;
                }
            }
        });
        add(stop);

        add(Box.createRigidArea(new Dimension(0,40)));
    }



    private static void createAndShowGUI() {
        JFrame f=new JFrame();

        f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));

        for (int i = 0; i < NUM_JCOUNT; i++) {
            f.add(new JCount(), new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        }

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}