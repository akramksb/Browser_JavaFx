import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class BrowserController implements Initializable {

    @FXML
    private TabPane tabPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tab tab;
        tab = tabPane.getTabs().get(0);
        AnchorPane root = null;
        try {
            root = FXMLLoader.load( getClass().getResource("view/tabContent.fxml") );
            tab.setContent( root );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addTab(){
        int tabPos = tabPane.getTabs().size()-1;
        if ( tabPos == 0 )
        {
            ((Stage) tabPane.getScene().getWindow()).close();
            return;
        }

        Tab tab = new Tab();
        tab.setText( "New Tab" );

        tabPane.getTabs().add(tabPos, tab);
        tabPane.getSelectionModel().select(tabPos);
        try {
            AnchorPane root = FXMLLoader.load( getClass().getResource("view/tabContent.fxml") );
            tab.setContent(root );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
