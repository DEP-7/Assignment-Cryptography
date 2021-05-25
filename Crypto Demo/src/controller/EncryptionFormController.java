package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import lk.ijse.crypto.DCCrypto;

public class EncryptionFormController {
    public JFXTextField txtText;
    public JFXTextField txtKey;
    public JFXTextField txtOutput;

    public void btnEncrypt_OnAction(ActionEvent actionEvent) {
        String text=txtText.getText().trim();
        String key=txtKey.getText().trim();
        if (text.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid text");
            txtText.requestFocus();
            return;
        }
        if (key.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid key");
            txtKey.requestFocus();
            return;
        }
        txtOutput.setText(DCCrypto.encrypt(text,key));
    }
}
