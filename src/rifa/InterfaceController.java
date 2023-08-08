/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package rifa;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author brigido
 */
public class InterfaceController implements Initializable {

    @FXML
    private ComboBox<String> digitos;
    @FXML
    private TextField fin;
    @FXML
    private Button generar;
    @FXML
    private TableView<Numeros> box;
    @FXML
    private TableColumn<Numeros, String> numero;

    private ObservableList<Numeros> list;
    @FXML
    private TableColumn<Numeros, String> numero3;
    @FXML
    private TableColumn<Numeros, String> nombre3;
    @FXML
    private TableView<Numeros> box3;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        digitos.setItems(FXCollections.observableArrayList("2", "3"));
    }

    @FXML
    private void GenerarTabla(ActionEvent event) {
        generar.setDisable(true);
        String digit = digitos.getSelectionModel().getSelectedItem();
        if ("2".equals(digit)) {
            list = FXCollections.observableArrayList();
            Numeros.NumerosTabla2(list);
            numero.setCellValueFactory(
                new PropertyValueFactory<>("numero")
            );
            box.setItems(list);
        }if ("3".equals(digit)){
            list = FXCollections.observableArrayList();
            Numeros.NumerosTabla3(list);
            numero3.setCellValueFactory(
                new PropertyValueFactory<>("numero")
            );
            box3.setItems(list);
        } if (!"2".equals(digit) && !"3".equals(digit)) {   
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Rifa");
            alert.setHeaderText("Error al generar los numeros");
            alert.setContentText("No se ha seleccionado la cantidad de digitos");
            alert.showAndWait();
        }
        
    }

}
