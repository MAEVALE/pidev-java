/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import tn.cliniq.entities.Rdv;
import tn.cliniq.services.RdvService;


/**
 *
 * @author lengu
 */
public class main {
    public static void main(String[] args) {
        //Rdv r = new Rdv();
        Rdv r = new Rdv(0,"Joyce","2020-02-03");
        RdvService rs = new RdvService();
        
        //rs.ajouterRdv(r);
       // System.out.println(r.toString());
        //rs.supprimerRdv(2);
      //  rs.afficherrdv();
       // System.out.println(ps.afficher());
    }
}
