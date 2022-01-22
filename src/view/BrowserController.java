package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import metier.MetierBrowser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BrowserController implements Initializable {

    @FXML
    private TabPane tabPane;

    private MetierBrowser metierBrowser = new MetierBrowser();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tab tab;
        tab = tabPane.getTabs().get(0);
        AnchorPane root = null;
        try {
            root = FXMLLoader.load( getClass().getResource("/view/tabContent.fxml") );
            tab.setContent( root );

            metierBrowser.createHistory();

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
            AnchorPane root = FXMLLoader.load( getClass().getResource("/view/tabContent.fxml") );
            tab.setContent(root );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
