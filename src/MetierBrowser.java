import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MetierBrowser {
    public void createHistory(){
        try {
            Connection connection = SingletonConnexionDB.getConnection();

            String sql = "CREATE TABLE if not exists 'history' ( "+
                    "'id'	INTEGER," +
                    "'url'	TEXT, "+
                    "'date'	TEXT," +
                    "PRIMARY KEY('id' AUTOINCREMENT));";
            Statement statement = connection.createStatement();
            statement.execute( sql );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
