package com.company;

import com.sun.source.tree.ReturnTree;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    int historiqueIndex = 0;

    //Sous menu règle, permet de lire les règles du jeu avant de retourner au menu.
    public static void sousMenuRegle(){
        System.out.println("\n\nPendant son tour un joueur peut déplacer son pion d’une case (verticalement ou horizontalement), puis il détruit une case du plateau.\n" +
                "Le dernier joueur pouvant encore se déplacer gagne.\n" +
                "Contraintes :\n" +
                "- Un joueur ne peut pas détruire une case occupée.\n" +
                "- Un joueur ne peut pas occuper une case détruite ou une case déjà occupée.\n" +
                "- Un joueur bloqué pendant un tour est déclaré perdant.\n");
    }

    //Affichage du menu principal puis choix des options dde jeu
    public static int menuPrincipal(boolean enJeu) {
        String[] pseudos;
        int finDeJeu = 0;
        String [] historique= new String[10];
        int historiqueIndex = 0;
        String [] historiqueDePartie = new String[10];
        for (int i = 0; i < 10; i++){
            historiqueDePartie[i] = "";
        }
        boolean rejouer = true;
        while (rejouer == true){
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
                            if (enJeu){
                                System.out.println("Pour reprendre votre partie appuez sur 5");
                            }
                            menu = sc.nextInt();
                            inputCorrect = false;
                        } catch (InputMismatchException e) {
                            System.out.println(" \n Veuillez recommencer et choisir une option valide \n");
                            sc.next();
                        }
                    }
                    if (menu == 1) {
                        pseudos = pseudo();
                        int replay = 0;
                        String[][] tableau = tableZero();
                        int victoire = Jeu(pseudos);
                        if (victoire != 0){
                            historiqueDePartie = historiquePartie(victoire, historique, historiqueIndex, pseudos);
                        }
                        fin = true;
                        menu = 4;
                        inputCorrect = true;
                        if(victoire != 0){
                            System.out.println("Voulez vous rejouer ?");
                            System.out.println("1 : OUI  |  2 : NON ");
                            replay = sc.nextInt();
                        }
                        if (replay == 2){
                            rejouer = false;
                        }
                    } else if (menu == 2) {
                        boolean historiqueConsulte = false;
                        while (historiqueConsulte == false) {
                            int count = 0;
                            for (int i = 0; i < 10; i++) {
                                if (historiqueDePartie[i] == "") {
                                    count++;
                                }
                            }
                            if (count == 10) {
                                System.out.println("L'historique est vide vous devez commencer une partie");
                                historiqueConsulte = true;
                                menu = 0;
                                continue;
                            } else {
                                for (int i = 0; i < 10; i++) {
                                    System.out.println("Partie " + i + " : " + historiqueDePartie[i]);
                                    historiqueConsulte = true;
                                    menu = 0;
                                    continue;
                                }
                            }
                        }
                    } else if (menu == 3) {
                        sousMenuRegle();
                        inputCorrect = true;
                    }
                    else if (menu == 4) {
                        finDeJeu = 1;
                        rejouer = false;
                        fin = true;
                        menu = 4;
                        continue;
                    }
                    else if (menu == 0){
                        System.out.println("Selectionnez une option :");
                        System.out.println("1 : Nouveau jeu | 2 : Tableau des scores | 3 : Règles | 4 : Quitter");
                        if (enJeu){
                            System.out.println("Pour reprendre votre partie appuez sur 5");
                        }
                        menu = sc.nextInt();
                    }
                    else if(enJeu && menu == 5){
                        rejouer = false;
                        fin = true;
                        menu = 4;
                        continue;
                    }
                    else {
                        System.out.println(" \n Veuillez recommencer et choisir une option valide \n");
                        break;
                    }
                }
            }
            historiqueIndex ++;
        }
        return finDeJeu;
    }

    //Permet d'afficher le tableau actuel
    public static void affichageTableau(String tableau[][]) {

        System.out.print("1️⃣ "); System.out.print("2️⃣ "); System.out.print("3️⃣ ");System.out.print("4️⃣ ");System.out.print("5️⃣ ");System.out.print("6️⃣ ");
        System.out.print("7️⃣ ");System.out.print("8️⃣ ");System.out.print("9️⃣ ");System.out.print("\uD83D\uDD1F ");System.out.print("11 ");
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

    public static String[] historiquePartie(int victoire, String[] historique, int historiqueIndex, String[] pseudo){
        if (victoire == 1){
            historique[historiqueIndex] = pseudo[0] + " à gagné !";
        }
        else {
            historique[historiqueIndex] = pseudo[1] + " à gagné !!";
        }
        return historique;
    }

    public static int Jeu( String[] pseudo) {
        boolean jeuFinis = false;
        int joueur = joueurStart();
        String[][] tableau = tableZero();
        affichageTableau(tableau);
        int victoire = verificationVictoire(tableau, joueur);

        while (jeuFinis == false) {

            victoire = verificationVictoire(tableau, joueur);
            if (victoire == 1) {
                System.out.println(pseudo[0] + " à gagné !!");
                break;
            }
            else if (victoire == 2) {
                System.out.println(pseudo[1] + " à gagné !!");
                break;
            }

            int fin = deplacementJoueur(tableau, joueur, pseudo);

            if (fin == 1){
                jeuFinis = true;
                break;
            }

            destructionCase(tableau);

            if (joueur == 1) {
                joueur = 2;
            } else {
                joueur = 1;
            }
        }
        return victoire;
    }

    public static int joueurStart(){
        boolean MeruemCommence = (Math.random() < 0.5);
        int joueur = 0;
        if (MeruemCommence) {
            joueur = 1;
        } else {
            joueur = 2;
        }
        return joueur;
    }

    public static String[] pseudo() {
//        Attribution des pseudos
        int a = 0;
        System.out.println("Joueur 1 Veuillez choisir votre pseudo pour cette partie !");
        String[] pseudos = new String[2];
        boolean goodPseudo= false;
        while (goodPseudo == false ){
            pseudos[0] = sc.nextLine();
            a = pseudos[0].length();
            if (a > 2 && a <= 10) {
                goodPseudo = true;
            }
            else {
                System.out.println("Veuillez entrer un pseudo d'au moins deux characteres et de maximum dix characteres");
                goodPseudo = false;
            }
        }

        System.out.println("Joueur 2 Veuillez choisir votre pseudo pour cette partie !");
        goodPseudo= false;
        while (goodPseudo == false ){
            pseudos[1] = sc.nextLine();
            a = pseudos[1].length();
            if (a > 2 && a <= 10) {
                goodPseudo = true;
            }
            else {
                System.out.println("Veuillez entrer un pseudo d'au moins deux characteres et de maximum dix characteres");
                goodPseudo = false;
            }
        }return pseudos;
    }

    public static int deplacementJoueur(String[][] tableau, int joueur, String[] pseudo){
        int finDeJeu = 0;
        String nickname;
        String couleurJoueur;
        String couleurAutreJoueur;
        if (joueur == 1) {
            couleurJoueur = "🟦";
            couleurAutreJoueur = "\uD83D\uDFE5";
            nickname = pseudo[0];
        }
        else {
            couleurJoueur = "\uD83D\uDFE5";
            couleurAutreJoueur = "🟦";
            nickname = pseudo[1];

        }
        boolean deplacementFais = false;
        boolean deplacementValide = false;
        boolean inputCorrect = true;
        int deplacement= 0;
        while (deplacementFais == false){
            while (deplacementValide == false){

                while (inputCorrect) {
                    try {
                        System.out.println(nickname + ", déplacez votre pion : ");
                        System.out.println("Appuyez sur 1 pour aller a gauche");
                        System.out.println("Appuyez sur 2 pour aller en haut");
                        System.out.println("Appuyez sur 3 pour aller en bas");
                        System.out.println("Appuyez sur 4 pour aller a droite");
                        System.out.println("Pour consultez les règles, appuyez sur 6");
                        System.out.println("Pour retourner au menu appuyez sur 9");
                        deplacement = sc.nextInt();
                        inputCorrect = false;
                    } catch (InputMismatchException e) {
                        System.out.println("");
                        System.out.println( nickname + "Enfin... il faut choisir une option valide \n");
                        sc.next();
                    }
                }
                if(deplacement == 1) {
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 11; j++) {
                            if (tableau[i][j] == couleurJoueur) {
                                if (tableau[i][j - 1] == "⬜") {
                                    tableau[i][j] = "⬜";
                                    tableau[i][j - 1] = couleurJoueur;
                                    deplacementFais = true;
                                    deplacementValide = true;
                                    j = 11;
                                    i = 11;
                                }else {
                                    System.out.println(nickname + " je dois encore te rappeler les règles !? Il est INTERDIT de se déplacer sur une case DETRUITE ou OCCUPE par l'autre joueur");
                                    affichageTableau(tableau);
                                    inputCorrect = true;
                                }
                            }
                        }
                    }
                }
                else if(deplacement == 2) {
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 11; j++) {
                            if (tableau[i][j] == couleurJoueur) {
                                if (tableau[i - 1][j] == "⬜") {
                                    tableau[i][j] = "⬜";
                                    tableau[i - 1][j] = couleurJoueur;
                                    deplacementFais = true;
                                    deplacementValide = true;
                                    j = 11;
                                    i = 11;
                                }else {
                                    System.out.println("RAPPEL : Il est interdit de se déplacer sur une case détruite ou occupé par l'autre joueur");
                                    affichageTableau(tableau);
                                    inputCorrect = true;
                                }
                            }
                        }
                    }
                }
                else if(deplacement == 3) {
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 11; j++) {
                            if (tableau[i][j] == couleurJoueur) {
                                if (tableau[i + 1][j] == "⬜"){
                                    tableau[i][j] = "⬜";
                                    tableau[i + 1][j] = couleurJoueur;
                                    deplacementFais = true;
                                    deplacementValide = true;
                                    j = 11;
                                    i = 11;
                                }else {
                                    System.out.println("RAPPEL : Il est interdit de se déplacer sur une case détruite ou occupé par l'autre joueur");
                                    affichageTableau(tableau);
                                    inputCorrect = true;
                                }

                            }
                        }
                    }
                }
                else if(deplacement == 4) {
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 11; j++) {
                            if (tableau[i][j] == couleurJoueur) {
                                if (tableau[i][j + 1] == "⬜"){
                                    tableau[i][j] = "⬜";
                                    tableau[i][j + 1] = couleurJoueur;
                                    deplacementFais = true;
                                    deplacementValide = true;
                                    j = 11;
                                    i = 11;
                                }
                                else {
                                    System.out.println("RAPPEL : Il est interdit de se déplacer sur une case détruite ou occupé par l'autre joueur");
                                    affichageTableau(tableau);
                                    inputCorrect = true;
                                }
                            }
                        }
                    }
                }
                else if (deplacement == 6){
                    sousMenuRegle();
                    affichageTableau(tableau);
                    inputCorrect = true;
                    continue;
                }
                else if (deplacement == 9){
                    boolean enJeu = true;
                    finDeJeu = menuPrincipal(enJeu);
                    affichageTableau(tableau);
                    inputCorrect = true;
                    continue;
                }
                else{
                    affichageTableau(tableau);
                    System.out.println("Veuillez entrer un entier valide");
                }
                if (finDeJeu == 1){
                    inputCorrect = true;
                    deplacementValide = true;
                    deplacementFais = true;

                    break;
            }
        }
        }
    affichageTableau(tableau);
        return finDeJeu;
    }

    public static void destructionCase(String tableau[][]){
        /**Permet au joueur de choisir une case à détruire */
        boolean destructionValide = false;
        boolean inputCorrect = false;
        int ligne = 0;
        int colonne = 0;
        while(destructionValide == false){
            System.out.println("Détruisez une case (Pour se faire, entrez les index des lignes et colonnes de la case voulue");
            while (inputCorrect == false) {
                try {
                    System.out.println("Ligne de la case : ");
                    ligne = sc.nextInt();
                    inputCorrect = true;
                } catch (InputMismatchException e) {
                    System.out.println(" \n Veuillez recommencer et choisir une option valide \n");
                    sc.next();
                }
            }
            inputCorrect = false;
            while (inputCorrect == false) {
                try {
                    System.out.println("Colonne de la case : ");
                    colonne = sc.nextInt();
                    inputCorrect = true;
                } catch (InputMismatchException e) {
                    System.out.println(" \n Veuillez recommencer et choisir une option valide \n");
                    sc.next();
                }
            }

            if(tableau[ligne -1][colonne -1] == "⬜") {
                tableau[ligne - 1][colonne - 1] = "⬛";
                affichageTableau(tableau);
                destructionValide = true;
            }else {
                System.out.println("RAPPEL : Il est interdit de détruire une case occupé par un joueur ou déja détruite");
                affichageTableau(tableau);
            }
        }
    }

    public static int verificationVictoire(String tableau[][], int joueur) {
        int victoire = 0;
        String couleurJoueur;
        String couleurAutreJoueur;
        int ligne = 0;
        int colonne = 0;

        if (joueur == 1) {
            couleurJoueur = "🟦";
            couleurAutreJoueur = "\uD83D\uDFE5";
        } else {
            couleurJoueur = "\uD83D\uDFE5";
            couleurAutreJoueur = "🟦";
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                if (tableau[i][j] == couleurJoueur) {
                    ligne = i;
                    colonne = j;
                }
            }
        }
        if ((tableau[ligne + 1][colonne] == "⬛" || tableau[ligne + 1][colonne - 1] == couleurAutreJoueur) && (tableau[ligne - 1][colonne] == "⬛" || tableau[ligne + 1][colonne - 1] == couleurAutreJoueur) &&
                (tableau[ligne][colonne + 1] == "⬛" || tableau[ligne][colonne + 1] == couleurAutreJoueur) && (tableau[ligne][colonne - 1] == "⬛" || tableau[ligne][colonne - 1] == couleurAutreJoueur)) {

                if (joueur == 1){
                    victoire = 2;
                }
                else {
                    victoire = 1;
                }
        }
        return victoire;
    }

    public static void main(String[] args) {
        boolean enJeu = false;
        menuPrincipal(enJeu);
    }
}
