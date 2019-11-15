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
    protected List<Integer> combinaisonSecrete;
    protected CombinaisonsParams combinaisonsParams;
    static Logger logger = Logger.getLogger(Combinaisons.class);

    public Combinaisons(CombinaisonsParams combinaisonsParams) {
        this(new ArrayList<>(), new ArrayList<>(), combinaisonsParams);
    }

    public Combinaisons(List<Integer> combinaison, List<Integer> combinaisonSecrete, CombinaisonsParams combinaisonsParams) {
        this.combinaison = combinaison;
        this.combinaisonSecrete = combinaisonSecrete;
        this.combinaisonsParams = combinaisonsParams;
    }

    /**
     * This method enables to combine the results of sorts in an indexed list.
     * It is identically shared by all the subclasses Combinaisons.
     */
    abstract void combiner();

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

