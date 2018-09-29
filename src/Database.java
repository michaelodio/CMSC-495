package proto2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    Connection databaseConn;
    String url = "jdbc:sqlite:Resources/group7.db";

    public Database(String url){

        connect(url);
    }

    public Database (){

        connect(this.url);
    }

    private void connect(String url) {
        databaseConn = null;
        try {
            // create a connection to the database
            databaseConn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
       /* } finally {
            try {
                if (databaseConn != null) {
                    databaseConn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }*/
        }
    }

    public Connection getConnection(){
        return databaseConn;
    }

}