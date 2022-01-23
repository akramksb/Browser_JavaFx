package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TabPane root = FXMLLoader.load( getClass().getResource("/view/fxml/browser.fxml") );
        Scene scene = new Scene( root );
        primaryStage.setScene( scene );
        primaryStage.setTitle("Browser");
        primaryStage.getIcons().add(new Image("/view/icons/icons8-browser-60.png"));
        primaryStage.show();
    }
}
