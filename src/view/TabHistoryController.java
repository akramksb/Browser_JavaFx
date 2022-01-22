package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import metier.History;
import metier.MetierBrowser;

import java.net.URL;
import java.util.ResourceBundle;

public class TabHistoryController implements Initializable {

        @FXML
        private TableColumn<History, String> date;

        @FXML
        private AnchorPane tabContent;

        @FXML
        private TableView<History> tablHistory;

        @FXML
        private TableColumn<History, String> urlhstroy;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                MetierBrowser metierBrowser = new MetierBrowser();

                date.setCellValueFactory(new PropertyValueFactory<>("date"));
                urlhstroy.setCellValueFactory(new PropertyValueFactory<>("url"));

                tablHistory.getItems().addAll(metierBrowser.getAllHistory());
        }
}
