package com.ocr.jeanlouis34.escapegame;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This Class is one of the 3 basic classes which deal with the combinaisons issues.
 *
 * It is used to define the content of the automatic combinaisons based on an automatic sort.
 *
 * The results are used at two levels :
 * to compare the combinaisons (PlayerJoueur + PlayerMachine)
 * to run 2 of the 3 operatives patterns (JeuChallenger and JeuDefenseur)
 */

public class CombinaisonsAuto extends Combinaisons {

    static Logger logger = Logger.getLogger(CombinaisonsAuto.class);
    private List<Integer> combinaison;
    private Combinaisons combinaisons;

    public CombinaisonsAuto(Combinaisons combinaisons) {
        this(new ArrayList<>(), combinaisons);
    }

    public CombinaisonsAuto(List<Integer> combinaison, Combinaisons combinaisons) {
        this.combinaison = combinaison;
        this.combinaisons = combinaisons;
    }

    @Override
    public List<Integer> getCombinaison() {
        return combinaison;
    }

    /**
     * This method enables to combine the results of automatic sorts in an indexed list.
     */

    public void combiner() {
        while (this.combinaison.size() < combinaisons.getNbCombinaisons()) {
            this.tirer();
        }
    }

    public void printCombinaison() {
        for (int i = 0; i < combinaisons.getNbCombinaisons(); i++) {
            logger.info("Élément à l'index " + i + " = " + combinaison.get(i));
        }
    }


    /**
     * This is a sub-method of the method combinerAuto
     * It enables to realize automatic sorts
     *
     * WARNING : this method could be deeply developped to enable the Player Machine to adjust its combinaisons from the answers of the Human Player
     * For now, the game can be very boring in its automatic pattern.
     * This further development could give an extra interest to this Escape Game.
     */

    public void tirer() {
        Random r = new Random();
        int randint = Math.abs(r.nextInt()) % 10;
            this.combinaison.add(randint);
    }
}

