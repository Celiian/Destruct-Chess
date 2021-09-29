package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);


    //Sous menu règle, permet de lire les règles du jeu avant de retourner au menu.
    public static void sousMenuRegle(){
        boolean fin = false;
        boolean continuer = true;
        while (continuer){
        boolean inputCorrect = true;
        int menu = 0;
        while (inputCorrect) {
            try {
                System.out.println("\n\nPendant son tour un joueur peut déplacer son pion d’une case (verticalement ou horizontalement), puis il détruit une case du plateau.\n" +
                        "Le dernier joueur pouvant encore se déplacer gagne.\n" +
                        "Contraintes :\n" +
                        "- Un joueur ne peut pas détruire une case occupée.\n" +
                        "- Un joueur ne peut pas occuper une case détruite ou une case déjà occupée.\n" +
                        "- Un joueur bloqué pendant un tour est déclaré perdant.\n");
                System.out.println("Retour : Tappez 3 ");
                menu = sc.nextInt();
                inputCorrect = false;
            }
            catch (InputMismatchException e) {
                sc.next();
            }
        }
        if (menu == 3){
            menuPrincipal();
            continuer = false;
        }
        else {
        }
        }
    }

    //Affichage du menu principal puis choix des options dde jeu
    public static void menuPrincipal() {
        String[][] tableau = tableZero();
        boolean fin = false;
        while (fin == false) {
            int menu = 0;
            boolean inputCorrect = true;
            System.out.println(" \n\nMenu Principal : ");
            while (menu != 4) {
                while (inputCorrect) {
                    try {
                        System.out.println("Selectionnez une option :");
                        System.out.println("1 : Nouveau jeu | 2 : Tableau des scores | 3 : Règles | 4 : Quitter");
                        menu = sc.nextInt();
                        inputCorrect = false;
                    } catch (InputMismatchException e) {
                        System.out.println(" \n Veuillez recommencer et choisir une option valide \n");
                        sc.next();
                    }
                }
                if (menu == 1) {
                    Jeu();
                    fin = true;
                    menu = 4;
                    inputCorrect = true;
                } else if (menu == 2) {

                } else if (menu == 3) {
                    sousMenuRegle();
                    inputCorrect = true;
                }
                else if (menu == 4) {
                    fin = true;
                    menu = 4;
                }
                else {
                    System.out.println(" \n Veuillez recommencer et choisir une option valide \n");
                    System.out.println("Selectionnez une option :");
                    System.out.println("1 : Nouveau jeu | 2 : Tableau des scores | 3 : Règles | 4 : Quitter");
                    menu = sc.nextInt();
                    break;
                }
            }
        }
    }


    //Permet d'afficher le tableau actuel
    public static void affichageTableau(String tableau[][]) {
        for (int j = 0; j < 11; j++) {
            System.out.print( j + 1 );
            System.out.print( "  " );
        }
        System.out.println("");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(tableau[i][j] + " " );
            }System.out.print(i + 1);
            System.out.println("");
        }
    }

    public static String[][] tableZero() {
        String[][] tableau = new String[10][11];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                if (i == 5 && j == 4){
                    tableau[i][j] = "\uD83D\uDFE6";
                }
                else if (i == 5 && j == 5) {
                    tableau[i][j] = "\uD83D\uDFE5";
                }
                else{
                    tableau[i][j] = "⬜";
                }
            }
        }
        return tableau;
    }


    public static void Jeu(){
        boolean jeuFinis = false;
        int joueur = joueurStart();
        String[][] tableau = tableZero();
        affichageTableau(tableau);

        while (jeuFinis == false) {
            deplacementJoueur(tableau, joueur);
            destructionCase(tableau);
           if (joueur == 1){
               joueur = 2;
           }
           else {
               joueur = 1;
           }
        }
    }



    public static int joueurStart(){
        boolean MeruemCommence = (Math.random() < 0.5);
        int joueur = 0;
        if (MeruemCommence) {
            joueur = 1;
            System.out.println("Meruem commence");
        } else {
            joueur = 2;
            System.out.println("Komugi commence");
        }
        return joueur;
    }


    public static void deplacementJoueur(String tableau[][],int joueur){


        String couleurJoueur;

        if (joueur == 1) {
            couleurJoueur = "🟦";

        }
        else {
            couleurJoueur = "\uD83D\uDFE5";

        }


        boolean deplacementFais = false;


        while (deplacementFais == false){
            System.out.println("Déplacez votre pion : ");
            System.out.println("Appuyez sur 1 pour aller a gauche");
            System.out.println("Appuyez sur 2 pour aller en haut");
            System.out.println("Appuyez sur 3 pour aller en bas");
            System.out.println("Appuyez sur 4 pour aller a droite");
            int deplacement = sc.nextInt();


            if(deplacement == 1) {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 11; j++) {
                        if (tableau[i][j] == couleurJoueur) {
                            tableau[i][j] = "⬜";
                            tableau[i][j - 1] = couleurJoueur;
                            deplacementFais = true;
                            j = 11;
                            i = 11;
                        }
                    }
                }
            }

            else if(deplacement == 2) {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 11; j++) {
                        if (tableau[i][j] == couleurJoueur) {
                            tableau[i][j] = "⬜";
                            tableau[i - 1][j] = couleurJoueur;
                            deplacementFais = true;
                            j = 11;
                            i = 11;
                        }
                    }
                }
            }

            else if(deplacement == 3) {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 11; j++) {
                        if (tableau[i][j] == couleurJoueur) {

                            if (tableau[i + 1][j] != "⬛" || tableau[i + 1][j] != ""){

                            }
                            tableau[i][j] = "⬜";
                            tableau[i + 1][j] = couleurJoueur;
                            deplacementFais = true;
                            j = 11;
                            i = 11;
                        }
                    }
                }
            }

            else if(deplacement == 4) {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 11; j++) {
                        if (tableau[i][j] == couleurJoueur) {
                            tableau[i][j] = "⬜";
                            tableau[i][j + 1] = couleurJoueur;
                            deplacementFais = true;
                            j = 11;
                            i = 11;
                        }
                    }
                }
            }
            else{
                    affichageTableau(tableau);
                    System.out.println("Veuillez entrer un entier valide");
            }
        }
    affichageTableau(tableau);
    }

    public static void destructionCase(String tableau[][]){
        System.out.println("Détruisez une case (Pour se faire, entrez les index des lignes et colonnes de la case voulue");
        System.out.println("Ligne de la case : ");
        int ligne = sc.nextInt();
        System.out.println("Colonne de la case : ");
        int colonne =  sc.nextInt();

        tableau[ligne -1][colonne -1] = "⬛";
        affichageTableau(tableau);

    }


    public static void main(String[] args) {
        menuPrincipal();
    }
}
