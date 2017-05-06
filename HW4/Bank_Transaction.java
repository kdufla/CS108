/**
 * encapsulation of transactin
 */
public class Bank_Transaction {

    private int from;
    private int to;
    private int amount;

    public Bank_Transaction(int from, int to, int amount){
        this.from=from;
        this.to=to;
        this.amount=amount;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString(){
        return from+" "+to+" "+amount;
    }
}
