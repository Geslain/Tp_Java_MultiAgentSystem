/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tp_note;

import java.io.IOException;

/**
 *
 * @author Trusty
 */
public class Grille extends Thread
{
    // déclaration des propriétés de la voiture
    boolean occupe;
    int taille_l=10; //position sur la grille en hauteur
    int taille_h=20; //position sur la grille en largeur
    Pieces[][] grille = new Pieces[taille_h][taille_l];
    int l_peage=taille_h/2;

    public Grille(){
        Initialise_Grille();
        Affiche_Grille();
        Maj_Grille();
        Affiche_Grille();
    }

    //Fonction permettant l'initialisation d'une voiture
    public void Initialise_Grille() {
        occupe = false;
        for (int i = 0; i<taille_h; i++) {
            for (int j = 0; j<taille_l; j++) {
                grille[i][j] = new Pieces();
                if(i==l_peage && j!=taille_l-1 && j!=0){
                    grille[i][j].affichage="P";
                }
                else if(j==0 || j==taille_l-1){
                    grille[i][j].affichage="|";
                }
                
                else{
                    grille[i][j].affichage="R";
                    grille[i][j].acceleration_attente=0;
                }
            }
        }
    }
    public void Affiche_Grille(){
        for (int i = 0; i<taille_h; i++) {
            for (int j = 0; j<taille_l; j++) {
                //System.out.print('[' + grille[i][j].affichage + grille[i][j].acceleration_attente + ']');
                System.out.print(" " + grille[i][j].affichage + " ");
            }
            System.out.println();
        }
        Temps_Actualise(30);
    }
    
    public void Maj_Acceleration(){
        for (int i = 0; i<taille_h; i++) {
            for (int j = 0; j<taille_l; j++) {
                if(grille[i][j].affichage=="V" && i>0 ){
                    if(grille[i-1][j].affichage!="P")
                        grille[i][j].acceleration_attente=(int)(Math.random() * 6)+1;
                }
            }
        }
    }
    
    public void Maj_Grille(){
        grille[taille_h-1][5].affichage="V";
        Create_Voiture(0);
        for (int i = taille_h-1; i>=0; i--) {
            for (int j = 0; j<taille_l; j++) {
                if (grille[i][j].affichage=="V" && grille[i][j].acceleration_attente!=0){
                    //s=Obstacle_Devant(i, j);
                   /* if (s!=0){
                        Voiture_Devant_Obstacle(s, i, j);
                    }
                    else{*/
                        Avancer_Voiture(i,j);
                   // }
                }
                else if(grille[i][j].acceleration_attente==0 && i==l_peage+1 && grille[i][j].affichage=="V"){
                    Avancer_2_case(j);
                }
            }
            System.out.println();
            Affiche_Grille();
        }
        Maj_Acceleration();
        
        
    }
    
    public void Create_Voiture(int x){
        int s=0;
        for (int j=1;j<taille_l-1;j++){
            s=(int)(Math.random() * 100)+1;
            switch (x){
                case 0 : 
                    if (s < 80){
                        grille[taille_h-1][j].affichage="V";
                        grille[taille_h-1][j].acceleration_attente=(int) (Math.random() * 6)+2;
                    }
                case 1 :
                    if (s < 50){
                        grille[taille_h-1][j].affichage="V";
                        grille[taille_h-1][j].acceleration_attente=(int) (Math.random() * 6)+2;
                    };
                
                case 2 : 
                    if (s < 20){
                        grille[taille_h-1][j].affichage="V";
                        grille[taille_h-1][j].acceleration_attente=(int) (Math.random() * 6)+2;
                    };
            }
        }
    }
    
 /*   public int Obstacle_Devant(int i, int j){
        for (int x=i ; x>=i-grille[i][j].acceleration_attente;x--){
            if (grille[x][j].affichage=="V" || grille[x][j].affichage=="P"){
                if(grille[x][j].affichage=="R")
                    return (x+1);
            }
        }
        return 0;
    }*/
    
    public void Avancer_Voiture(int i, int j){
        if(i>=1){
            if(grille[i][j].acceleration_attente!=0 && grille[i-1][j].affichage=="R"){
                Avancer_1_case(i, j);
            }
            else if(grille[i][j].acceleration_attente!=0 && grille[i-1][j].affichage=="P"){
                grille[i][j].acceleration_attente=0;
                //Avancer_2_case(i, j);
            }
            else if(grille[i][j].acceleration_attente!=0 && grille[i-1][j].affichage=="V"){
                if (grille[i-1][j].acceleration_attente!=0 && grille[i-2][j].affichage=="R"){
                    Avancer_1_case(i-1, j);
                    grille[i-1][j].acceleration_attente=grille[i-1][j].acceleration_attente-1;
                }
            }
        }
        else {
            grille[i][j].acceleration_attente=0;
            grille[i][j].affichage="R";
        }
    }
    
    public boolean Voiture_Devant_Obstacle(int i, int j){
        if(grille[i-1][j].affichage!="R"){
            return false;
        }
        return true;
    }
    
    public void Avancer_1_case(int i, int j){
        grille[i-1][j].affichage=grille[i][j].affichage;
        grille[i][j].affichage="R";
        if(grille[i][j].acceleration_attente!=0){
            grille[i-1][j].acceleration_attente=grille[i][j].acceleration_attente-1;
            grille[i][j].acceleration_attente=0;
        }
        
    }
    
    public void Avancer_2_case(int j){ 
        int s=(int)(Math.random() * 100)+1;
        s=40;
        if(grille[l_peage+1][j].affichage=="V" && s < 50){
            grille[l_peage-1][j].affichage=grille[l_peage+1][j].affichage;
            grille[l_peage+1][j].affichage="R";
            grille[l_peage-1][j].acceleration_attente=(int)(Math.random() * 6)+1;
            for (int i=l_peage;i<taille_h;i++){
                if(Voiture_Devant_Obstacle(i, j))
                Avancer_1_case(i,j);
            }
        }
        
    }
    
    public static void Temps_Actualise(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
        System.out.println("Une seconde s'est écoulée !");
    }
    

}

