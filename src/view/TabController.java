package view;

import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import metier.MetierBrowser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TabController implements Initializable {

    @FXML
    private TextField barRecherche;

    @FXML
    private Button bookmark;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button buttonGo;

    @FXML
    private Button buttonHistory;

    @FXML
    private Button buttonPrint;

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

    @FXML
    private AnchorPane tabContent;

    @FXML
    private WebView webView;

    private WebHistory history;


    private WebEngine engine;
    private float zoomScale;

    private BrowserController browserController;

    private boolean isPrivate = false;

    private MetierBrowser metierBrowser = new MetierBrowser();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        zoomScale = 1;
        engine = webView.getEngine();

        engine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                // new page has loaded, process:
                barRecherche.setText( engine.getLocation());
                progressBar.setVisible(false);
                // save to history in broeser.db
                if ( !isPrivate )
                    metierBrowser.saveToHistory(engine.getLocation());
            }
            if ( newState == Worker.State.RUNNING )
            {
                progressBar.setVisible(true);
                progressBar.progressProperty().bind(engine.getLoadWorker().progressProperty());
            }
        });

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

    public void privateWindow()
    {
        try {
            Stage dialog = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/browser.fxml"));
            TabPane root = fxmlLoader.load();

            BrowserController controller = ( BrowserController ) fxmlLoader.getController();
            controller.setPrivate(true);

            Scene dialogScene = new Scene( root );
            dialog.setScene(dialogScene);
            dialog.setTitle("Private window");
            dialog.show();
            dialog.getIcons().add(new Image("/view/icons/icons8-pirate-64.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayHistory()
    {
//        showpage("history");
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        for ( WebHistory.Entry entrie : entries ){
            System.out.println( entrie.getLastVisitedDate() + " - " + entrie.getUrl() );
        }

    }

    public void goBack()
    {
        try {
            history = engine.getHistory();
            history.go(-1);
        }catch (Exception e){}

    }
    public void goForward()
    {
        try{
            history = engine.getHistory();
            history.go(1);
        }
        catch ( Exception e){}
    }

    public void bookmarkPage(){
        try {
            Stage dialog = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/BookmarkPopUp.fxml"));
            AnchorPane bookmarkContent = fxmlLoader.load();

            AddBookmarkConroller controller = ( AddBookmarkConroller ) fxmlLoader.getController();
            controller.setBrowserController( browserController );
            controller.setUrl( engine.getLocation() );
            controller.setName( engine.getTitle() );

            Scene dialogScene = new Scene( bookmarkContent );
            dialog.setScene(dialogScene);
            dialog.setTitle("add bookmark");
            dialog.show();

        }catch (Exception e) { e.printStackTrace(); }
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

    public void displayBookmark(){
        try {
//            showpage("Bookmark");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
//    public void showpage(String fxml) {
//        try {
//            Parent parent = FXMLLoader.load(getClass().getResource("/view/fxml/" + fxml + ".fxml"));
//            Stage stage = new Stage();
//            Scene scene = new Scene(parent);
//            stage.setScene(scene);
//            stage.setResizable(false);
//            stage.setAlwaysOnTop(true);
//            stage.initModality(Modality.WINDOW_MODAL);
//            stage.show();
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//
//        }
//    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void setBrowserController(BrowserController browserController) {
        this.browserController = browserController;
    }
}
