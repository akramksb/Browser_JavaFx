package view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import metier.Bookmark;

public class TabBookmarkController {

    @FXML
    private TableColumn<Bookmark, String> name;

    @FXML
    private AnchorPane tabContent;

    @FXML
    private TableColumn<Bookmark, String > urlbook;

}
