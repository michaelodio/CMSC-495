public class Login {
    String username;
    String password;

    public Login(String username, String Password){
        this.username = username;
        this.password = password;
    }
    boolean checkCredentials(String username, String Password){
        if (username is in database){
            if (password == databasePassword) {
                return true;
            }else { return false }
        }else { return false}
    }

}
