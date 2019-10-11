package com.ocr.jeanlouis34.escapegame;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * This Class is one of the 3 basic classes which deal with the combinaisons issues.
 *
 * It is used to define the size of both the automatic and handmade combinaisons.
 *
 * The results are used at two differents levels :
 * to compare the combinaisons (PlayerJoueur + PlayerMachine)
 * to run 2 of the 3 operatives patterns (JeuChallenger and JeuDefenseur)
 */

public class Combinaisons {

    private int nbCombinaisons;
    protected List<Integer> combinaison;
    protected int tirage;
    static Logger logger = Logger.getLogger(Combinaisons.class);

    public Combinaisons() {
        this(0, new ArrayList<>(), 0);
    }

    public Combinaisons(int nbCombinaisons, List<Integer> combinaison, int tirage) {
        this.nbCombinaisons = nbCombinaisons;
        this.combinaison = combinaison;
        this.tirage = tirage;
    }

    public int getNbCombinaisons() {
        return nbCombinaisons;
    }

    public List<Integer> getCombinaison() {
        return combinaison;
    }

    /**
     * This is the lone method of this class
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

    public void combiner (List<Integer> combinaison) {
        this.combinaison = combinaison;
        logger.info("Création d'une combinaison sous forme d'une liste de longueur Nbcombinaisons et indexation dans l'Arraylist du tirage à la clé correspond au tour de tirage, par réitération le même nombre de fois que le Nbcombinaisons de la méthode tirer. ");
    }

    public void tirer (int tirage) {
        this.tirage = tirage;
        logger.info("Tirage d'un chiffre entier k compris entre 0 et 9 avant affectation dans une Arraylist.");
    }

    public void printCombinaison(){
        for (int j = 0; j < this.nbCombinaisons; j++) {
            logger.info("Élément à l'index " + j + " = " + combinaison.get(j));
        }
    }
}

