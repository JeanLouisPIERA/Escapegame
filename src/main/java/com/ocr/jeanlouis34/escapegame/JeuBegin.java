package com.ocr.jeanlouis34.escapegame;

import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JeuBegin {

    private String NomJoueur = "";
    public Integer NbCombinaisons;
    private int ModeJeu;
    static Logger logger = Logger.getLogger(JeuBegin.class);

    public int getModeJeu() {
        return ModeJeu;
    }

    public String getNomJoueur() { return NomJoueur;
    }

    public Integer getNbCombinaisons() { return NbCombinaisons;
    }

    protected void setNomJoueur() {
        Scanner sc = new Scanner(System.in);
        this.NomJoueur = sc.nextLine();
    }


    protected void setNbCombinaisons() {
        Scanner sc = new Scanner(System.in).useDelimiter(" *");
        try {
            logger.info("Attention, pense à entrer un nombre entier positif ...");
            this.NbCombinaisons = sc.nextInt();
        } catch (InputMismatchException ex) {
            logger.error(" La saisie du nombre de combinaisons n'est pas correcte. On recommance à zéro ...");
            sc.next();
            setNbCombinaisons();
        }
    }

    protected void setModeJeu() {
        Scanner sc = new Scanner(System.in);
        try {
            logger.info("Attention, pense à entrer un nombre entier positif ...");
            this.ModeJeu = sc.nextInt();
        } catch (InputMismatchException e) {
            logger.error("La saisie du nombre du mode de jeu n'est pas correcte. Il faut recommencer ...");
            sc.next();
            setModeJeu();
            }
        }

    public void AskSomething() {
        logger.info("Ami Joueur, Bonjour ! \n Bienvenue dans ce nouvel Escape Game. Dis moi, quel est ton nom ?");
        setNomJoueur();
        logger.info("C'est noté. Je te remercie" + this.NomJoueur + "! \n Dans ce jeu, l'ordinateur ou toi, vous devrez découvrir une combinaison secrète de plusieurs chiffres. \n Dis moi tu veux que cette combinaison contienne combien de chiffres ?");
        setNbCombinaisons();
        logger.info("Parfait. Pour connaitre le nombre de combinaisons possibles, multiplie 10 par lui même autant de fois que tu as choisi de chiffres dans ta combinaison");
        logger.info("Maintenant, entre l'ordinateur et toi, à la fin, il ne peut en rester qu'un. \n Voici les modes de Jeu qui s'offrent à toi :");
    }

    public void displayAvailableModeJeu() {
        logger.info("Choix N°1 - Mode CHALLENGER : c'est l'ordinateur qui choisit la combinaison. A toi de la découvrir en un nombre de coups limités");
        logger.info("Choix N°2 - Mode DEFENSEUR : c'est toi qui choisit la combinaison et l'ordinateur qui attaque dans un nombre de coups limités");
        logger.info("Choix N°3 - Mode DUEL : l'ordinateur et toi vous choisissez vos combinaisons et attaquez à tour de rôle");
        logger.info("Alors Ami" + this.NomJoueur + ", quelle est ta décision ? Ecris ici le numéro de ton choix :");
    }

    public void displaySelectedModeJeu(int nbModeJeu) {
        switch (nbModeJeu) {
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
            setModeJeu();
            this.displaySelectedModeJeu(ModeJeu);
    }
}