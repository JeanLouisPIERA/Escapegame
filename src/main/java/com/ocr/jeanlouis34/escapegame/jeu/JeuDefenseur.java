package com.ocr.jeanlouis34.escapegame.jeu;

import com.ocr.jeanlouis34.escapegame.player.PlayerJoueur;
import com.ocr.jeanlouis34.escapegame.combi.CombinaisonManuelle;
import com.ocr.jeanlouis34.escapegame.combi.CombinaisonsAuto;
import com.ocr.jeanlouis34.escapegame.combi.CombinaisonsParams;
import com.ocr.jeanlouis34.escapegame.player.PlayerMachine;
import org.apache.log4j.Logger;

/**
 * Twin class of the Class JeuChallenger
 *
 * This class defines the characteristics of one of the 3 patterns :
 * In the Defenseur pattern : the Machine has to find out the combinaison created by the Player,
 * This Class enables to run as many loops as the number of rounds and to return the value of victoireJoueurDef
 * The variable victoireJoueurDef says if the Human Player has won that is if the Player Machine has not been able to find out the combinaison defined by the Huùan Player
 *
 *  This class instances objects and methods of some Classes :
 *  * CombinaisonsParams and JeuParams about predefined parameters bu default which define the game characteristics,
 *  * PlayerJoueur about its method used by the human player to manuallyally compare both automatic and manual combinaisons,
 *  * PlayerMachine about its method used by the Machine to automatically compare both automatic and manual combinaisons,
 *  * Combinaisons, CombinaisonsAuto and CombinaisonManuelle about the size of the combinaisons and the methods to combine automatically and not
 *  */

public class JeuDefenseur implements Jeu {

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
    static Logger logger = Logger.getLogger(JeuDefenseur.class);

    public JeuDefenseur (JeuParams jeuParams){
        this(0,0,jeuParams);
    }

    public JeuDefenseur(int victoire, int tourPartie, JeuParams jeuParams) {
        this.victoire = victoire;
        this.tourPartie = tourPartie;
        this.jeuParams = jeuParams;
    }

    public int getTourPartie() {
        return tourPartie;
    }

    public int getVictoire() {
        return victoire;
    }

    /**
     * This method is the lone method of this class
     * It enables to run as many loops as the number of rounds and to return the value of victoireJoueurDef
     * The variable victoireJoueurDef says if the Machine Player has found out the secret combinaison of the Human Player
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
        combinaisonsAuto.getCombinaisonMin().clear();
        combinaisonsAuto.getCombinaisonMax().clear();
        combinaisonsAuto.getCombinaisonModeTirageAuto().clear();
        combinaisonsParams.addNbCombinaisons();
        System.out.print("SAISIR VOTRE COMBINAISON SECRETE :  ");
        combinaisonManuelle.setModeTirageManuel(1);
        combinaisonManuelle.combiner();
        combinaisonsAuto.setModeTirageAuto(1);
        combinaisonsAuto.combiner();
        combinaisonsAuto.printCombinaison();
        playerJoueur.comparerLesListes();
        if (playerJoueur.getVictoire() == 2) {
            victoire = 2;
        } else if (playerJoueur.getVictoire() == 1) {
            victoire = 1;
            while (victoire == 1 && tourPartie < jeuParams.getNbTours()) {
                tourPartie++;
                playerMachine.lireComparaisonsListes();
                combinaisonsAuto.combiner();
                combinaisonsAuto.printCombinaison();
                playerJoueur.comparerLesListes();
                if (playerJoueur.getVictoire() == 1) {
                    victoire = 1;
                } else if (playerJoueur.getVictoire() == 2) {
                    victoire = 2;
                }
            }
        }
    }
}


