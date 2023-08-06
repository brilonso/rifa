/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package rifa;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author brigido
 */
public class InterfaceController implements Initializable {

    @FXML
    private ComboBox<String> digitos;
    @FXML
    private TextField inicio;
    @FXML
    private TextField fin;
    @FXML
    private Button generar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        digitos.setItems(FXCollections.observableArrayList("2 digitos", "3 digitos"));
    }

}
