import java.sql.SQLException;

public class Transaction {


    /*public Transaction(Account account){
        this.account = account;
    }*/
    public static void depositFunds(double amount, int actNum){
        try {
        	double prevBal = 0;
        	double newBal = 0;

        	prevBal = Input.getBalance(actNum);
        	newBal = prevBal + amount;
             //updates db
			Input.setBalance(newBal, actNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


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
