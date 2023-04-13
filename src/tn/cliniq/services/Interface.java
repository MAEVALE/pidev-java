/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.cliniq.services;

import java.util.List;
import tn.cliniq.entities.Rdv;
/**
 *
 * @author lengu
 */
public interface Interface {
    public void ajouterRdv (Rdv r);
    public void modifierRdv(Rdv r);
    public void supprimerRdv(int id);
    public List <Rdv> afficherrdv();
}
