package com.ocr.jeanlouis34.escapegame;

import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is the twin class of the class jeubegin.
 * It uses the variables runModeJeu.
 *
 * It avoids to have a stackoverflow at the exit of the 3 operative Classes : JeuChallenger, JeuDefenseur and JeuDuel.
 * All of these 3 classes only return but a result which is analyzed in the Main Class.
 * If the result is a defeat or if the number of rounds is ended, the only method of the Class Jeuend is invoked.
 * It enables to propose on more time at the end of each pattern the choice of running or not the game between the 3 operative patterns.
 */

public class JeuEnd {

    static Logger logger = Logger.getLogger(JeuEnd.class);
    private String ready;
    private int choixJeuEnd;
    private JeuBegin jeubegin;


    public JeuEnd(JeuBegin jeubegin) {
        this("", 0,jeubegin);
    }

    public JeuEnd(String ready, int choixJeuEnd, JeuBegin jeubegin) {
        this.ready = ready;
        this.choixJeuEnd = choixJeuEnd;
        this.jeubegin = jeubegin;
    }

    public String getReady() {
        return ready;
    }

    /**
     * The lone method of the class
     * This method enables to choose one more time between the three operative patterns.
     */

    public void finirlapartie() {
        logger.info("La précédente partie est finie. \nQue veux tu faire maintenant ?");
        logger.info("Si tu veux continuer à jouer, réponds OUI : ");
        String OUI = "OUI";
        Scanner sc = new Scanner(System.in);
        try {
            ready = sc.nextLine();
        } catch (InputMismatchException e) {
            logger.info("La saisie n'est pas correcte. Il faut recommencer ...");
            sc.next();
            ready = sc.nextLine();
        }
        if (ready.equals(OUI)) {
            logger.info("Quel mode de jeu souhaites-tu jouer ? Un patit rappel.");
            jeubegin.runModeJeu();
            switch (jeubegin.getModeJeu()) {
                case 1:
                    choixJeuEnd = 1;
                    break;
                case 2:
                    choixJeuEnd = 2;
                    break;
                case 3:
                    choixJeuEnd = 3;
                    break;
                default:
                    logger.info("Ne réponds pas n'importe quoi pour éviter le combat. Pour que tu comprennes bien, bis repetita ...");
                    finirlapartie();
                    break;
            }
        } else {
            logger.info("Tu as choisi de quitter l'application. J'espère te revoir bientôt et te souhaite une bonne journée " + jeubegin.getNomJoueur());
            System.exit(0);
        }
    }
}



