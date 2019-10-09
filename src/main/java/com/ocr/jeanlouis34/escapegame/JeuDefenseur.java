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

public class JeuDefenseur extends Jeu {

    private int victoire;
    private int tourPartie;
    private Jeu jeu;
    private Combinaisons combinaisons = new Combinaisons();
    private CombinaisonsAuto combinaisonsAuto = new CombinaisonsAuto(combinaisons);
    private CombinaisonManuelle combinaisonManuelle = new CombinaisonManuelle(combinaisons);
    private PlayerJoueur playerJoueur = new PlayerJoueur(combinaisons, combinaisonsAuto, combinaisonManuelle);
    static Logger logger = Logger.getLogger(JeuDefenseur.class);

    public JeuDefenseur (Jeu jeu){
        this(0,0,jeu);
    }

    public JeuDefenseur(int victoire, int tourPartie, Jeu jeu) {
        this.victoire = victoire;
        this.tourPartie = tourPartie;
        this.jeu = jeu;
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

    public void runJeu() {
        tourPartie = 0;
        combinaisonManuelle.getCombinaison().clear();
        combinaisonsAuto.getCombinaison().clear();
        combinaisons.addNbCombinaisons();
        combinaisonManuelle.combiner();
        do {
            if (jeu.getModeDeveloper().equals("OUI")) {
                logger.info("\nJe rapelle que ta combinaison secrète est :");
                combinaisonManuelle.printCombinaison();
                logger.info("\nComparons step by step les 2 combinaisons ...");
            }
                combinaisonsAuto.combiner();
                playerJoueur.comparerLesListes();
                tourPartie++;
                if (playerJoueur.getVictoire() == 1) {
                    victoire = 1;
                    logger.info("Bravo " + jeu.getNomJoueur() + ". Tu t'es bien défendu.");
                } else if (playerJoueur.getVictoire() == 2) {
                    victoire = 2;
                    logger.info("Désolé " + jeu.getNomJoueur() + ". Le Player Machine a été plus fort que toi.");
                }
            }
            while (victoire == 1 && tourPartie < jeu.getNbTours());
        }
    }


