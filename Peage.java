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
public class Peage
{
    String affichage;
    int t_passage; // temps de passage en seconde

    public Peage() {
        Initialise_Peage();
    }
    
    
    //Fonction permettant l'initialisation d'une voiture
    public void Initialise_Peage() {
        t_passage = (int)(Math.random() * 6)+3;
        affichage= " P ";
    }

    public static void Temps_Actualise(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
        System.out.println("Une seconde s'est écoulée !");
    }
}

