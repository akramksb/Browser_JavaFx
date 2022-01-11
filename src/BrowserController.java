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

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class BrowserController implements Initializable {

    @FXML
    private TabPane tabPane;

    @FXML
    private AnchorPane tabContent;

    @FXML
    private TextField barRecherche;

    @FXML
    private Button buttonGo;

    @FXML
    private WebView webView;

    @FXML
    private Button buttonRe;

    @FXML
    private Button buttonZoonIn;

    @FXML
    private Button buttonZoonOut;

    @FXML
    private Button buttongoBack;
    @FXML
    private Button buttongoFront;

    private WebHistory history;


    private WebEngine engine;
    private float zoomScale;


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



}
