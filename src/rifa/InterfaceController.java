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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author brigido
 */
public class InterfaceController implements Initializable {

    @FXML
    private ComboBox<String> digitos;
    @FXML
    private Button generar;
    @FXML
    private TableView<Numeros> box;
    @FXML
    private TableColumn<Numeros, String> numero;

    private ObservableList<Numeros> list;
    private ObservableList<Numeros> list3;
    @FXML
    private TableColumn<Numeros, String> numero3;
    @FXML
    private TableColumn<Numeros, String> nombre3;
    @FXML
    private TableView<Numeros> box3;
    @FXML
    private TableColumn<Numeros, String> nombre;
    @FXML
    private Button guardar;
    @FXML
    private TextField name;
    @FXML
    private TextArea number;
    @FXML
    private Button insert;
    String jdbcURL = "jdbc:h2:~/rifa";
    String username = "sa";
    String password = "";
    @FXML
    private TextField name3;
    @FXML
    private TextArea number3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        digitos.setItems(FXCollections.observableArrayList("2", "3"));
        try {

            Connection connection = DriverManager.getConnection(jdbcURL, username, password);

            String sql = "CREATE TABLE IF NOT EXISTS RIFA2(ID VARCHAR(2) PRIMARY KEY, NOMBRE VARCHAR(50));\n"
                    + "CREATE TABLE IF NOT EXISTS RIFA3(ID VARCHAR(3) PRIMARY KEY, NOMBRE VARCHAR(50));";

            Statement statement = connection.createStatement();
            statement.execute(sql);
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            e.getLocalizedMessage();
        }
    }

    @FXML
    private void GenerarTabla(ActionEvent event) {

        String digit = digitos.getSelectionModel().getSelectedItem();
        if ("2".equals(digit)) {
            list = FXCollections.observableArrayList();
            Numeros.NumerosTabla2(list);
            numero.setCellValueFactory(
                    numero -> numero.getValue().PropertyNumero()
            );
            nombre.setCellValueFactory(
                    nombre -> nombre.getValue().PropertyNombre()
            );
            box.setItems(list);
            try {
                Connection conexion = DriverManager.getConnection(jdbcURL, username, password);
                String sql = "SELECT * FROM RIFA2";
                Statement statement = conexion.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                if (!resultSet.next()) {
                    //Connection conexion = DriverManager.getConnection(jdbcURL, username, password);
                    PreparedStatement instruccion = conexion.prepareStatement("INSERT INTO RIFA2 (ID, NOMBRE) VALUES (?,?)");
                    for (Numeros n : list) {
                        String num = n.getNumero();
                        String nom = n.getNombre();

                        instruccion.setString(1, num);
                        instruccion.setString(2, nom);
                        instruccion.executeUpdate();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Rifa");
                    alert.setHeaderText("Error al generar los numeros");
                    alert.setContentText("Ya existen datos de esta rifa \nEsto elmiminara todos los datos \nEsta seguro de continuar?");
                    Optional<ButtonType> action = alert.showAndWait();
                    if (action.get() == ButtonType.OK) {
                        String delete = "DELETE FROM RIFA2 WHERE ID BETWEEN '00' AND '99'";
                        statement.execute(delete);
                        PreparedStatement instruccion = conexion.prepareStatement("INSERT INTO RIFA2 (ID, NOMBRE) VALUES (?,?)");
                        for (Numeros n : list) {
                            String num = n.getNumero();
                            String nom = n.getNombre();

                            instruccion.setString(1, num);
                            instruccion.setString(2, nom);
                            instruccion.executeUpdate();
                        }
                        generar.setDisable(true);
                    }
                }
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
                e.getLocalizedMessage();
            }

        }
        if ("3".equals(digit)) {
            list = FXCollections.observableArrayList();
            Numeros.NumerosTabla3(list);
            numero3.setCellValueFactory(
                    new PropertyValueFactory<>("numero")
            );
            box3.setItems(list);
            generar.setDisable(true);
            try {
                Connection conexion = DriverManager.getConnection(jdbcURL, username, password);
                PreparedStatement instruccion = conexion.prepareStatement("INSERT INTO RIFA3 (ID, NOMBRE) VALUES (?,?)");
                for (Numeros n : list) {
                    String num = n.getNumero();
                    String nom = n.getNombre();

                    instruccion.setString(1, num);
                    instruccion.setString(2, nom);
                    instruccion.executeUpdate();

                }
                /*Numeros.guardarNumeros(conexion);
            int resultado = 
            String sql = "INSERT INTO RIFA VALUES("+Integer.parseInt(numero.getText())+","+nombre.getText().toString()+");";*/
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
                e.getLocalizedMessage();
            }

        }
        if (!"2".equals(digit) && !"3".equals(digit)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Rifa");
            alert.setHeaderText("Error al generar los numeros");
            alert.setContentText("No se ha seleccionado la cantidad de digitos");
            alert.showAndWait();
        }

    }

    @FXML
    private void guardas(ActionEvent event) {

    }

    @FXML
    private void InsertarTabla(ActionEvent event) throws SQLException {
        box.getItems().clear();
        Connection conexion = DriverManager.getConnection(jdbcURL, username, password);
        PreparedStatement instruccion = conexion.prepareStatement("UPDATE RIFA2 SET NOMBRE = ? WHERE ID = ?");
        String[] p = number.getText().split(",");
        for (int i = 0; i < p.length; i++) {    //length is the property of the array  
            instruccion.setString(1, name.getText());
            instruccion.setString(2, p[i]);
            instruccion.executeUpdate();
        }
        String sql = "SELECT * FROM RIFA2";
        Statement statement = conexion.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(
                    new Numeros(
                            rs.getString("ID"),
                            rs.getString("NOMBRE")
                    )
            );
        }
        box.setItems(list);
        numero.setCellValueFactory(
                numero -> numero.getValue().PropertyNumero()
        );
        nombre.setCellValueFactory(
                nombre -> nombre.getValue().PropertyNombre()
        );
        conexion.close();
    }

    private void InsertarTabla3(ActionEvent event) throws SQLException {
        box3.getItems().clear();
        Connection conexion = DriverManager.getConnection(jdbcURL, username, password);
        PreparedStatement instruccion = conexion.prepareStatement("UPDATE RIFA3 SET NOMBRE = ? WHERE ID = ?");
        String[] p = number.getText().split(",");
        for (int i = 0; i < p.length; i++) {    //length is the property of the array  
            instruccion.setString(1, name.getText());
            instruccion.setString(2, p[i]);
            instruccion.executeUpdate();
        }
        String sql = "SELECT * FROM RIFA3";
        Statement statement = conexion.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list3.add(
                    new Numeros(
                            rs.getString("ID"),
                            rs.getString("NOMBRE")
                    )
            );
        }
        box3.setItems(list3);
        numero3.setCellValueFactory(
                numero -> numero.getValue().PropertyNumero()
        );
        nombre3.setCellValueFactory(
                nombre -> nombre.getValue().PropertyNombre()
        );
        conexion.close();
    }

    @FXML
    private void CargarDatos(ActionEvent event) throws SQLException {
        list = FXCollections.observableArrayList();
        list3 = FXCollections.observableArrayList();
        boolean r1 = false;
        boolean r2 = false;
        Connection conexion = DriverManager.getConnection(jdbcURL, username, password);
        String sql = "SELECT * FROM RIFA2";
        String sql2 = "SELECT * FROM RIFA3";
        Statement statement = conexion.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        if (rs.isBeforeFirst()) {
            while (rs.next()) {
                list.add(
                        new Numeros(
                                rs.getString("ID"),
                                rs.getString("NOMBRE")
                        )
                );
            }
            box.setItems(list);
            numero.setCellValueFactory(
                    numero -> numero.getValue().PropertyNumero()
            );
            nombre.setCellValueFactory(
                    nombre -> nombre.getValue().PropertyNombre()
            );
            r1 = true;
        }
        statement = conexion.createStatement();
        ResultSet rs2 = statement.executeQuery(sql2);
        if (rs2.isBeforeFirst()) {
            list3.removeAll();
            while (rs2.next()) {
                list3.add(
                        new Numeros(
                                rs2.getString("ID"),
                                rs2.getString("NOMBRE")
                        )
                );
            }
            numero3.setCellValueFactory(
                    numero -> numero.getValue().PropertyNumero()
            );
            nombre3.setCellValueFactory(
                    nombre -> nombre.getValue().PropertyNombre()
            );
            box3.setItems(list3);

            r2 = true;
        }
        if (r1 == false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Rifa");
            alert.setHeaderText("Error rifa");
            alert.setContentText("No hay rifa de 2 digitos o esta vacia");
            alert.showAndWait();
        }
        if (r2 == false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Rifa");
            alert.setHeaderText("Error rifa");
            alert.setContentText("No hay rifa de 3 digitos o esta vacia");
            alert.showAndWait();
        }
        conexion.close();

    }

}
