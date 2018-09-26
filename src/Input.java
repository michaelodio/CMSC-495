public class Input {
    String username;
    String password;
    String emailAddress;
    String fName;
    String lName;
    int phoneNum;

    public void Input(){
        //display login prompt to user;
        if (new user){
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

        }

    }
    void createUserAccount(String accountType){
        //TODO add to database new Account(username, password, emailAddress, fName,  lName, phoneNum, accountType);
    }
    void createNewAccount(String accountType){
        //TODO add to database new Account(username, accountType);
    }
    boolean login(String username, String password){
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
    }

}
