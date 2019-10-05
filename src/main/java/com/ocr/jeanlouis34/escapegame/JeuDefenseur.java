package com.ocr.jeanlouis34.escapegame;

import org.apache.log4j.Logger;

/**
 * Twin class of the Class JeuChallenger
 *
 * This class defines the characteristics of one of the 3 patterns :
 * In the Defenseur pattern : the Machine has to find out the combinaison created by the Player,
 * This Class enables to run as many loops as the number of rounds and to return the value of victoireJoueurDef
 * The variable victoireJoueurDef says if the Human Player has won that is if the Player Machine has not been able to find out the combinaison defined by the Huùan Player
 *
 * This class instances objects and methods of some Classes :
 * JeuBegin about the game characteristics,
 * PlayerJoueur about its method to handly compare both automatic and manual combinaisons,
 * Combinaisons, CombinaisonsAuto and CombinaisonManuelle about the size of the combinaisons and the methods to combine automatically and not
 *
 * This class objects are instanced only in class JeuDuel.
 * This class is one of the 5 classes of the first range (see javadoc of the Class JeuBegin)are invoked in the Class main.
 */

public class JeuDefenseur {

    private int victoireJoueurDef;
    private int tourPartie1;
    private JeuBegin jeubegin = new JeuBegin();
    private Combinaisons combinaisons = new Combinaisons();
    private CombinaisonsAuto combinaisonsAuto = new CombinaisonsAuto(combinaisons);
    private CombinaisonManuelle combinaisonManuelle = new CombinaisonManuelle(combinaisons);
    private PlayerJoueur playerJoueur = new PlayerJoueur(combinaisons, combinaisonsAuto, combinaisonManuelle);
    private JeuEnd jeuEnd = new JeuEnd(jeubegin);
    static Logger logger = Logger.getLogger(JeuChallenger.class);

    public JeuDefenseur(JeuBegin jeubegin, JeuEnd jeuEnd) {
        this(0, 0, jeubegin, jeuEnd);
    }

    public JeuDefenseur(int victoireJoueurDef, int tourPartie1, JeuBegin jeubegin, JeuEnd jeuEnd) {
        this.victoireJoueurDef = victoireJoueurDef;
        this.tourPartie1 = tourPartie1;
        this.jeubegin = jeubegin;
        this.jeuEnd = jeuEnd;
    }

    public int getTourPartie1() {
        return tourPartie1;
    }

    public int getVictoireJoueurDef() {
        return victoireJoueurDef;
    }

    /**
     * This method is the lone method of this class
     * It enables to run as many loops as the number of rounds and to return the value of victoireJoueurDef
     * The variable victoireJoueurDef says if the Machine Player has found out the secret combinaison of the Human Player
     *
     * In the code, some lines are surrounded by signs to neutralize loggers and enable to play in real
     * The loggers have not been erased to enable to test the program in his different issues
     */

    public void runJeuDefenseur() {
        tourPartie1 = 0;
        combinaisonManuelle.getCombinaisonManuelle().clear();
        combinaisonsAuto.getCombinaisonsAuto().clear();
        combinaisons.addNbCombinaisons();
        combinaisonManuelle.combinerManuelle();
        do {
                /*combinaisonManuelle.printCombinaisonManuelle();*/
                combinaisonsAuto.combinerAuto();
                /*combinaisonsAuto.printCombinaisonsAuto();*/
                playerJoueur.comparerLesListesManuelle();
                tourPartie1++;
                if (playerJoueur.getVictoireDefenseur() == 1) {
                    victoireJoueurDef = 1;
                    logger.info("Bravo " + jeubegin.getNomJoueur() + ". Tu t'es bien défendu.");
                } else if (playerJoueur.getVictoireDefenseur() == 2) {
                    victoireJoueurDef = 2;
                    logger.info("Désolé " + jeubegin.getNomJoueur() + ". Le Player Machine a été plus fort que toi.");
                }
            }
            while (victoireJoueurDef == 1 && tourPartie1 < jeubegin.getNbTours());
        }
    }


