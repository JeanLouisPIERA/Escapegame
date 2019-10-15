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

public class PlayerMachine implements Player {

    static Logger logger = Logger.getLogger(PlayerMachine.class);
    private int victoire;
    private List<String> comparaisonsListes;
    private CombinaisonsParams combinaisonsParams;
    private CombinaisonsAuto combinaisonsAuto;
    private CombinaisonManuelle combinaisonManuelle;

    public PlayerMachine(CombinaisonsParams combinaisonsParams, CombinaisonsAuto combinaisonsAuto, CombinaisonManuelle combinaisonManuelle) {
        this(0,new ArrayList<>(), combinaisonsParams, combinaisonsAuto, combinaisonManuelle);
    }

    public PlayerMachine(int victoire, List<String> comparaisonsListes, CombinaisonsParams combinaisonsParams, CombinaisonsAuto combinaisonsAuto, CombinaisonManuelle combinaisonManuelle) {
        this.victoire = victoire;
        this.comparaisonsListes = comparaisonsListes;
        this.combinaisonsParams = combinaisonsParams;
        this.combinaisonsAuto = combinaisonsAuto;
        this.combinaisonManuelle = combinaisonManuelle;
    }

    public List getComparaisonsListes() {
        return comparaisonsListes;
    }

    public void setComparaisonsListes(ArrayList<String> comparaisonsListes) {
        this.comparaisonsListes = comparaisonsListes;
    }

    public int getVictoire() {
        return victoire;
    }

    public void setVictoire(int victoire) {
        this.victoire = victoire;
    }

    /**
     * This is the lone method of this class which enables the Machine Player to compare the result of both combinaisons, key by key.
     */


    public void comparerLesListes() {
        String A = "=";
        String B = "-";
        String C = "+";
        for (int k = 0; k < combinaisonsParams.getNbCombinaisons(); k++) {
            int cm = (Integer) combinaisonManuelle.getCombinaison().get(k);
            int ca = (Integer) combinaisonsAuto.getCombinaison().get(k);
            if (cm == ca) {
                (comparaisonsListes).add(A);
            } else if (cm < ca) {
                (comparaisonsListes).add(B);
            } else {
                (comparaisonsListes).add(C);
            }
        }
        printComparaisonsListes();
        combinaisonManuelle.getCombinaison().clear();
        comparaisonsListes.clear();
        }

        public void printComparaisonsListes(){
            logger.info("\nPour chacun des chiffres que tu as proposés, je vais te donner une indication : \n = si c'est bon, - si ton chiffre est inférieur, + si ton chiffre est supérieur");
            logger.info("\nTa proposition " + combinaisonManuelle.getCombinaison() + " donne les résultats suivants : " + comparaisonsListes);
            if (combinaisonManuelle.getCombinaison().equals(combinaisonsAuto.getCombinaison())) {
                logger.info("\nTu as découvert la combinaison secrète du Player Machine.");
                victoire = 1;
            } else {
                logger.info("\nTu n'as toujours pas découvert la combinaison secrète du Player Machine.");
                victoire = 2;
            }
        }
    }



