public class Bank_Worker implements Runnable {

    private Bank_Bank bank;

    public Bank_Worker(Bank_Bank bank) {
        this.bank=bank;
    }

    @Override
    public void run() {
        while (true){
            Bank_Transaction t=(Bank_Transaction)bank.getQueue().poll();
            if(t!=null) {
                if (t == bank.poison) break;
                handleTransaction(t);
            }
        }

    }

    /**
     * decrease and increase balances according to transaction
     */
    public void handleTransaction(Bank_Transaction t){
        int from=t.getFrom();
        int to=t.getTo();
        int amount=t.getAmount();
        Bank_Account ac=bank.getAccs().get(from);
        ac.decremntBalance(amount);

        ac=bank.getAccs().get(to);
        ac.incrementBalance(amount);
    }
}
