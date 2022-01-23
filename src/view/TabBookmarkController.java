package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import metier.Bookmark;
import metier.MetierBrowser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class TabBookmarkController implements Initializable {

    @FXML
    private TableColumn<Bookmark, String> name;

    @FXML
    private AnchorPane tabContent;

    @FXML
    private TableColumn<Bookmark, String > urlbook;


    @FXML
    private TableView<Bookmark> tableBookmark;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MetierBrowser metierBrowser = new MetierBrowser();

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        urlbook.setCellValueFactory(new PropertyValueFactory<>("url"));

        tableBookmark.getItems().addAll( metierBrowser.getAllBookmarks() );
    }

    @FXML
    void exportBookmark() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".db", "*.db"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showSaveDialog(null);

        MetierBrowser metierBrowser = new MetierBrowser();
        metierBrowser.exportBookmark( selectedFile.getAbsolutePath() );

    }

    @FXML
    void importBookmark() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".db", "*.db"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(null);

        MetierBrowser metierBrowser = new MetierBrowser();
        metierBrowser.importBookmark( selectedFile.getAbsolutePath() );
    }

}
