package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import lk.ijse.crypto.DCCrypto;

public class DecryptionFormController {
    public JFXTextField txtKey;
    public JFXTextField txtOutput;
    public JFXTextField txtCipherText;

    public void btnDecrypt_OnAction(ActionEvent actionEvent) {
        String cipherText=txtCipherText.getText().trim();
        String key=txtKey.getText().trim();
        if (cipherText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid cipherText").show();
            txtCipherText.requestFocus();
            return;
        }
        if (key.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid key").show();
            txtKey.requestFocus();
            return;
        }
        txtOutput.setText(DCCrypto.decrypt(cipherText,key));
    }
}
