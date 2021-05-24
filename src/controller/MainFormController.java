package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {
    public AnchorPane root;

    public void btnEncryption_OnAction(ActionEvent actionEvent) throws IOException {
        loadForm("Encryption");
    }

    public void btnDecryption_OnAction(ActionEvent actionEvent) throws IOException {
        loadForm("Decryption");
    }

    private void loadForm(String formName) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(this.getClass().getResource("../view/"+formName+"Form.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(formName+" Window");
        stage.centerOnScreen();
        stage.initOwner(this.root.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }
}
