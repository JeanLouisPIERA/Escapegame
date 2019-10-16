package com.ocr.jeanlouis34.escapegame.combi;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is one of the 3 basic classes which deal with the combinaisons issues.
 *
 * It is used to define the size of both the automatic and handmade combinaisons.
 *
 * The results are used at two differents levels :
 * to compare the combinaisons (PlayerJoueur + PlayerMachine)
 * to run 2 of the 3 operatives patterns (JeuChallenger and JeuDefenseur)
 */

public abstract class Combinaisons {


    protected List<Integer> combinaison;
    protected int tirage;
    private CombinaisonsParams combinaisonsParams;
    static Logger logger = Logger.getLogger(Combinaisons.class);

    public Combinaisons(CombinaisonsParams combinaisonsParams) {
        this(new ArrayList<>(), 0, combinaisonsParams);
    }

    public Combinaisons(List<Integer> combinaison, int tirage, CombinaisonsParams combinaisonsParams) {
        this.combinaison = combinaison;
        this.tirage = tirage;
        this.combinaisonsParams = combinaisonsParams;
    }

    /**
     * This method enables to combine the results of sorts in an indexed list.
     * It is identically shared by all the subclasses Combinaisons.
     */

    public void combiner (List<Integer> combinaison) {
        while (this.combinaison.size() < combinaisonsParams.getNbCombinaisons())
            this.tirer();
        }

    /**
     * This method enables to sort a number.
     * It is overrided by each member of this superclass, either on a manual way, either on an automatic way.
     */

    abstract void tirer();

    /**
     * This method enables to print the combinaison obtained by the method combiner.
     * It is identically shared by all the subclasses Combinaisons.
     */

    public void printCombinaison(){
        for (int j = 0; j < combinaisonsParams.getNbCombinaisons(); j++) {
            logger.info("Élément à l'index " + j + " = " + combinaison.get(j));
        }
    }
}

