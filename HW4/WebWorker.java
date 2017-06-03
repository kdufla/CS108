import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;
import javax.swing.*;


public class WebWorker extends Thread {

    private String urlString;
    private int row;
    private String resultStatus;
    private Semaphore sem;
    private WebFrame.Launcher launch;

    public WebWorker(String urlString, int row, WebFrame.Launcher launcher) {
        this.urlString=urlString;
        this.row=row;
        launch=launcher;

    }

    @Override
    public void run() {
        connectURL();
        launch.update(resultStatus,row);

    }

    private void connectURL(){

        InputStream input = null;
        StringBuilder contents = null;

        try {
            //System.out.println("1");
            long startTime = System.currentTimeMillis();
            URL url = new URL(this.urlString);
            URLConnection connection = url.openConnection();

            // Set connect() to throw an IOException
            // if connection does not succeed in this many msecs.
            connection.setConnectTimeout(5000);
            connection.connect();
            input = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            char[] array = new char[1000];
            int len;
            int size=0;
            contents = new StringBuilder(1000);
            while ((len = reader.read(array, 0, array.length)) > 0) {
                contents.append(array, 0, len);
                Thread.sleep(100);
                size+=len;
            }
            long endTime = System.currentTimeMillis();
            // Successful download if we get here
            resultStatus = new SimpleDateFormat("HH:mm:ss").format(new Date(startTime))
                    + "  " + (endTime - startTime)
                    + "ms  " + size + " bytes";
        }
        // Otherwise control jumps to a catch...
        catch (MalformedURLException ignored) {
            resultStatus="err";
        } catch (InterruptedException exception) {
            resultStatus="interrupted";
        } catch (IOException ignored) {
            resultStatus="err";
        }
        // "finally" clause, to close the input stream
        // in any case
        finally {
            //resultStatus+=" "+row;
            try {
                if (input != null) input.close();
            } catch (IOException ignored) {
            }
        }

    }
}
