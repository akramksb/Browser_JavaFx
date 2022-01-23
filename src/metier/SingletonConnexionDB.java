package metier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexionDB {
    private static Connection connection;
    static {
        try {
            connection= DriverManager.getConnection("jdbc:sqlite:browser.db");
        }catch (Exception e){
            System.out.println( e.getMessage() );
        }

    }
    public static Connection getConnection() {
        return connection;
    }
    public static Connection getConnection( String path )
    {
        try {
            return DriverManager.getConnection("jdbc:sqlite:"+path);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
