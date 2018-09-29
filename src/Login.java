package proto2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {
    String username;
    String password;
    String dbPassword;

    public Login(String username, String password){
        this.username = username;
        this.password = password;
    }
    boolean checkCredentials() throws SQLException {

        Database database = new Database();
        Connection dbConn = database.getConnection();

        Statement stmt = null;
        String query =
                "select PASSWORD " +
                        "from " + "Users" +
                        " where USERNAME = '" + username + "'";

        try {
            stmt = dbConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                dbPassword = rs.getString("PASSWORD");

            }
        } catch (SQLException e ) {
            System.out.println("------------------Login-----------------");
            System.out.println("Login Failed: " + e);
            System.out.println("--------------------------------------------------------");
        } finally {
            if (stmt != null) { stmt.close(); }
            dbConn.close();
        }


        if (password.equals(dbPassword)){
            return true;
        }else{
            return false;
        }
    }

}