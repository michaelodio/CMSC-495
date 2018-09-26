public class Account {

    private String accountType; //Checkings or Savings
    private String accountOwner;
    private int accountNumber;
    private double balance;


    public Account(String username, String accountType) {

        this.accountNumber =
                count of
        database entries +1;
        this.accountOwner = username;
        this.accountType = accountType;
        create new
                database entry
        and add
        accountNumber,
                accountOwner and
                accountType;
    }

    public Account(String username, String password, String emailAddress, String fName, String lName, int phoneNum, String
            accountType) {
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
    }

    public void setBalance(double balance) {
        this.balance = balance;
        put new balance in database;
        public double getBalance () {
            balance = balance from database;
            return balance;
        }
        public int getAccountNumber () {
            return accountNumber;
        }

    }

    public String getAccountType() {
        return this.accountType;
    }

    public double getBalance() {
        return this.balance;
    }
}
