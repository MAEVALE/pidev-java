/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.cliniq.gui;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import tn.cliniq.entities.Rdv;
import tn.cliniq.services.RdvService;
import tn.cliniq.tools.MaConnexion;

/**
 *
 * @author lengu
 */
public class RdvController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TableView<Rdv> tvrdv;
    @FXML
    private TableColumn<Rdv, String> colid;
    @FXML
    private TableColumn<Rdv, String> colnom;
    @FXML
    private TableColumn<Rdv, String> coldate;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private TextField tfid ;
    @FXML
    private TextField tfnom;
    @FXML
    private DatePicker dpdate;
    
    Connection connection = null;
    ObservableList<Rdv> rdv = FXCollections.observableArrayList();
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       viewrdv();
    }   
    
    private boolean Validchamp(TextField T){
        return !T.getText().isEmpty() && T.getLength() < 2;
        //return !T.getText().isEmpty();
    }
    
 

    @FXML
    private void add(ActionEvent event) {
        //int id_rdv = Integer.parseInt(tfid.getText());
        String nom_rdv = tfnom.getText();
        String date = dpdate.getValue().toString();
        
        
         if (nom_rdv.isEmpty()) {
    // Afficher un message d'erreur si un champ est vide
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez remplir tous les champs");
    alert.showAndWait();
    return ;
}
        


         
        Rdv r = new Rdv(0, nom_rdv, date);
        RdvService rs = new RdvService();
        rs.ajouterRdv(r);
        getRdv();
    }
        

    @FXML
    private void update(ActionEvent event) {
        int id_rdv = Integer.parseInt(tfid.getText());
        String nom_rdv = tfnom.getText();
        String date = dpdate.getValue().toString();
        
        Rdv r = new Rdv(id_rdv, nom_rdv, date);
        RdvService rs = new RdvService();
        rs.modifierRdv(r);
                getRdv();

    }

    @FXML
    private void delete(ActionEvent event) {
        int id_rdv = Integer.parseInt(tfid.getText());
        RdvService rs = new RdvService();
        rs.supprimerRdv(id_rdv);
                getRdv();
                

    }

    private void getRdv(){
        try {
            rdv.clear();
            
            String req = "SELECT * FROM `rdv`";
            Statement ste = connection.createStatement();
            ResultSet result = ste.executeQuery(req);

            while (result.next()) {
                Rdv resultRdv;
                resultRdv = new Rdv(
                        result.getInt("id"), 
                        result.getString("nom"), 
                        result.getString("date")); 
                rdv.add(resultRdv);
                tvrdv.setItems(rdv);
                
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void viewrdv(){
        connection = MaConnexion.getInstance().getCnx();
        getRdv();
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        tvrdv.setRowFactory(tv-> {
            TableRow<Rdv> row = new TableRow<>();
            row.setOnMouseClicked(even -> {
                if (even.getClickCount() == 1 && !row.isEmpty()){
                    int myIndex = tvrdv.getSelectionModel().getSelectedIndex();
                    tfid.setText(""+(tvrdv.getItems().get(myIndex).getId()));
                    tfnom.setText((tvrdv.getItems().get(myIndex).getNom()));
                    LocalDate date = LocalDate.parse(tvrdv.getItems().get(myIndex).getDate());
                    dpdate.setValue(date);
                }
            });
            return row;
        });
    }
}
        
       
