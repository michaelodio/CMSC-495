import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


class Transaction {

    private NumberFormat formatter = new DecimalFormat("#0.00");

    static boolean noFunds = false;


    Transaction(int accountNumber, double amount, String transactionType, String accountType) throws SQLException {


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
        switch (transactionType.toLowerCase()) {

            case "deposit":
                depositFunds(amount, accountNumber);
                break;
            case "withdrawal":
                withdrawFunds(amount, accountNumber);
                break;
            case "interest":
                amount = calculateInterest(accountNumber, accountType);
                break;
            default:
                break;
        }


        Statement insertStmt = null;
        try {
            insertStmt = dbConn.createStatement();
            insertStmt.executeUpdate(
                    "insert into Transactions (ACCOUNT_NUM, ACCOUNT_TYPE, TRANSACTION_TYPE, TRANSACTION_TIME, AMOUNT) " +
                            "values('" + accountNumber + "','" + accountType + "', " +
                            "'" + transactionType + "', " +
                            "'" + dateFormat.format(date) + "', " +
                            "'" + Double.parseDouble(formatter.format(amount)) + "')");
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

    }


    private void depositFunds(double amount, int accountNumber) {
        try {
            NumberFormat formatter = new DecimalFormat("#0.00");
            double balance = Input.getBalance(accountNumber);
            Input.setBalance(balance + Double.parseDouble(formatter.format(amount)), accountNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void withdrawFunds(double amount, int accountNumber) {
        try {
            if (Input.getBalance(accountNumber) >= amount) {
                Input.setBalance(Input.getBalance(accountNumber) - amount, accountNumber);

            } else {
                throw new IllegalArgumentException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            noFunds = true;
            System.out.println("Insufficient Funds");
        }
    }

    private double calculateInterest(int accountNum, String accountType) {

        try {
            double currentBalance = Input.getBalance(accountNum);
            double interestAmount;

            //DEBUG

            System.out.println("Calculating interest...");
            System.out.println("Current balance: " + currentBalance);

            if (accountType.equals("Checking")) {
                interestAmount = currentBalance * .0006d;
                depositFunds(interestAmount, accountNum);
            } else {
                interestAmount = currentBalance * .04d;
                depositFunds(interestAmount, accountNum);
            }

            //DEBUG

            System.out.println("Account Type:  " + accountType);
            System.out.println("Interest Amount: " + interestAmount);

            return interestAmount;


        } catch (SQLException e) {
            e.printStackTrace();

        }
        return 0d;

    }


}
