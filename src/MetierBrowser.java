import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public void saveToHistory( String date, String url )
    {
        try {
            Connection connection = SingletonConnexionDB.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement( "INSERT INTO 'history' ('url', 'date') VALUES (? , ?);" );
            preparedStatement.setString(1, url);
            preparedStatement.setString(2, date);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveToHistory( String url )
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        saveToHistory( formatter.format(date), url );
    }

    public void createBookmark(){
        try {
            Connection connection = SingletonConnexionDB.getConnection();

            String sql = "CREATE TABLE if not exists 'bookmark' ( "+
                    "'id'	INTEGER," +
                    "'name'	TEXT, "+
                    "'url'	TEXT," +
                    "PRIMARY KEY('id' AUTOINCREMENT));";
            Statement statement = connection.createStatement();
            statement.execute( sql );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveToBookmark( String name, String url )
    {
        try {
            Connection connection = SingletonConnexionDB.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement( "INSERT INTO 'bookmark' ('name', 'url') VALUES (? , ?);" );
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, url);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
