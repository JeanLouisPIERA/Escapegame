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

public class CombinaisonManuelle {

    static Logger logger = Logger.getLogger(CombinaisonManuelle.class);
    private List<Integer> combinaisonManuelle;
    private int tirageManuel;
    private Combinaisons combinaisons;

    public CombinaisonManuelle(Combinaisons combinaisons) {
        this(new ArrayList<>(),0,combinaisons);
    }

    public CombinaisonManuelle(List<Integer> combinaisonManuelle, int tirageManuel, Combinaisons combinaisons) {
        this.combinaisonManuelle = combinaisonManuelle;
        this.tirageManuel = tirageManuel;
        this.combinaisons = combinaisons;
    }

    public List<Integer> getCombinaisonManuelle() {
        return combinaisonManuelle;
    }

    public void setCombinaisonsManuelle(List<Integer> combinaisonManuelle) {
        this.combinaisonManuelle = combinaisonManuelle;
    }

    public int getTirageManuel() {
        return tirageManuel;
    }

    public void setTirageManuel(int tirageManuel) {
        this.tirageManuel = tirageManuel;
    }

    /**
     * This method is a basic method to combine scanned choices of the Human Player in an indexed list.
     */

    public void combinerManuelle() {

        /*logger.info("Ici le Player Machine. Je rappelle que la combinaison secrète a une longueur de " + combinaisons.getNbCombinaisons() + " chiffres.");*/
        logger.info("\nA toi de proposer une combinaison. \nAttention tu ne peux saisir que des chiffres entre 0 et 9. \nSinon la machine n'enregistrera que le premier chiffre que tu saisiras.");
        while (this.combinaisonManuelle.size() < combinaisons.getNbCombinaisons())
            try {
                tirerManuel();
            } catch (Exception e) {
                logger.error(e);
            }
    }

    /**
     * This is a sub-method of the method combinerManuelle
     * It enables to scan the decisions of the Human Player when he creates his own combinaison.
     */


    public void tirerManuel() {

        logger.info("Merci d'indiquer ici le chiffre que tu choisis :");
        Scanner sc = new Scanner(System.in).useDelimiter(" *");
        try {
            this.tirageManuel = sc.nextInt();
            (/*(ArrayList)*/ this.combinaisonManuelle).add(tirageManuel);
        } catch (InputMismatchException e) {
            logger.info("La saisie n'est pas correcte. Il faut recommencer ...");
            sc.next();
            tirerManuel();
        }
        }

    public void printCombinaisonManuelle() {
        for (int j = 0; j < combinaisons.getNbCombinaisons(); j++) {
            logger.info("Élément à l'index " + j + " = " + combinaisonManuelle.get(j));
        }
    }


}
