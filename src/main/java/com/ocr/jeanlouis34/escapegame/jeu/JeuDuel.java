package com.ocr.jeanlouis34.escapegame.jeu;

import com.ocr.jeanlouis34.escapegame.combi.CombinaisonManuelle;
import com.ocr.jeanlouis34.escapegame.combi.CombinaisonsAuto;
import com.ocr.jeanlouis34.escapegame.combi.CombinaisonsParams;
import com.ocr.jeanlouis34.escapegame.player.PlayerJoueur;
import com.ocr.jeanlouis34.escapegame.player.PlayerMachine;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class run alternatively methods of twin-classes JeuChallenger and Jeudefenseur.
 *
 * This class defines the characteristics of one of the 3 patterns :
 * In the Duel pattern, both other patterns are played each one after the other, beginning with the Challenge pattern
 *  *
 * This Class enables to run as many loops as the number of rounds and to return the value of both victoireJoueur and victoireJoueurDef

 *  This class instances objects and methods of some Classes :
 *  * CombinaisonsParams and JeuParams about predefined parameters bu default which define the game characteristics,
 *  * PlayerJoueur about its method used by the human player to manuallyally compare both automatic and manual combinaisons,
 *  * PlayerMachine about its method used by the Machine to automatically compare both automatic and manual combinaisons,
 *  * Combinaisons, CombinaisonsAuto and CombinaisonManuelle about the size of the combinaisons and the methods to combine automatically and not
*/

public class JeuDuel implements Jeu {

    private String ready1;
    // this variable enables to store the result of each round of the game
    private int victoireDuel;
    // this variable enables to count and register the number of rounds that have been played in the game
    private int tourPartie;
    private JeuParams jeuParams = new JeuParams();
    private CombinaisonsParams combinaisonsParams = new CombinaisonsParams();
    private CombinaisonsAuto combinaisonsAuto = new CombinaisonsAuto(combinaisonsParams);
    private CombinaisonManuelle combinaisonManuelle = new CombinaisonManuelle(combinaisonsParams);
    private PlayerJoueur playerJoueur = new PlayerJoueur(combinaisonsParams, combinaisonsAuto, combinaisonManuelle);
    private PlayerMachine playerMachine = new PlayerMachine(combinaisonsParams, combinaisonsAuto, combinaisonManuelle, playerJoueur);
    static Logger logger = Logger.getLogger(JeuDuel.class);

    public JeuDuel(JeuParams jeuParams) {
        this("", 0, 0, jeuParams);
    }

    public JeuDuel(String ready1, int victoireDuel, int tourPartie, JeuParams jeuParams) {
        this.ready1 = ready1;
        this.victoireDuel = victoireDuel;
        this.tourPartie = tourPartie;
        this.jeuParams = jeuParams;
    }

    public int getVictoireDuel() {
        return victoireDuel;
    }

    /**
     * This method is the lone method of this class
     * It theorically enables to do an infinite run of loops (although limited at 100 ...) in which alternate one round of JeuChallenger pattern and one round of JeuDefenseur pattern until one of them displays the victory signal.
     * <p>
     */
    @Override
    public void runJeu() {
        tourPartie = 0;
        combinaisonManuelle.getCombinaison().clear();
        combinaisonsAuto.getCombinaison().clear();
        combinaisonsAuto.getCombinaisonSecrete().clear();
        combinaisonManuelle.getCombinaisonSecrete().clear();
        combinaisonsParams.addNbCombinaisons();

        logger.info("ROUND CHALLENGER");
        combinaisonsAuto.setModeTirageAuto(5);
        combinaisonsAuto.combiner();
        if (jeuParams.getModeDeveloper().equals("OUI")) {
            logger.info("\n(COMBINAISON SECRETE DE LA MACHINE :" + combinaisonsAuto.getCombinaison() + ")");
        }
        System.out.print("VOTRE PROPOSITION :    ");
        combinaisonManuelle.setModeTirageManuel(2);
        combinaisonManuelle.combiner();
        playerMachine.comparerLesListes();

        if (playerMachine.getVictoire() == 1) {
            victoireDuel = 1;
        } else if (playerMachine.getVictoire() == 2) {

            logger.info("\nROUND DEFENSEUR");
            System.out.print("SAISIR VOTRE COMBINAISON SECRETE :  ");
            combinaisonManuelle.setModeTirageManuel(1);
            combinaisonManuelle.combiner();
            combinaisonsAuto.setModeTirageAuto(1);
            combinaisonsAuto.combiner();
            combinaisonsAuto.printCombinaison();
            playerJoueur.comparerLesListes();

            if (playerJoueur.getVictoire() == 2) {
                victoireDuel = 4;
            } else if (playerJoueur.getVictoire() == 1) {
                do {
                    logger.info("\nNOUVEAU ROUND CHALLENGER");
                    System.out.print("VOTRE PROPOSITION :    ");
                    combinaisonManuelle.setModeTirageManuel(2);
                    combinaisonManuelle.combiner();

                    playerMachine.comparerLesListes();

                    if (playerMachine.getVictoire() == 1) {
                        victoireDuel = 1;
                    } else if (playerMachine.getVictoire() == 2) {
                        logger.info("\nNOUVEAU ROUND DEFENSEUR");
                        playerMachine.lireComparaisonsListes();
                        combinaisonsAuto.combiner();
                        combinaisonsAuto.printCombinaison();
                        playerJoueur.comparerLesListes();

                        if (playerJoueur.getVictoire() == 2) {
                            victoireDuel = 4;
                        } else if (playerJoueur.getVictoire() == 1) {
                            victoireDuel = 3;
                        }
                    }
                }while (victoireDuel == 2 || victoireDuel == 3) ;
            }
        }
    }
}










