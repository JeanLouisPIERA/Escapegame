package com.ocr.jeanlouis34.escapegame;

import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class defines the characteristics of the whole game :
 * the name of the human player known as Player Joueur or the player (opposed to the Machine or Player Machine)
 *
 * the 3 different game patterns that is :
 * Challenger pattern: the Player has to find out the combinaison created by the Machine,
 * Defenseur pattern : the Machine has to find out the combinaison created by the Player,
 * Duel pattern : both other patterns are played each one after the other, beginning with the Challenge pattern
 *
 * the number of rounds of each pattern
 * the Duel pattern is not limited but by a maximum number of 100 rounds, in which the round of each other pattern is 1
 *
 * This class objects are instanced in classes JeuChallenger, JeuDefenseur, JeuDuel, JeuEnd.
 * Only this 5 classes of the first range are invoked in the Class main.
 */

public class JeuBegin {

    private String nomJoueur;
    private int modeJeu;
    private int nbTours;
    static Logger logger = Logger.getLogger(JeuBegin.class);

    public JeuBegin() {
        nomJoueur = "";
        modeJeu = 0;
        nbTours = 0;
    }

    public JeuBegin(String nomJoueur, int modeJeu, int nbTours) {
        this.nomJoueur = nomJoueur;
        this.modeJeu = modeJeu;
        this.nbTours = nbTours;
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
            if (nbTours <= 9) {
                logger.info("Tu as choisi de jouer ta partie en " + this.nbTours + "\nOn continue ...");
            } else {
                logger.info("Ta saisie n'est pas correcte. Je décide donc de fixer le nombre de tours à 5.");
                this.nbTours = 5;
            }
        } catch (InputMismatchException ex) {
            logger.error("La saisie n'est pas correcte. On recommence à zéro ...");
            nbTours = 0;
            sc.next();
            addNbTours();
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

    /**
     * This is a sub-method of the method of this Class runModeJeu
     */

    public void displaySelectedModeJeu() {
        switch (modeJeu) {
            case 1:
                logger.info("Tu as choisi de jouer en mode CHALLENGER. A toi de trouver la combinaison secrète. Que la Force soit avec toi !");
                logger.info("\nEn combien de tours veux-tu jouer cette partie ?");
                addNbTours();
                break;
            case 2:
                logger.info("Tu as choisi de jouer en mode DEFENSEUR. Tous mes voeux pour que ta combinaison secrète ne soit pas découverte !");
                logger.info("\nEn combien de tours veux-tu jouer cette partie ?");
                addNbTours();
                break;
            case 3:
                logger.info("Tu as choisi de jouer en mode DUEL. On va alterner les modes Challenger et Défenseur à tour de rôle jusqu'à ce que l'un des joueurs l'emporte.");
                nbTours = 100;
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
}