package com.ocr.jeanlouis34.escapegame;

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
 *
 * This class instances objects and methods of some Classes :
 * JeuBegin about the game characteristics,
 * PlayerJoueur about its method to handly compare both automatic and manual combinaisons,
 * Combinaisons, CombinaisonsAuto and CombinaisonManuelle about the size of the combinaisons and the methods to combine automatically and not
 *
 * This class objects are instanced only in class JeuDuel.
 * This clazs is one of the 5 classes of the first range (see javadoc of the Class JeuBegin) are invoked in the Class main.
 */

public class JeuDuel extends Jeu {

    private String ready1;
    private int victoireDuel;
    private int tourPartie;
    private Jeu jeu = new Jeu();
    private JeuDefenseur jeudefenseur;
    private JeuChallenger jeuchallenger;
    static Logger logger = Logger.getLogger(JeuDuel.class);

    public JeuDuel (Jeu jeu, JeuDefenseur jeudefenseur, JeuChallenger jeuchallenger) {
        this("",0,0,jeu, jeudefenseur, jeuchallenger);
    }

    public JeuDuel(String ready1, int victoireDuel, int tourPartie, Jeu jeu, JeuDefenseur jeudefenseur, JeuChallenger jeuchallenger) {
        this.ready1 = ready1;
        this.victoireDuel = victoireDuel;
        this.tourPartie = tourPartie;
        this.jeu = jeu;
        this.jeudefenseur = jeudefenseur;
        this.jeuchallenger = jeuchallenger;
    }

    public int getVictoireDuel() {
        return victoireDuel;
    }

    /**
     * This method is the lone method of this class
     * It theorically enables to do an infinite run of loops (although limited at 100 ...) in which alternate one round of JeuChallenger pattern and one round of JeuDefenseur pattern until one of them displays the victory signal.
     *
     * A the end of each loop, the player is asked if he wants to break the run of this pattern
     * in this case, he can move back to the Main Menu and choose if he wants to play one of the another patterns.
     */

    public void runJeu() {
            jeu.setNbTours(1) ;
            logger.info("Pour éviter de jouer jusqu'à l'infini, le nombre de tours maximum de la partie a été fixé à 100");
            logger.info("On va aussi te demander régulièrement si tu veux mettre fin à la partie DUEL.");
            String OUI = "OUI";
            do {
                logger.info("\nOn commence par le mode Challenger.\n");
                jeuchallenger.runJeu();
                if (jeuchallenger.getVictoire() == 1) {
                    victoireDuel =1;
                } else {
                    victoireDuel = 2;
                    logger.info("\nOn passe maintenant au mode Défenseur.\n");
                    jeudefenseur.runJeu();
                    if (jeudefenseur.getVictoire() == 2) {
                        victoireDuel = 3;
                    } else {
                        victoireDuel = 4;
                    }
                }
                logger.info("Si tu veux continuer à jouer cette partie de DUEL, réponds OUI : ");
                Scanner sc = new Scanner(System.in);
                try {
                    ready1 = sc.nextLine();
                } catch (InputMismatchException e) {
                    logger.info("La saisie n'est pas correcte. Il faut recommencer ...");
                    sc.next();
                    ready1 = sc.nextLine();
                }
            }while(ready1.equals(OUI));
            logger.info("Tu as décidé de mettre fin à cette partie en mode Duel. C'est noté.");
            victoireDuel = 5;
        }
    }










