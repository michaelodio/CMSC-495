import java.sql.SQLException;
import java.util.Scanner;

public class Input {
    String username = "";
    String password = "";
    String emailAddress = "";
    String fName = "";
    String lName = "";
    int phoneNum = 0;
    String accountType = "";

    public Input(){
        //display login prompt to user;

        //Temporary console prompt:

        Scanner in = new Scanner(System.in);

        System.out.println("Enter Account Info:");
        System.out.print("Username:");
        username = in.nextLine();
        System.out.print("\nPassword:");
        password = in.nextLine();
        System.out.print("\nEmail:");
        emailAddress = in.nextLine();
        System.out.print("\nFirst Name:");
        fName = in.nextLine();
        System.out.print("\nLast Name:");
        lName = in.nextLine();
        System.out.print("\nPhone Number:");
        phoneNum = Integer.parseInt(in.nextLine());
        System.out.print("\nChecking or Savings?:");
        accountType = in.nextLine();


        try {
            createUserAccount(accountType);
        } catch (SQLException e) {
            System.out.println("------------------AccountCreate-----------------");
            System.out.println("Cannot create new account: " + e);
            System.out.println("--------------------------------------------------------");
        }


        /*if (new user){
            createUserAccount(accountType);
        }else if(currentUser){
            if (login() returns true){
                while (true){
                    //prompt user to display balance, transaction history, create transaction or create new account;
                    if (display balance and transaction hist){
                        for all accounts in database owned by user
                        Display disp = new Display();
                        disp.displayBalance(account);
                    }else if(create new account){
                        prompt user for accountType;
                        createNewAccount(accountType);
                    }else{
                        prompt user for transactionType;
                        createTransaction(transactionType);
                    }
                }
            }else{display login error and display login prompt}

        }*/

    }
    void createUserAccount(String accountType) throws SQLException {
        new Account(username, password, emailAddress, fName, lName, phoneNum, accountType);
    }
    void createNewAccount(String accountType){
        //TODO add to database new Account(username, accountType);
    }
   /* boolean login(String username, String password){
        //TODO send username and password to Login to check database;
        if (Login returns true){
            return true;
        }else{return false}
    }
    double createTransaction(Account account; String transactionType, int amount){
        if (transactionType is withdraw){
            withdraw from account;
        }else if(transactionType is deposit){
            deposit to account;
        }else{calculate interest}
    }*/

}
