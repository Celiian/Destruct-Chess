package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void sousMenuRegle(){
        boolean fin = false;
        boolean continuer = true;
        while (continuer){


        boolean inputCorrect = true;
        int menu = 0;
        while (inputCorrect) {
            try {
                System.out.println("\n\nLe but du jeu est de bloquer l'autre joueur. Pour se faire, deux actions sont effectués tour à tour par les joueurs :\n" +
                    "1) Le joueur doit déplacer son pion, horizontalement ou verticalement \n" +
                    "2) Le joueur doit choisir une case du plateau à détruire");
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
        boolean fin = false;
        while (fin == false) {
            int menu = 0;
            boolean inputCorrect = true;


            System.out.println("Menu Principal : ");


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

                } else if (menu == 2) {

                } else if (menu == 3) {
                    sousMenuRegle();
                    inputCorrect = true;
                }
                else if (menu == 4) {
                    fin = true;
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


    public static void main(String[] args) {


        menuPrincipal();





    }
}
