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
        zoomScale = 1;
        engine = webView.getEngine();
        loadPage();
    }

    public void loadPage(){
//        engine.load("http://www.google.com");
        if ( barRecherche.getText().contains( "://" )  )
            engine.load( barRecherche.getText() );
        else
        {
            engine.load("https://www.google.com/search?q=" + barRecherche.getText() );
        }

        engine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                // new page has loaded, process:
                barRecherche.setText( engine.getLocation());

            }
        });
    }

    public void refreshPage(){
        zoomScale = 1;
        webView.setZoom(zoomScale);
        engine.reload();
    }

    public void zoomIn(){
        zoomScale += 0.25;
        webView.setZoom(zoomScale);
    }
    public void zoomOut(){
        zoomScale -= 0.25;
        webView.setZoom(zoomScale);
    }

    public void displayHistory()
    {
//        history = engine.getHistory();
//        ObservableList<WebHistory.Entry> entries = history.getEntries();
//        for ( WebHistory.Entry entrie : entries ){
//            System.out.println( entrie.getLastVisitedDate() + " - " + entrie.getTitle() );
//        }

        Tab tab = new Tab();
        tab.setText( "test" );

        tabPane.getTabs().add(tab);
        try {
            TabPane root = FXMLLoader.load( getClass().getResource("view/browser.fxml") );
            tab.setContent(root.getTabs().get(0).getContent() );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void goBack()
    {

    }
    public void goFront()
    {

    }

}
