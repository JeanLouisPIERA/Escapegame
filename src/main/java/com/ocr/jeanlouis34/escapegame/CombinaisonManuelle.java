package com.ocr.jeanlouis34.escapegame;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * This Class is one of the 3 basic classes which deal with the combinaisons issues.
 *
 * It is used to define the content of the handmade combinaisons based on an automatic sort.
 *
 * The results are used at two differents levels :
 * to compare the combinaisons (PlayerJoueur + PlayerMachine)
 * to run 2 of the 3 operatives patterns (JeuChallenger and JeuDefenseur)
 */

public class CombinaisonManuelle extends Combinaisons{

    static Logger logger = Logger.getLogger(CombinaisonManuelle.class);

    private List<Integer> combinaison;
    private int tirage;
    private Combinaisons combinaisons;

    public CombinaisonManuelle(Combinaisons combinaisons) {
        this(new ArrayList<>(), 0, combinaisons);
    }

    public CombinaisonManuelle(List<Integer> combinaison, int tirage, Combinaisons combinaisons) {
        this.combinaison = combinaison;
        this.tirage = tirage;
        this.combinaisons = combinaisons;
    }

    @Override
    public List<Integer> getCombinaison() {
        return combinaison;
    }

    /**
     * This method is a basic method to combine scanned choices of the Human Player in an indexed list.
     */

    public void combiner() {
        /*logger.info("Ici le Player Machine. Je rappelle que la combinaison secrète a une longueur de " + combinaisons.getNbCombinaisons() + " chiffres.");*/
        logger.info("\nA toi de proposer une combinaison. \nAttention tu ne peux saisir que des chiffres entre 0 et 9. \nSinon la machine n'enregistrera que le premier chiffre que tu saisiras.");
        while (this.combinaison.size() < combinaisons.getNbCombinaisons())
            try {
                this.tirer();
            } catch (Exception e) {
                logger.error(e);
            }
    }

    /**
     * This is a sub-method of the method combinerManuelle
     * It enables to scan the decisions of the Human Player when he creates his own combinaison.
     */


    public void tirer() {

        logger.info("Merci d'indiquer ici le chiffre que tu choisis :");
        Scanner sc = new Scanner(System.in).useDelimiter(" *");
        try {
            this.tirage = sc.nextInt();
            (/*(ArrayList)*/ this.combinaison).add(tirage);
        } catch (InputMismatchException e) {
            logger.info("La saisie n'est pas correcte. Il faut recommencer ...");
            sc.next();
            this.tirer();
        }
        }

    public void printCombinaison() {
        for (int j = 0; j < combinaisons.getNbCombinaisons(); j++) {
            logger.info("Élément à l'index " + j + " = " + combinaison.get(j));
        }
    }
}
