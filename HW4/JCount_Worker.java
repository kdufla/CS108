import javax.swing.*;

public class JCount_Worker extends Thread{

    private static final int INTERVAL=10000;

    int input;
    JLabel label;

    public JCount_Worker(String txt, JLabel label) {
        input=Integer.parseInt(txt);
        this.label=label;
    }

    @Override
    public void run() {
        for (int i = 0; i < input; i++) {
            if(isInterrupted())
                break;

            if(i%INTERVAL==0){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }

                final int j=i;

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        label.setText(j+"");
                    }
                });

            }


        }

    }
}
