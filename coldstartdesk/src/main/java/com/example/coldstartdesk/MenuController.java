package com.example.coldstartdesk;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MenuController {
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtRole;

    @FXML
    private void onCadastrarClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro");
        alert.setHeaderText(null);
        alert.setContentText("Usuario cadastrado: " + txtNome.getText() + " - Email: " + txtEmail.getText() + " - Role: " + txtRole.getText());
        alert.showAndWait();
    }
}
