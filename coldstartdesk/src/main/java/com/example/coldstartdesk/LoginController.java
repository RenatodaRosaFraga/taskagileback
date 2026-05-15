package com.example.coldstartdesk;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtSenha;

    @FXML
    private void onHelloButtonClick(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Login");
        alert.setHeaderText(null);
        alert.setContentText("Login efetuado com o email!"+txtLogin.getText());
        alert.showAndWait();
    }
}
