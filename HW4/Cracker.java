import java.util.ArrayList;

public class Cracker {

    public static void main(String[] args) {
        if (args.length > 1) {
            hashToPassword(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        } else {
            System.out.println(passwordToHash(args[0]));
        }
    }

    private static String passwordToHash(String str) {
        return HashCracker_Cracker.hexToString(HashCracker_Cracker.hash(str));
    }

    private static void hashToPassword(String hash, int passLen, int threads) {
        int chars=HashCracker_Cracker.CHARS.length;

        int num=chars/threads;
        int diff=chars-num*threads;
        int n,start=0;

        Thread[] threadList=new Thread[threads];

        for (int i = 0; i < threads; i++) {
            if (diff>0){
                n=1;
                diff--;
            }else
                n=0;

            threadList[i]=new Thread(new HashCracker_Worker(start,num+n,passLen,hash));
            threadList[i].start();

            start+=num+n;
        }

        for (int i = 0; i < threads; i++) {
            try {
                threadList[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("all done");

    }

}