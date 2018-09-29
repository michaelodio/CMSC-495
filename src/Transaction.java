package proto2;

public class Transaction {
    Account account;

    public Transaction(Account account){
        this.account = account;
    }
    public double depositFunds( double amount){
        //account.setBalance(account.getBalance() + amount);
        //TODO add transaction to database;
        return 0d;
    }
    /*public withdrawFunds(double amount){
        if (account.getBalance() >= amount){
            account.setBalance(account.getBalance() - amount);
            //TODO add transaction to database;
        }else{
            throw new InsufficientFundsException();
        }
    }*/
    /*public calculateInterest(Account account){
        //TODO send account to Interest to calculate interest;
        //TODO add transaction to database;
    }*/


}