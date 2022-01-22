package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class BookmarkController implements Initializable {

    @FXML
    private Button BuutonSearch;

    @FXML
    private TableColumn<?, ?> nomBookmark;

    @FXML
    private TableColumn<?, ?> urlBookmark;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
