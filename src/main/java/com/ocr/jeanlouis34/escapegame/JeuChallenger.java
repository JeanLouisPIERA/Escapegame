package com.ocr.jeanlouis34.escapegame;

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

public class JeuChallenger {

    private int victoireJoueur;
    private int tourPartie;
    private JeuBegin jeubegin = new JeuBegin();
    private Combinaisons combinaisons = new Combinaisons();
    private CombinaisonsAuto combinaisonsAuto = new CombinaisonsAuto(combinaisons);
    private CombinaisonManuelle combinaisonManuelle = new CombinaisonManuelle(combinaisons);
    private PlayerMachine playerMachine = new PlayerMachine(combinaisons, combinaisonsAuto, combinaisonManuelle);
    private JeuEnd jeuEnd = new JeuEnd(jeubegin);
    static Logger logger = Logger.getLogger(JeuChallenger.class);

    public JeuChallenger(JeuBegin jeubegin, JeuEnd jeuEnd) {
        this(0, 0, jeubegin, jeuEnd);
    }

    public JeuChallenger(int victoireJoueur, int tourPartie, JeuBegin jeubegin, JeuEnd jeuEnd) {
        this.victoireJoueur = victoireJoueur;
        this.tourPartie = tourPartie;
        this.jeubegin = jeubegin;
        this.jeuEnd = jeuEnd;
    }

    public int getTourPartie() {
        return tourPartie;
    }

    public void setTourPartie(int tourPartie) {
        this.tourPartie = tourPartie;
    }

    public int getVictoireJoueur() {
        return victoireJoueur;
    }

    /**
     * This method is the lone method of this class
     * It enables to run as many loops as the number of rounds and to return the value of victoireJoueur
     * The variable victoireJoueur says if the Human Player has won
     *
     * In the code, some lines are surrounded by signs to neutralize loggers and enable to play in real
     * The loggers have not been erased to enable to test the program in his different issues
     */

    public void runJeuChallenger() {
        tourPartie = 0;
        combinaisonManuelle.getCombinaisonManuelle().clear();
        combinaisonsAuto.getCombinaisonsAuto().clear();
        combinaisons.addNbCombinaisons();
        combinaisonsAuto.combinerAuto();
            do {
                /*combinaisonsAuto.printCombinaisonsAuto();*/
                combinaisonManuelle.combinerManuelle();
                /*combinaisonManuelle.printCombinaisonManuelle();*/
                playerMachine.comparerLesListesAuto();
                tourPartie++;
                if (playerMachine.getVictoireChallenger() == 1) {
                    victoireJoueur = 1;
                    logger.info("Bravo " + jeubegin.getNomJoueur() + ". Tu as gagné.");
                } else if (playerMachine.getVictoireChallenger() == 2) {
                    victoireJoueur = 2;
                    logger.info("Désolé " + jeubegin.getNomJoueur() + ". Tu n'as pas gagné.");
                }
            }
            while (victoireJoueur == 2 && tourPartie < jeubegin.getNbTours());
        }
    }

