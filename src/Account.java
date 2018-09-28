import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Account {

    private String accountType; //Checkings or Savings
    private String accountOwner;
    private int accountNumber;
    private double balance = 0d;
    private Database database;
    private Connection dbConn;


    public Account(String username, String password, String emailAddress, String fName, String lName, int phoneNum, String
            accountType) throws SQLException {

        database = new Database();
        dbConn = database.getConnection();

        //SQL query to count number of rows in the accounts table

        Statement stmt = null;
        String query = "select COUNT(*) from Accounts";
        int size = 0;
        try {
            stmt = dbConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                size++;
            }
        } catch (SQLException e ) {
            System.out.println("------------------Tablerize.getRowCount-----------------");
            System.out.println("Cannot get resultSet row count: " + e);
            System.out.println("--------------------------------------------------------");
        } finally {
            if (stmt != null) { stmt.close(); }
        }


        this.accountNumber =  size + 1;
        this.accountOwner = username;
        this.accountType = accountType;

        // creates the table if it does not exist

        String createString =
                "create table if not exists " + "Accounts" +
                        "(USERNAME varchar(40) NOT NULL, " +
                        "PASSWORD varchar(40) NOT NULL, " +
                        "EMAIL varchar(40) NOT NULL, " +
                        "FNAME varchar(40) NOT NULL, " +
                        "LNAME varchar(20) NOT NULL, " +
                        "PHONENUM integer NOT NULL, " +
                        "ACCOUNTTYPE varchar(40) NOT NULL, " +
                        "ACCOUNTOWNER varchar(40) NOT NULL, " +
                        "ACCOUNTNUMBER integer NOT NULL, " +
                        "BALANCE DECIMAL(14,2) NOT NULL, " +
                        "PRIMARY KEY (ACCOUNTNUMBER))";

        Statement createStmt = null;
        try {
           createStmt = dbConn.createStatement();
           createStmt.executeUpdate(createString);
        } catch (SQLException e) {
            System.out.println("------------------TableCreate-----------------");
            System.out.println("Cannot create table: " + e);
            System.out.println("--------------------------------------------------------");
        } finally {
            if (createStmt != null) { createStmt.close(); }
        }

        //adding account information

        Statement insertStmt = null;
        try {
            insertStmt = dbConn.createStatement();
            insertStmt.executeUpdate(
                    "insert into Accounts " +
                            "values('" + username + "','"+ password +"', " +
                            "'"+ emailAddress +"', " +
                            "'"+ fName +"', " +
                            "'"+ lName +"', " +
                            "'"+ phoneNum +"', " +
                            "'"+ accountType +"', " +
                            "'"+ accountOwner +"', " +
                            "'"+ accountNumber +"', " +
                            "'"+ balance +"')");
        } catch (SQLException e) {
            System.out.println("------------------TableInsert-----------------");
            System.out.println("Cannot insert into table: " + e);
            System.out.println("--------------------------------------------------------");
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }

    /*public Account(String username, String accountType) {
        this.accountNumber = count of database entries + 1;
        this.accountOwner = username;
        this.accountType = accountType;

        create new database entry and add accountNumber and accountOwner;
        create new user database entry and add
        username
                password
        emailAddress
                fName
        lName
                phonNum;
    }*/

   /* public void setBalance(double balance) {
        this.balance = balance;
        put new balance in database;
        public double getBalance () {
            balance = balance from database;
            return balance;
        }
        public int getAccountNumber () {
            return accountNumber;
        }

    }*/

    public String getAccountType() {
        return this.accountType;
    }

    public double getBalance() {
        return this.balance;
    }
}
