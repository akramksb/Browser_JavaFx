package metier;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnexionDB {
    private static Connection connection;
    static {
        try {
            connection= DriverManager.getConnection("jdbc:sqlite:browser.db");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static Connection getConnection() {
        return connection;
    }
}
