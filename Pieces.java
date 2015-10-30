/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tp_note;

/**
 *
 * @author Trusty
 */
public class Pieces {
    int acceleration_attente; 
    String affichage;

    public Pieces() {
        Initialise_Piece();
    }

    //Fonction permettant l'initialisation d'une voiture
    public void Initialise_Piece() {
        acceleration_attente= (int)(Math.random() * 6)+1;
        affichage= " ";
    }
    public boolean Decompte_de_temps() {
        
        if (acceleration_attente!=0){
            Temps_Actualise(acceleration_attente*1000);
        }
        return true;
    }
    
    public static void Temps_Actualise(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
        System.out.println("Une seconde s'est écoulée !");
    }
    
    
}
