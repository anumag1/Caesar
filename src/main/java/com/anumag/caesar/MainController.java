package com.anumag.caesar;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class MainController {

    @FXML
    TextArea textInputField;
    @FXML
    TextArea keyInputField;
    @FXML
    TextArea outputField;

    public void encrypt() {
        Caesar c = new Caesar();
        outputField.setText(c.encrypt(textInputField.getText(), Integer.parseInt(keyInputField.getText())));
    }

    public void decrypt() {
        Caesar c = new Caesar();
        outputField.setText(c.decrypt(textInputField.getText(), Integer.parseInt(keyInputField.getText())));
    }

    public void bruteForce() {
        Caesar c = new Caesar();
        outputField.setText(c.bruteForce(textInputField.getText()));
    }

    public void analysis() {
        Caesar c = new Caesar();
        outputField.setText(c.frequencyAnalysis(textInputField.getText()));
    }
}
