package com.ocr.jeanlouis34.escapegame;

import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Jeu {

    static Logger logger = Logger.getLogger(Jeu.class);
    private String nomJoueur;
    private int modeJeu;
    private int nbTours;
    private String ready;
    private int choixJeu;
    private int victoire;
    private int tourPartie;
    private String ready1;
    private String modeDeveloper;

    public Jeu() {
        this("",0,0,"",0, 0, 0, "", "");
    }

    public Jeu(String nomJoueur, int modeJeu, int nbTours, String ready, int choixJeu, int victoire, int tourPartie, String ready1, String modeDeveloper) {
        this.nomJoueur = nomJoueur;
        this.modeJeu = modeJeu;
        this.nbTours = nbTours;
        this.ready = ready;
        this.choixJeu = choixJeu;
        this.victoire = victoire;
        this.tourPartie = tourPartie;
        this.ready1 = ready1;
        this.modeDeveloper = modeDeveloper;
    }

    /**
     * This abstract method is only taken over in the sub classes of the Class Jeu that is JeuChallenger, JeuDefenseur & JeuDuel
     */

    public void runJeu() {
        logger.info("Je choisis le mode Jeu de ma partie entre les 3 modes possibles = Challenger, Defenseur ou Duel");
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    /**
     * This method enables to scan the name of the Human Player
     * This method is run in the Main Class
     */

    public void addNomJoueur() {
        Scanner sc = new Scanner(System.in);
        logger.info("Bonjour à toi! \nBienvenue dans ce nouvel Escape Game. Dis moi, quel est ton nom ?");
        this.nomJoueur = sc.nextLine();
    }

    public void printNomJoueur() {
        logger.info("C'est noté. Je te remercie" + this.nomJoueur + "! \n ");
        logger.info("Dans ce jeu, le Player Machine ou toi, vous devrez découvrir une combinaison secrète de plusieurs chiffres.");
        logger.info("Entre le Player Machine et toi, à la fin, il ne peut en rester qu'un.");
    }

    public int getModeJeu() { return modeJeu;
    }

    /**
     * This is a sub-method of the method of this Class runModeJeu
     * This method enables to scan the number of the choosen game pattern
     *
     * Inputmismatch Exception if the human player answer with a letter, a word or a negative number.
     * If he writes a number > 10, only the first int is scanned.
     */

    public void addModeJeu () {
        Scanner sc = new Scanner(System.in).useDelimiter(" *");
        try {
            logger.info("Attention, l'ordinateur ne retiendra que le premier chiffre que tu saisiras.");
            logger.info("Je te rappelle que tu ne peux saisir que 3 choix : 1 pour CHALLENGER, 2 pour DEFENSEUR, 3 pour DUEL !");
            this.modeJeu = sc.nextInt();
        } catch (InputMismatchException ex) {
            logger.error(" La saisie n'est pas correcte. On recommence à zéro ...");
            this.modeJeu = 0;
            sc.next();
            addModeJeu();
        }
    }

    public int getNbTours() {
        return nbTours;
    }

    public void setNbTours(int nbTours) {
        this.nbTours = nbTours;
    }

    /**
     * This method enables to define the number of rounds for each of both patterns JeuChallenger and JeuDefenseur
     * The pattern JeuDuel is not intended
     *
     * Inputmismatch Exception if the human player answer with a letter, a word or a negative number.
     * If he writes a number > 10, only the first int is scanned.
     *
     */

    public void addNbTours () {
        Scanner sc = new Scanner(System.in).useDelimiter(" *");
        try {
            logger.info("Tu peux choisir jusqu'à 9 tours. \nAttention, pense bien à entrer un chiffre positif ...");
            this.nbTours = sc.nextInt();
        } catch (InputMismatchException ex) {
            logger.error("La saisie n'est pas correcte. On recommence à zéro ...");
            nbTours = 0;
            sc.next();
            addNbTours();
        }
    }

    public void printNbTours() {
        if (nbTours <= 9) {
            logger.info("Tu as choisi de jouer ta partie en " + this.nbTours + "\nOn continue ...");
        } else {
            logger.info("Ta saisie n'est pas correcte. Je décide donc de fixer le nombre de tours à 5.");
            this.nbTours = 5;
        }
    }

    /**
     * This is a sub-method of the method of this Class runModeJeu
     */

    public void displayAvailableModeJeu(){
        logger.info ("\nVoici les modes de Jeu qui s'offrent à toi :");
        logger.info("Choix N°1 - Mode CHALLENGER : c'est le Player Machine qui choisit la combinaison. A toi de la découvrir en un nombre de coups limités");
        logger.info("Choix N°2 - Mode DEFENSEUR : c'est toi qui choisit la combinaison et le Player Machine qui attaque dans un nombre de coups limités");
        logger.info("Choix N°3 - Mode DUEL : le Player Machine et toi vous choisissez vos combinaisons et attaquez à tour de rôle");
        logger.info("\nAlors dis moi" + this.getNomJoueur() + ", quelle est ta décision ? Ecris ici le numéro de ton choix :");
    }

    public String getModeDeveloper() {
        return modeDeveloper;
    }

    /**
            * This is a sub-method of the displaySeletedModeJeu which is a sub-method of the Class runModeJeu
     */

    public void modeDeveloper() {
        Scanner sc = new Scanner(System.in);
        logger.info("\nPour tester toutes les capacités du jeu, tu as la possibilité de jouer en mode Développeur. \nEn mode Développeur, la combinaison secrète est dévoilée au joueur attaquant ...");
        logger.info("Si tu choisis le mode Développeur, tape OUI :");
        this.modeDeveloper = sc.nextLine();
        logger.info("C'est noté. Je te remercie " + this.nomJoueur + "! ");
    }

    /**
     * This is a sub-method of the method of this Class runModeJeu
     */

    public void displaySelectedModeJeu() {
        switch (modeJeu) {
            case 1:
                logger.info("Tu as choisi de jouer en mode CHALLENGER. A toi de trouver la combinaison secrète. Que la Force soit avec toi !");
                modeDeveloper();
                logger.info("\nEn combien de tours veux-tu jouer cette partie ?");
                addNbTours();
                printNbTours();
                break;
            case 2:
                logger.info("Tu as choisi de jouer en mode DEFENSEUR. Tous mes voeux pour que ta combinaison secrète ne soit pas découverte !");
                modeDeveloper();
                logger.info("\nEn combien de tours veux-tu jouer cette partie ?");
                addNbTours();
                printNbTours();
                break;
            case 3:
                logger.info("Tu as choisi de jouer en mode DUEL. On va alterner les modes Challenger et Défenseur à tour de rôle jusqu'à ce que l'un des joueurs l'emporte.");
                nbTours = 100;
                modeDeveloper();
                break;
            default:
                logger.info("Ne réponds pas n'importe quoi pour éviter le combat. Pour que tu comprennes bien, bis repetita ...");
                runModeJeu();
                break;
        }
    }

    /**
     * This method enables to scan the pattern choosen by the Player and to display the Rules and the results
     * This method is run in the Main ClassThis method
     */

    public void runModeJeu() {
        this.displayAvailableModeJeu();
        addModeJeu();
        this.displaySelectedModeJeu();
    }

    public String getReady() {
        return ready;
    }

    /**
     * The lone method of the class
     * This method enables to choose one more time between the three operative patterns.
     */

    public void finirlapartie() {
        logger.info("La précédente partie est finie. \nQue veux tu faire maintenant ?");
        logger.info("Si tu veux continuer à jouer, réponds OUI : ");
        String OUI = "OUI";
        Scanner sc = new Scanner(System.in);
        try {
            ready = sc.nextLine();
        } catch (InputMismatchException e) {
            logger.info("La saisie n'est pas correcte. Il faut recommencer ...");
            sc.next();
            ready = sc.nextLine();
        }
        if (ready.equals(OUI)) {
            logger.info("Quel mode de jeu souhaites-tu jouer ? Un patit rappel.");
            runModeJeu();
            switch (modeJeu) {
                case 1:
                    choixJeu = 1;
                    break;
                case 2:
                    choixJeu = 2;
                    break;
                case 3:
                    choixJeu = 3;
                    break;
                default:
                    logger.info("Ne réponds pas n'importe quoi pour éviter le combat. Pour que tu comprennes bien, bis repetita ...");
                    finirlapartie();
                    break;
            }
        } else {
            logger.info("Tu as choisi de quitter l'application. J'espère te revoir bientôt et te souhaite une bonne journée " + nomJoueur);
            System.exit(0);
        }
    }
}



