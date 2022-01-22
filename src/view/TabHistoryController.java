package view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import metier.History;

public class TabHistoryController {

        @FXML
        private TableColumn<History, String> date;

        @FXML
        private AnchorPane tabContent;

        @FXML
        private TableColumn<History, String> urlhstroy;

}
