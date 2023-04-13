/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.cliniq.services;
import java.sql.*;
import java.util.*;
import tn.cliniq.tools.MaConnexion;
import java.sql.Connection;
import tn.cliniq.entities.Rdv;


/**
 *
 * @author lengu
 */
public class RdvService implements Interface{
    Statement ste;
    Connection cnx = MaConnexion.getInstance().getCnx();
   // String sql="";

    @Override
    public void ajouterRdv(Rdv r) {
        // TODO Auto-generated method stub
        try {
            ste = cnx.createStatement();
            String req = "Insert into rdv values(0,'"
                    + r.getNom() + "','"
                    + r.getDate() + "')";
            ste.executeUpdate(req);
            System.out.println("rendez-vous pris");
        } catch (SQLException ex) {
            System.out.println("Echec !!!!");
        }
    }

    @Override
    public void modifierRdv(Rdv r) {
        // TODO Auto-generated method stub
        try {
            String req = "UPDATE `rdv` SET "
                    + "`nom` = '" + r.getNom() + "', "
                    + "`date` = '" + r.getDate() + "'"
                    + "WHERE `rdv`.`id` = " + r.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("rdv updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerRdv(int id) {
        // TODO Auto-generated method stub
        try {
            String req = "DELETE FROM `rdv` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Rdv deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Rdv> afficherrdv() {
        // TODO Auto-generated method stub

        List<Rdv> pers = new ArrayList<Rdv>();
        try {
            String req = "SELECT * FROM `rdv`";
            Statement ste = cnx.createStatement();
            ResultSet result = ste.executeQuery(req);

            while (result.next()) {
                Rdv resultRdv;
                resultRdv = new Rdv(
                        result.getInt("id"), 
                        result.getString("nom"), 
                        result.getString("date")); 
                pers.add(resultRdv);
            }
            System.out.println(pers);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return pers;
    }

}
