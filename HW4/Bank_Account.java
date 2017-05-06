/**
 * encapsulation of account
 * can change balance of individual account and count total transactions
 */
public class Bank_Account {

    private static final int Def_Ballance=1000;

    private int id;
    private int balance;
    private int transactions;

    public Bank_Account(int id){
        this.id=id;
        balance=Def_Ballance;
        transactions=0;
    }

    public int getBalance(){
        return balance;
    }

    public int getId(){
        return id;
    }

    public synchronized void incrementBalance(int i){
        balance+=i;
        transactions++;
    }

    public synchronized void decremntBalance(int i){
        balance-=i;
        transactions++;
    }

    @Override
    public String toString(){
        return "acct:"+id+" bal:"+balance+" trans:"+transactions;
    }

}
