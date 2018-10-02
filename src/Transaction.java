import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

    public Transaction(int accountNumber, double amount, String transactionType, String accountType) throws SQLException {


        //TODO integrate into a constructor

        Database database = new Database();
        Connection dbConn = database.getConnection();

        // creates the table if it does not exist

        String createString =
                "create table if not exists " + "Transactions" +
                        "(ACCOUNT_NUM integer(40) NOT NULL, " +
                        "ACCOUNT_TYPE varchar(40) NOT NULL, " +
                        "TRANSACTION_TYPE varchar(40) NOT NULL, " +
                        "AMOUNT Decimal(15,2) NOT NULL, " +
                        "TRANSACTION_TIME varchar(40) NOT NULL, " +
                        "TRANSACTION_ID integer PRIMARY KEY AUTOINCREMENT) ";

        Statement createStmt = null;
        try {
            createStmt = dbConn.createStatement();
            createStmt.executeUpdate(createString);
        } catch (SQLException e) {
            System.out.println("------------------TableCreate-----------------");
            System.out.println("Cannot create TRANSACTIONS table: " + e);
            System.out.println("--------------------------------------------------------");
        } finally {
            if (createStmt != null) {
                createStmt.close();
            }
        }

        //adding transaction information

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43


        Statement insertStmt = null;
        try {
            insertStmt = dbConn.createStatement();
            insertStmt.executeUpdate(
                    "insert into Transactions (ACCOUNT_NUM, ACCOUNT_TYPE, TRANSACTION_TYPE, TRANSACTION_TIME, AMOUNT) " +
                            "values('" + accountNumber + "','" + accountType + "', " +
                            "'" + transactionType + "', " +
                            "'" + dateFormat.format(date) + "', " +
                            "'" + amount + "')");
        } catch (SQLException e) {
            System.out.println("------------------TableInsert-----------------");
            System.out.println("Cannot insert into Transactions table: " + e);
            System.out.println("--------------------------------------------------------");
        } finally {
            if (insertStmt != null) {
                insertStmt.close();
                dbConn.close();
            }
        }

        switch(transactionType){

            case "Deposit":
                depositFunds(amount, accountNumber);
                break;
            case "Withdrawal":
                withdrawFunds(amount, accountNumber);
                break;
            case "Interest":
                //TODO calculate interest
                break;
            default:

        }
    }


    private void depositFunds( double amount, int accountNumber){
        try {
            Input.setBalance(Input.getBalance(accountNumber) + amount, accountNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void withdrawFunds(double amount, int accountNumber){
        try {
            if (Input.getBalance(accountNumber) >= amount){
                Input.setBalance(Input.getBalance(accountNumber) - amount, accountNumber);

            }else{
                throw new IllegalArgumentException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
           System.out.println("Insufficient Funds");
        }
    }
    /*public calculateInterest(Account account){
        //TODO send account to Interest to calculate interest;
        //TODO add transaction to database;
    }*/


}
