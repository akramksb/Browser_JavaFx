import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TabPane root = FXMLLoader.load( getClass().getResource("view/browser1.fxml") );
        Scene scene = new Scene( root );
        primaryStage.setScene( scene );
        primaryStage.show();
    }
}
