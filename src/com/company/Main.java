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



    public static void affichageTableau(String tableau[][]) {
        for (int j = 0; j < 11; j++) {
            System.out.print( j );
            System.out.print( "  " );
        }
        System.out.println("");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(tableau[i][j] + " " );
            }System.out.print(i);
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
        String[][] tableau = tableZero();
        affichageTableau(tableau);


    }


    public static void deplacementJoueur(){
        System.out.println("Déplacez votre pion : ");
        System.out.println("Appuez sur 1 pour aller a gauche");
        System.out.println("Appuez sur 2 pour aller en haut");
        System.out.println("Appuez sur 3 pour aller en bas");
        System.out.println("Appuez sur 4 pour aller a droite");
        int déplacement = sc.nextInt();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                
            }
        }


    }

    public static void main(String[] args) {
        menuPrincipal();
    }
}
