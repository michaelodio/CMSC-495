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

        while(true) {
            System.out.println("Select an option: " +
                    "\n1. Login" +
                    "\n2. Register" +
                    "\nChoice: ");

            int option = Integer.parseInt(in.nextLine());

            if (option == 1) {


                System.out.println("Login:");
                System.out.println("Username:");
                username = in.nextLine();
                System.out.println("Password:");
                password = in.nextLine();

                if (login(username, password)) {
                    System.out.println("login Successful!");

                    System.out.println("Create new Account:");
                    System.out.println("Select account type:" +
                    "\n 1. Checking" +
                    "\n 2. Savings");
                    int typeChoice = Integer.parseInt(in.nextLine());
                    if (typeChoice == 1){
                        accountType = "Checking";
                    }else{
                        accountType = "Savings";
                    }
                    System.out.println("You have selected " + accountType);
                    System.out.println("Enter initial balance: ");
                    double balance = Double.parseDouble(in.nextLine());

                    createNewAccount(accountType, balance);

                }

            } else {

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
            }
        }
    }
    void createUserAccount(String accountType) throws SQLException {
        new Account(username, password, emailAddress, fName, lName, phoneNum);
    }
    void createNewAccount(String accountType, double balance){
        new Account(username, accountType, balance);
    }
    boolean login(String username, String password){
        Login login = new Login(username, password);
        try {
            if (login.checkCredentials()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    /*double createTransaction(Account account; String transactionType, int amount){
        if (transactionType is withdraw){
            withdraw from account;
        }else if(transactionType is deposit){
            deposit to account;
        }else{calculate interest}
    }*/

}
