import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class Bank_Bank {


    public static final int Capicity = 30;
    public static final int NumAccs = 20;
    public static final Bank_Transaction poison = new Bank_Transaction(0, 0, 0);

    private List<Bank_Account> accs;
    private ArrayBlockingQueue queue;

    public Bank_Bank() {

        accs = new ArrayList<Bank_Account>();
        for (int i = 0; i < NumAccs; ++i)
            accs.add(new Bank_Account(i));

        queue = new ArrayBlockingQueue<Bank_Transaction>(Capicity);


    }

    public List<Bank_Account> getAccs() {
        return accs;
    }

    public ArrayBlockingQueue getQueue() {
        return queue;
    }

    public static void main(String[] args) throws InterruptedException {
        String source = args[0];//"/home/kdufla/IdeaProjects/ass4practice/src/5k.txt";
        int workerC = Integer.parseInt(args[1]);//"3");
        Thread[] workers = new Thread[workerC];

        Bank_Bank bank = new Bank_Bank();

        // run threads
        for (int i = 0; i < workerC; i++) {
            workers[i] = new Thread(new Bank_Worker(bank));
            workers[i].start();
        }


        //http://stackoverflow.com/questions/10752484/how-to-read-integer-values-from-text-file
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(source));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // add each transaction to queue
        while (scanner.hasNextInt()) {
            try {
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                int amount = scanner.nextInt();
                Bank_Transaction t = new Bank_Transaction(from, to, amount);
                bank.queue.put(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // add one stop message for each thread
        for (int i = 0; i < workerC; i++) {
            try {
                bank.queue.put(poison);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // wait for other threads
        for (int i = 0; i < workerC; i++) {
            workers[i].join();
        }

        for (int i = 0; i < bank.NumAccs; i++) {
            System.out.println(bank.accs.get(i));
        }
    }
}
