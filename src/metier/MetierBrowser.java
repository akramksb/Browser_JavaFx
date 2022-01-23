package metier;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    public ArrayList<History> getAllHistory( )
    {
        try {
            Connection connection = SingletonConnexionDB.getConnection();
            ArrayList<History> histories = new ArrayList<History>();
            PreparedStatement preparedStatement = connection.prepareStatement( "SELECT * from history order by id DESC" );
            ResultSet resultSet = preparedStatement.executeQuery();

            while ( resultSet.next() )
            {
                histories.add( new History( resultSet.getInt(1),
                                resultSet.getString(3),
                                resultSet.getString(2) ));
            }
            return histories;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
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

    public void createBookmark( String dbPath ){
        try {
            Connection connection = SingletonConnexionDB.getConnection( dbPath );

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

    public ArrayList<Bookmark> getAllBookmarks()
    {
        try {
            Connection connection = SingletonConnexionDB.getConnection();
            return getBookmarks(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Bookmark> getAllBookmarks(String dbPath )
    {
        try {
            Connection connection = SingletonConnexionDB.getConnection( dbPath );
            return getBookmarks(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<Bookmark> getBookmarks(Connection connection) throws SQLException {
        ArrayList<Bookmark> bookmarks = new ArrayList<Bookmark>();
        PreparedStatement preparedStatement = connection.prepareStatement( "SELECT * from 'bookmark'" );
        ResultSet resultSet = preparedStatement.executeQuery();

        while ( resultSet.next() )
        {
            bookmarks.add( new Bookmark( resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3) ));
        }
        return bookmarks;
    }

    public void exportBookmark( String dbPath )
    {
        try {
            createBookmark( dbPath );
            Connection connection = SingletonConnexionDB.getConnection(dbPath);
            ArrayList<Bookmark> bookmarks = getAllBookmarks( );
            for (Bookmark bookmark : bookmarks ) {
                PreparedStatement preparedStatement = connection.prepareStatement( "INSERT INTO 'bookmark' ('name', 'url') VALUES (? , ?);" );
                preparedStatement.setString(1, bookmark.getName());
                preparedStatement.setString(2, bookmark.getUrl());

                preparedStatement.executeUpdate();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void importBookmark( String dbPath )
    {
        try {
            ArrayList<Bookmark> bookmarks = getAllBookmarks( dbPath );
            Connection connection = SingletonConnexionDB.getConnection();
            for (Bookmark bookmark : bookmarks ) {
                PreparedStatement preparedStatement = connection.prepareStatement( "INSERT INTO 'bookmark' ('name', 'url') VALUES (? , ?);" );
                preparedStatement.setString(1, bookmark.getName());
                preparedStatement.setString(2, bookmark.getUrl());

                preparedStatement.executeUpdate();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
