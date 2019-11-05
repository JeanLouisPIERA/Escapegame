package com.ocr.jeanlouis34.escapegame.combi;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

public class CombinaisonsParams {

    static Logger logger = Logger.getLogger(CombinaisonsParams.class);
    private int nbCombinaisons;

    public CombinaisonsParams() {
        this.nbCombinaisons = 0;
    }

    public CombinaisonsParams(int nbCombinaisons) {
        this.nbCombinaisons = nbCombinaisons;
    }

    public int getNbCombinaisons() {
        return nbCombinaisons;
    }

    /**
     * this is the lone method of this class to enable to define the length of both manual and automatic combinaisons during the game
     * it is a basic method that scan an int
     */
    public void addNbCombinaisons() {
        final Properties prop = new Properties();
        InputStream input = null;
        try {
            input = Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration.properties");
            // chargement du fichier properties
            prop.load(input);
            // récupération et impression de la valeur de la propriété
            logger.info("Comme je te l'ai dit, une combinaison secrète doit être découverte. \nPar défaut le nombre de chiffres de cette combinaison est : ");
            System.out.println(prop.getProperty("nbCombinaisonsByDefault"));
        } catch (final IOException ex) {
            logger.error(ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (final IOException e) {
                    logger.error(e);
                }
            }
        }
        Scanner sc = new Scanner(System.in).useDelimiter(" *");
        logger.info("Si ce chiffre par défaut te convient, tape le à nouveau pour confirmer sinon indique combien tu veux que cette combinaison contienne de chiffres ?");
        logger.info("Attention, pense à entrer un nombre chiffre positif entre 2 et 9 ...");
        try {
            this.nbCombinaisons = sc.nextInt();
        } catch (InputMismatchException ex) {
            logger.error("La saisie n'est pas correcte. La partie va donc se jouer avec la valeur par défaut.");
            int i = Integer.parseInt(prop.getProperty("nbCombinaisonsByDefault"));
            nbCombinaisons = i;
        }
        if (nbCombinaisons <= 9 && nbCombinaisons > 1) {
            logger.info("C'est noté. \nTu as décidé que la combinaison secrète auras une longueur de " + this.nbCombinaisons + "  chiffres.");
            logger.info("Pour connaitre le nombre de combinaisons possibles, multiplie 10 par lui même autant de fois que tu as choisi de chiffres dans ta combinaison");
        } else {
            int i = Integer.parseInt(prop.getProperty("nbCombinaisonsByDefault"));
            nbCombinaisons = i;
            logger.info("Ta saisie n'est pas correcte. La longueur de la combinaison secrète est donc fixée à la valeur par défaut de " + this.nbCombinaisons + " chiffres.");
        }
    }
}

