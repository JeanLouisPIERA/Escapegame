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

public class JeuChallenger extends Jeu {

    private int victoire;
    private int tourPartie;
    private Jeu jeu;
    private Combinaisons combinaisons = new Combinaisons();
    private CombinaisonsAuto combinaisonsAuto = new CombinaisonsAuto(combinaisons);
    private CombinaisonManuelle combinaisonManuelle = new CombinaisonManuelle(combinaisons);
    private PlayerMachine playerMachine = new PlayerMachine(combinaisons, combinaisonsAuto, combinaisonManuelle);
    static Logger logger = Logger.getLogger(JeuChallenger.class);

    public JeuChallenger(Jeu jeu) {
        this(0,0, jeu);
    }

    public JeuChallenger(int victoire, int tourPartie, Jeu jeu) {
        this.victoire = victoire;
        this.tourPartie = tourPartie;
        this.jeu = jeu;
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

    public void runJeu() {
        tourPartie = 0;
        combinaisonManuelle.getCombinaison().clear();
        combinaisonsAuto.getCombinaison().clear();
        combinaisons.addNbCombinaisons();
        combinaisonsAuto.combiner();
            do {
                if (jeu.getModeDeveloper().equals("OUI")) {
                    logger.info("\nLa combinaison secrète de la Machine est :");
                    combinaisonsAuto.printCombinaison();
                    logger.info("\nComparons step by step les 2 combinaisons ...");
                }
                combinaisonManuelle.combiner();
                playerMachine.comparerLesListes();
                tourPartie++;
                if (playerMachine.getVictoire() == 1) {
                    victoire = 1;
                    logger.info("Bravo " + jeu.getNomJoueur() + ". Tu as gagné.");
                } else if (playerMachine.getVictoire() == 2) {
                    victoire = 2;
                    logger.info("Désolé " + jeu.getNomJoueur() + ". Tu n'as pas gagné.");
                }
            }
            while (victoire == 2 && tourPartie < jeu.getNbTours());
        }
    }

