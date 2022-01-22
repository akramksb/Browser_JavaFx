import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.*;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class TabController implements Initializable {

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
    private MetierBrowser metierBrowser = new MetierBrowser();


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
                // save to history in broeser.db
                metierBrowser.saveToHistory(engine.getLocation());
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
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        for ( WebHistory.Entry entrie : entries ){
            System.out.println( entrie.getLastVisitedDate() + " - " + entrie.getUrl() );
        }

    }

    public void goBack()
    {
        history = engine.getHistory();
        history.go(-1);

    }
    public void goForward()
    {
        history = engine.getHistory();
        history.go(1);
    }

    public void print() {

        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);

        double scaleFactor = Math.max( webView.getBoundsInParent().getWidth(), webView.getBoundsInParent().getHeight() );

        double scaleX = pageLayout.getPrintableWidth() / scaleFactor;
        double scaleY = pageLayout.getPrintableHeight() / scaleFactor;
        webView.getTransforms().add(new Scale(scaleX, scaleY));

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean success = job.printPage(webView);
            if (success) {
                job.endJob();
            }
        }
        double scaleX1 = scaleFactor / pageLayout.getPrintableWidth() ;
        double scaleY1 = scaleFactor / pageLayout.getPrintableHeight();
        webView.getTransforms().add(new Scale(scaleX1, scaleY1));
    }

}
