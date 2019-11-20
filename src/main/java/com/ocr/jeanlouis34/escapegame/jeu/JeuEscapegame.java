package com.ocr.jeanlouis34.escapegame.jeu;

import org.apache.log4j.Logger;

/**
 * This class enables to launch the gme with at choice one of the 3 patterns of this game.
 *
 *  This class instances objects and methods of some Classes :
 *  * CombinaisonsParams and JeuParams about predefined parameters bu default which define the game characteristics,
 *  * PlayerJoueur about its method used by the human player to manuallyally compare both automatic and manual combinaisons,
 *  * PlayerMachine about its method used by the Machine to automatically compare both automatic and manual combinaisons,
 *  * Combinaisons, CombinaisonsAuto and CombinaisonManuelle about the size of the combinaisons and the methods to combine automatically and not
 */

public class JeuEscapegame implements Jeu {

    private JeuParams jeuParams;
    private JeuChallenger jeuChallenger;
    private JeuDefenseur jeuDefenseur;
    private JeuDuel jeuDuel;
    static Logger logger = Logger.getLogger(JeuEscapegame.class);

    public JeuEscapegame(JeuParams jeuParams, JeuChallenger jeuChallenger, JeuDefenseur jeuDefenseur, JeuDuel jeuDuel) {
        this.jeuParams = jeuParams;
        this.jeuChallenger = jeuChallenger;
        this.jeuDefenseur = jeuDefenseur;
        this.jeuDuel = jeuDuel;
    }

    /**
     * this method is in the lone one to be invoked in the class Main with the appropriate constructor
     */

    @Override
    public void runJeu() {
        jeuParams.runModeJeu();
        do {
            switch (jeuParams.getModeJeu()) {
                case 1:
                    jeuChallenger.runJeu();
                    if (jeuChallenger.getVictoire() == 1 || jeuChallenger.getTourPartie() == jeuParams.getNbTours()) {
                        jeuParams.finirlapartie();
                    }
                    break;
                case 2:
                    jeuDefenseur.runJeu();
                    if (jeuDefenseur.getVictoire() == 2 || jeuDefenseur.getTourPartie() == jeuParams.getNbTours()) {
                        jeuParams.finirlapartie();
                    }
                    break;
                case 3:
                    jeuDuel.runJeu();
                    if (jeuDuel.getVictoireDuel() == 1 || jeuDuel.getVictoireDuel() == 4) {
                        jeuParams.finirlapartie();
                    }
                    break;
                default:
                    logger.info("SAISIE INCORRECTE. GAME OVER");
                    System.exit(1);
                    break;
            }
        } while (jeuParams.getReady().equals("OUI"));
    }
}
