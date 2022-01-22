package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import metier.Bookmark;
import metier.MetierBrowser;

import java.net.URL;
import java.util.ArrayList;
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

        tableBookmark.getItems().addAll( metierBrowser.getAllBookmark() );


    }
}
