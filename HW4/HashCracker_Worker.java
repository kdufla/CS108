import java.util.Arrays;

public class HashCracker_Worker implements Runnable {

    private int start;
    private int range;
    private int len;
    private byte[] hash;

    public HashCracker_Worker(int start, int range, int len, String hash){
        this.start=start;
        this.range=range;
        this.len=len-1;
        this.hash=HashCracker_Cracker.hexToArray(hash);
    }

    public String guess(byte[] hash, String word,int left){
        byte[] h=HashCracker_Cracker.hash(word);
        if(Arrays.equals(hash,h))
            return word;

        String ret="";
        if(left==0)
            return ret;


        for(char c: HashCracker_Cracker.CHARS) {
            ret = guess(hash, word + c, left - 1);
            if (ret.length()>0)break;
        }
        return ret;
    }

    @Override
    public void run() {
        for (int i = start; i < start+range; i++) {
            String ans=guess(hash,""+HashCracker_Cracker.CHARS[i],len);
            if(ans.length()>0){
                System.out.println(ans);
                break;
            }
        }
    }
}
