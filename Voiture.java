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
public class Voiture {
    int acceleration;
    String affichage;

    public Voiture() {
        Initialise_Voiture();
    }


    //Fonction permettant l'initialisation d'une voiture
    public void Initialise_Voiture() {
        acceleration= (int)(Math.random() * 6)+1;
        affichage= "<img src='images/voiture.png'width='20px' height='10px'>";
    }
}
