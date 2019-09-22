package com.ocr.jeanlouis34.escapegame;

import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JeuBegin {

    private String nomJoueur = "";
    private int nbCombinaisons = 0;
    private int modeJeu = 0;
    static Logger logger = Logger.getLogger(JeuBegin.class);

    /* CONSTRUCTEURS */

    public JeuBegin() {

    }

    public JeuBegin(String nomJoueur, int nbCombinaisons, int modeJeu) {
        this.nomJoueur = nomJoueur;
        this.nbCombinaisons = nbCombinaisons;
        this.modeJeu = modeJeu;
    }

    /* NOM JOUEUR */

    public String getNomJoueur() {
        return nomJoueur;
    }

    protected void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public void addNomJoueur() {
        Scanner sc = new Scanner(System.in);
        logger.info("Ami Joueur, Bonjour ! \n Bienvenue dans ce nouvel Escape Game. Dis moi, quel est ton nom ?");
        this.nomJoueur = sc.nextLine();
        logger.info("C'est noté. Je te remercie" + this.nomJoueur + "! \n ");
        logger.info("Dans ce jeu, l'ordinateur ou toi, vous devrez découvrir une combinaison secrète de plusieurs chiffres.");
        logger.info("Entre l'ordinateur et toi, à la fin, il ne peut en rester qu'un. \n Voici les modes de Jeu qui s'offrent à toi :");
        setNomJoueur(this.nomJoueur);
    }

    /* NOMBRE DE COMBINAISONS */

    public int getNbCombinaisons() { return nbCombinaisons;
    }

    /*public void setNbCombinaisons(int nbCombinaisons) {
        this.nbCombinaisons = nbCombinaisons;
    }*/

    public void addNbCombinaisons() {
        Scanner sc = new Scanner(System.in);
        logger.info("Comme je te l'ai dit, une combinaison secrète doit être découverte. \n Dis moi tu veux que cette combinaison contienne combien de chiffres ?");
        try {
            logger.info("Attention, pense à entrer un nombre chiffre positif entre 2 et 9 ...");
            this.nbCombinaisons = sc.nextInt();
            /*setNbCombinaisons(this.nbCombinaisons);*/
        } catch (InputMismatchException ex) {
            logger.error(" La saisie du nombre de combinaisons n'est pas correcte. On recommence à zéro ...");
            sc.next();
            addNbCombinaisons();
        }
        if(nbCombinaisons <= 9 && nbCombinaisons > 1) {
            logger.info("Parfait. Pour connaitre le nombre de combinaisons possibles, multiplie 10 par lui même autant de fois que tu as choisi de chiffres dans ta combinaison");
        }
        else { logger.info(" La saisie du nombre de combinaisons n'est pas correcte. On recommence à zéro ...");
            sc.next();
            addNbCombinaisons();
        }
    }

    /* DEFINIR MODE DE JEU */

    public int getModeJeu() { return modeJeu;
    }

    public void setModeJeu(int modeJeu) {
        this.modeJeu = modeJeu;
        }

    public void addModeJeu () {
        Scanner sc = new Scanner(System.in);
        try {
            logger.info("Attention, pense à entrer un nombre entier positif ...");
            this.modeJeu = sc.nextInt();
            setModeJeu(this.modeJeu);
        } catch (InputMismatchException e) {
            logger.error("La saisie du nombre du mode de jeu n'est pas correcte. Il faut recommencer ...");
            sc.next();
        }

    }

    /* AUTRES METHODES */

    /*public void AskSomething() {
        logger.info("Ami Joueur, Bonjour ! \n Bienvenue dans ce nouvel Escape Game. Dis moi, quel est ton nom ?");
        addNomJoueur();
        logger.info("C'est noté. Je te remercie" + this.getNomJoueur() + "! \n ");
        logger.info("Dans ce jeu, l'ordinateur ou toi, vous devrez découvrir une combinaison secrète de plusieurs chiffres.");
        logger.info("Entre l'ordinateur et toi, à la fin, il ne peut en rester qu'un. \n ");
    }*/

    public void displayAvailableModeJeu(){
        logger.info ("Voici les modes de Jeu qui s'offrent à toi :");
        logger.info("Choix N°1 - Mode CHALLENGER : c'est l'ordinateur qui choisit la combinaison. A toi de la découvrir en un nombre de coups limités");
        logger.info("Choix N°2 - Mode DEFENSEUR : c'est toi qui choisit la combinaison et l'ordinateur qui attaque dans un nombre de coups limités");
        logger.info("Choix N°3 - Mode DUEL : l'ordinateur et toi vous choisissez vos combinaisons et attaquez à tour de rôle");
        logger.info("Alors Ami" + this.getNomJoueur() + ", quelle est ta décision ? Ecris ici le numéro de ton choix :");
    }

    public void displaySelectedModeJeu() {
        switch (modeJeu) {
            case 1:
                logger.info("BRAVO ! Tu as choisi le mode CHALLENGER. A toi de trouver la combinaison de l'ordinateur. Que la Force soit avec toi !");
                break;
            case 2:
                logger.info("BRAVO ! Tu as choisi le mode DEFENSEUR. Tous mes voeux pour que ce satané ordinateur ne trouve pas ta combinaison !");
                break;
            case 3:
                logger.info("BRAVO ! Tu as choisi le mode DUEL. L'ordinateur et toi vous allez jouer à tour de rôle. A toi l'honneur ! ");
                break;
            default:
                logger.info("Ne réponds pas n'importe quoi pour éviter le combat. Pour que tu comprennes bien, bis repetita ...");
                runModeJeu();
                break;
        }
    }

    public void runModeJeu() {
            this.displayAvailableModeJeu();
            addModeJeu();
            this.displaySelectedModeJeu();
    }
}