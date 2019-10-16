package com.ocr.jeanlouis34.escapegame.combi;

import org.apache.log4j.Logger;

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

    public CombinaisonsAuto(CombinaisonsParams combinaisonsParams) {
        super(combinaisonsParams);
    }

    public CombinaisonsAuto(List<Integer> combinaison, int tirage, CombinaisonsParams combinaisonsParams) {
        super(combinaison, tirage, combinaisonsParams);
    }

    public List<Integer> getCombinaison() {
        return combinaison;
    }

    /**
     * This method enables to combine the results of sorts in an indexed list.
     * It is identically shared by all the subclasses Combinaisons.
     */

    public void combiner() {
        super.combiner(combinaison);
    }

    /**
     * This method enables to print the combinaison obtained by the method combiner.
     * It is identically shared by all the subclasses Combinaisons.
     */

    public void printCombinaison() {
        super.printCombinaison();
    }


    /**
     * This is a sub-method of the method combinerAuto
     * It enables to realize automatic sorts
     *
     * WARNING : this method could be deeply developped to enable the Player Machine to adjust its combinaisons from the answers of the Human Player
     * For now, the game can be very boring in its automatic pattern.
     * This further development could give an extra interest to this Escape Game.
     */

    @Override
    public void tirer() {
        Random r = new Random();
        int randint = Math.abs(r.nextInt()) % 10;
            this.combinaison.add(randint);
    }
}

