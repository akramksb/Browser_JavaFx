package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import metier.MetierBrowser;

public class AddBookmarkConroller {

    @FXML
    private TextField nameField;

    private String url;
    private String name;
    private BrowserController browserController;
    private MetierBrowser metierBrowser = new MetierBrowser();

    @FXML
    void annuler(ActionEvent event) {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    @FXML
    void enregistrer(ActionEvent event) {
        name = nameField.getText();
        metierBrowser.saveToBookmark( name, url );
        annuler(event);
    }

    @FXML
    void showBookmarks(ActionEvent event) {
        browserController.addTab();
        annuler(event);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
        nameField.setText( name );
    }

    public void setBrowserController(BrowserController browserController) {
        this.browserController = browserController;
    }
}