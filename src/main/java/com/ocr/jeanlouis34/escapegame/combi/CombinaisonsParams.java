package com.ocr.jeanlouis34.escapegame.combi;

import org.apache.log4j.Logger;

import java.util.InputMismatchException;
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
        Scanner sc = new Scanner(System.in).useDelimiter(" *");
        logger.info("Comme je te l'ai dit, une combinaison secrète doit être découverte. \nDis moi tu veux que cette combinaison contienne combien de chiffres ?");
        try {
            logger.info("Attention, pense à entrer un nombre chiffre positif entre 2 et 9 ...");
            this.nbCombinaisons = sc.nextInt();
        } catch (InputMismatchException ex) {
            logger.error("La saisie n'est pas correcte. On recommence à zéro ...");
            sc.next();
            addNbCombinaisons();
        }
    }

    public void printNbcombinaisons() {
        if (nbCombinaisons <= 9 && nbCombinaisons > 1) {
            logger.info("C'est noté. \nTu as décidé que la combinaison secrète auras une longueur de " + this.nbCombinaisons +"  chiffres.");
            logger.info("Pour connaitre le nombre de combinaisons possibles, multiplie 10 par lui même autant de fois que tu as choisi de chiffres dans ta combinaison");
        } else {
            logger.info("Ta saisie n'est pas correcte. Je fixe donc la longueur de la combinaison secrète à 4 chiffres.");
            this.nbCombinaisons = 4;
        }
    }
}
