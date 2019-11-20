package com.ocr.jeanlouis34.escapegame.jeu;

import com.ocr.jeanlouis34.escapegame.player.PlayerJoueur;
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
 *  * CombinaisonsParams and JeuParams about predefined parameters bu default which define the game characteristics,
 *  * PlayerJoueur about its method used by the human player to manuallyally compare both automatic and manual combinaisons,
 *  * PlayerMachine about its method used by the Machine to automatically compare both automatic and manual combinaisons,
 *  * Combinaisons, CombinaisonsAuto and CombinaisonManuelle about the size of the combinaisons and the methods to combine automatically and not
 */
public class JeuChallenger implements Jeu {

    // this variable enables to store the result of each round of the game
    private int victoire;
    // this variable enables to count and register the number of rounds that have been played in the game
    private int tourPartie;
    private JeuParams jeuParams;
    private CombinaisonsParams combinaisonsParams = new CombinaisonsParams();
    private CombinaisonsAuto combinaisonsAuto = new CombinaisonsAuto(combinaisonsParams);
    private CombinaisonManuelle combinaisonManuelle = new CombinaisonManuelle(combinaisonsParams);
    private PlayerJoueur playerJoueur = new PlayerJoueur(combinaisonsParams, combinaisonsAuto, combinaisonManuelle);
    private PlayerMachine playerMachine = new PlayerMachine(combinaisonsParams, combinaisonsAuto, combinaisonManuelle, playerJoueur);
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
        combinaisonsAuto.getCombinaisonSecrete().clear();
        combinaisonManuelle.getCombinaisonSecrete().clear();
        combinaisonsParams.addNbCombinaisons();
        combinaisonsAuto.setModeTirageAuto(5);
        combinaisonsAuto.combiner();
        if (jeuParams.getModeDeveloper().equals("OUI")) {
            logger.info("\n(COMBINAISON SECRETE :" + combinaisonsAuto.getCombinaison() + ")");
        }

        do {
            System.out.print("VOTRE PROPOSITION :    ");
            combinaisonManuelle.setModeTirageManuel(2);
            combinaisonManuelle.combiner();
            playerMachine.comparerLesListes();
            tourPartie++;
            if (playerMachine.getVictoire() == 1) {
                victoire = 1;
            } else if (playerMachine.getVictoire() == 2) {
                victoire = 2;
            }
        }
        while (victoire == 2 && tourPartie < jeuParams.getNbTours());
    }
}

