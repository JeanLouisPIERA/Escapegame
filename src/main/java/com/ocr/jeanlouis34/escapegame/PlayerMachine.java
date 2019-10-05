package com.ocr.jeanlouis34.escapegame;

import org.apache.log4j.Logger;

import java.util.*;

/**
 * This class is the twin class of Player Machine.
 *
 * This class is only linked with the Jeuchallenger Class in the Challenger pattern.
 *
 * This class enables to compare both automatically and handly made combinaisons.
 *
 * That is why it uses the variables of all the classes about COMBINAISON.
 *
 */

public class PlayerMachine {

    static Logger logger = Logger.getLogger(PlayerMachine.class);
    private int victoireChallenger;
    private List<String> comparaisonsListesAuto;
    private Combinaisons combinaisons;
    private CombinaisonsAuto combinaisonsAuto;
    private CombinaisonManuelle combinaisonManuelle;


    public PlayerMachine(Combinaisons combinaisons, CombinaisonsAuto combinaisonsAuto, CombinaisonManuelle combinaisonManuelle) {
        this(0, new ArrayList<>(),combinaisons, combinaisonsAuto, combinaisonManuelle);

    }

    public PlayerMachine(int victoireChallenger, List<String> comparaisonsListesAuto, Combinaisons combinaisons, CombinaisonsAuto combinaisonsAuto, CombinaisonManuelle combinaisonManuelle) {
        this.victoireChallenger = victoireChallenger;
        this.comparaisonsListesAuto = comparaisonsListesAuto;
        this.combinaisons = combinaisons;
        this.combinaisonsAuto = combinaisonsAuto;
        this.combinaisonManuelle = combinaisonManuelle;
    }

    public List getComparaisonsListesAuto() {
        return comparaisonsListesAuto;
    }

    public void setComparaisonsListesAuto(ArrayList<String> comparaisonsListes) {
        this.comparaisonsListesAuto = comparaisonsListes;
    }

    public int getVictoireChallenger() {
        return victoireChallenger;
    }

    public void setVictoireChallenger(int victoireChallenger) {
        this.victoireChallenger = victoireChallenger;
    }

    /**
     * This is the lone method of this class which enables the Machine Player to compare the result of both combinaisons, key by key.
     */


    public void comparerLesListesAuto() {
        String A = "=";
        String B = "-";
        String C = "+";
        for (int k = 0; k < combinaisons.getNbCombinaisons(); k++) {
            int cm = (Integer) combinaisonManuelle.getCombinaisonManuelle().get(k);
            int ca = (Integer) combinaisonsAuto.getCombinaisonsAuto().get(k);
            if (cm == ca) {
                (comparaisonsListesAuto).add(A);
            } else if (cm < ca) {
                (comparaisonsListesAuto).add(B);
            } else {
                (comparaisonsListesAuto).add(C);
            }
        }
        logger.info("\nPour chacun des chiffres que tu as proposés, je vais te donner une indication : \n = si c'est bon, - si ton chiffre est inférieur, + si ton chiffre est supérieur");
        logger.info("\nTa proposition " + combinaisonManuelle.getCombinaisonManuelle() + " donne les résultats suivants : " + comparaisonsListesAuto);
        if (combinaisonManuelle.getCombinaisonManuelle().equals(combinaisonsAuto.getCombinaisonsAuto())) {
                logger.info("\nTu as découvert la combinaison secrète du Player Machine.");
                victoireChallenger = 1;
            } else {
                logger.info("\nTu n'as toujours pas découvert la combinaison secrète du Player Machine.");
                victoireChallenger = 2;
            }
        combinaisonManuelle.getCombinaisonManuelle().clear();
        comparaisonsListesAuto.clear();
        }
    }



