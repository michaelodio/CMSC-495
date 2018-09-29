package proto2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Account {

    private String accountType; //Checkings or Savings
    private String accountOwner;
    private int accountNumber;
    private double balance = 0d;
    private String username;
    private String password;
    private String emailAddress;
    private String fName;
    private String lName;
    private int phoneNum;
    private Database database;
    private Connection dbConn;


    //This constructor acts as a new account registration for users
    public Account(String username, String password, String emailAddress, String fName, String lName, int phoneNum) throws SQLException {

        database = new Database();
        dbConn = database.getConnection();

        //this.accountNumber = getAccountNum();
        this.accountOwner = username;
        this.accountType = accountType;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phoneNum;

        // creates the table if it does not exist

        String createString =
                "create table if not exists " + "Users" +
                        "(USERNAME varchar(40) NOT NULL, " +
                        "PASSWORD varchar(40) NOT NULL, " +
                        "EMAIL varchar(40) NOT NULL, " +
                        "FNAME varchar(40) NOT NULL, " +
                        "LNAME varchar(20) NOT NULL, " +
                        "PHONENUM integer NOT NULL, " +
                        "ID integer PRIMARY KEY AUTOINCREMENT) ";

        Statement createStmt = null;
        try {
            createStmt = dbConn.createStatement();
            createStmt.executeUpdate(createString);
        } catch (SQLException e) {
            System.out.println("------------------TableCreate-----------------");
            System.out.println("Cannot create table: " + e);
            System.out.println("--------------------------------------------------------");
        } finally {
            if (createStmt != null) {
                createStmt.close();
            }
        }

        //adding account information

        Statement insertStmt = null;
        try {
            insertStmt = dbConn.createStatement();
            insertStmt.executeUpdate(
                    "insert into Users (USERNAME, PASSWORD, EMAIL, FNAME, LNAME, PHONENUM) " +
                            "values('" + username + "','" + password + "', " +
                            "'" + emailAddress + "', " +
                            "'" + fName + "', " +
                            "'" + lName + "', " +
                            "'" + phoneNum + "')");
        } catch (SQLException e) {
            System.out.println("------------------TableInsert-----------------");
            System.out.println("Cannot insert into table: " + e);
            System.out.println("--------------------------------------------------------");
        } finally {
            if (insertStmt != null) {
                insertStmt.close();
            }
        }


    }

    //this constructor creates checking or savings accounts for existing users
    public Account (String username, String accountType, double balance){

        database = new Database();
        dbConn = database.getConnection();
        try {
            createAccount(username, accountType, balance);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    void createAccount(String username, String accountType, double balance) throws SQLException {

        // creates the table if it does not exist

        String createString =
                "create table if not exists " + "Accounts" +
                        "(USERNAME varchar(40) NOT NULL, " +
                        "ACCOUNTTYPE varchar(40) NOT NULL, " +
                        "BALANCE DECIMAL(14, 2), " +
                        "ID integer PRIMARY KEY AUTOINCREMENT)";

        Statement createStmt = null;
        try {
            createStmt = dbConn.createStatement();
            createStmt.executeUpdate(createString);
        } catch (SQLException e) {
            System.out.println("------------------TableCreate-----------------");
            System.out.println("Cannot create table: " + e);
            System.out.println("--------------------------------------------------------");
        } finally {
            if (createStmt != null) {
                createStmt.close();
            }
        }

        //adding account information

        Statement insertStmt = null;
        try {
            insertStmt = dbConn.createStatement();
            insertStmt.executeUpdate(
                    "insert into Accounts (USERNAME, ACCOUNTTYPE, BALANCE)" +
                            "values('" + username + "','" + accountType + "', " +
                            "'" + balance + "')");
        } catch (SQLException e) {
            System.out.println("------------------TableInsert-----------------");
            System.out.println("Cannot insert into table: " + e);
            System.out.println("--------------------------------------------------------");
        } finally {
            if (insertStmt != null) {
                insertStmt.close();
            }
        }


    }

    public void setBalance(double balance, int accountNumber) throws SQLException {
        this.balance = balance;
        Statement insertStmt = null;

        try {
            insertStmt = dbConn.createStatement();
            insertStmt.executeUpdate(
                    "UPDATE Accounts " +
                            "SET balance = " +
                            "values('" + balance + "')" +
                            " WHERE ID = " + accountNumber );
        } catch (SQLException e) {
            System.out.println("------------------TableInsert-----------------");
            System.out.println("Cannot insert into table: " + e);
            System.out.println("--------------------------------------------------------");
        } finally {
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }



    /*public String getAccountType() {
        return this.accountType;
    }
    public double getBalance() {
        return this.balance;
    }*/
}