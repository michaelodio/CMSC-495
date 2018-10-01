package proto2;

import java.sql.SQLException;

public class Transaction {

    public static void depositFunds(double amount, int actNum){
        try {
        	double prevBal = 0;
        	double newBal = 0;

        	prevBal = Input.getBalance(actNum);
        	newBal = prevBal + amount;

			Input.setBalance(newBal, actNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }
    public static void withdrawFunds(double amount,int actNum){

    	 try {
         	double prevBal = 0;
         	double newBal = 0;

         	prevBal = Input.getBalance(actNum);

         	if(prevBal>= amount) {
         		newBal = prevBal - amount;
         		Input.setBalance(newBal, actNum);
         	}



 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}




    }
    /*public calculateInterest(Account account){
        //TODO send account to Interest to calculate interest;
        //TODO add transaction to database;
    }*/


}
