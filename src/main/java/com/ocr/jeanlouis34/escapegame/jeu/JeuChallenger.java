package com.ocr.jeanlouis34.escapegame.jeu;

import com.ocr.jeanlouis34.escapegame.player.PlayerMachine;
import com.ocr.jeanlouis34.escapegame.combi.CombinaisonManuelle;
import com.ocr.jeanlouis34.escapegame.combi.CombinaisonsAuto;
import com.ocr.jeanlouis34.escapegame.combi.CombinaisonsParams;
import org.apache.log4j.Logger;

/**
 * Twin Class of the class JeuDefenseur
 *
 * This class defines the characteristics of one of the 3 patterns :
 * In the Challenger pattern, the Player has to find out the combinaison created by the Machine,
 *
 * This Class enables to run as many loops as the number of rounds and to return the value of victoireJoueur
 * The variable victoireJoueur says if the Human Player has won that is if he had found out the combinaison automatically defined by the Machine
 *
 * This class instances objects and methods of some Classes :
 *  * JeuBegin about the game characteristics,
 *  * PlayerMachine about its method used by the Machine to automatically compare both automatic and manual combinaisons,
 *  * Combinaisons, CombinaisonsAuto and CombinaisonManuelle about the size of the combinaisons and the methods to combine automatically and not
 *  *
 *
 * This class objects are instanced only in class JeuDuel.
 * This class is one of the 5 classes of the first range (see javadoc of the Class JeuBegin) are invoked in the Class main.
 */

public class JeuChallenger implements Jeu {

    private int victoire;
    private int tourPartie;
    private JeuParams jeuParams = new JeuParams();
    private CombinaisonsParams combinaisonsParams = new CombinaisonsParams();
    private CombinaisonsAuto combinaisonsAuto = new CombinaisonsAuto(combinaisonsParams);
    private CombinaisonManuelle combinaisonManuelle = new CombinaisonManuelle(combinaisonsParams);
    private PlayerMachine playerMachine = new PlayerMachine(combinaisonsParams, combinaisonsAuto, combinaisonManuelle);
    static Logger logger = Logger.getLogger(JeuChallenger.class);

    public JeuChallenger(JeuParams jeuParams) {
        this(0,0,jeuParams);
    }

    public JeuChallenger(int victoire, int tourPartie, JeuParams jeuParams) {
        this.victoire = victoire;
        this.tourPartie = tourPartie;
        this.jeuParams = jeuParams;
    }

    public int getTourPartie() {
        return tourPartie;
    }

    public void setTourPartie(int tourPartie) {
        this.tourPartie = tourPartie;
    }

    public int getVictoire() {
        return victoire;
    }

    /**
     * This method is the lone method of this class
     * It enables to run as many loops as the number of rounds and to return the value of victoireJoueur
     * The variable victoireJoueur says if the Human Player has won
     *
     * In the code, some lines are surrounded by signs to neutralize loggers and enable to play in real
     * The loggers have not been erased to enable to test the program in his different issues
     */

    @Override
    public void runJeu() {
        tourPartie = 0;
        combinaisonManuelle.getCombinaison().clear();
        combinaisonsAuto.getCombinaison().clear();
        combinaisonsParams.addNbCombinaisons();
        combinaisonsParams.printNbcombinaisons();
        combinaisonsAuto.combiner();
            do {
                if (jeuParams.getModeDeveloper().equals("OUI")) {
                    logger.info("\nLa combinaison secrète de la Machine est :");
                    combinaisonsAuto.printCombinaison();
                    logger.info("\nComparons step by step les 2 combinaisons ...");
                }
                combinaisonManuelle.combiner();
                playerMachine.comparerLesListes();
                tourPartie++;
                if (playerMachine.getVictoire() == 1) {
                    victoire = 1;
                    logger.info("Bravo " + jeuParams.getNomJoueur() + ". Tu as gagné.");
                } else if (playerMachine.getVictoire() == 2) {
                    victoire = 2;
                    logger.info("Désolé " + jeuParams.getNomJoueur() + ". Tu n'as pas gagné.");
                }
            }
        while (victoire == 2 && tourPartie < jeuParams.getNbTours());
    }
}

