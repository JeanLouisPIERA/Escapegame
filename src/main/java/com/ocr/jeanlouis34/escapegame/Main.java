package com.ocr.jeanlouis34.escapegame;


import com.ocr.jeanlouis34.escapegame.jeu.JeuChallenger;
import com.ocr.jeanlouis34.escapegame.jeu.JeuDefenseur;
import com.ocr.jeanlouis34.escapegame.jeu.JeuDuel;
import com.ocr.jeanlouis34.escapegame.jeu.JeuParams;
import org.apache.log4j.Logger;


public class Main {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(Main.class);

        JeuParams jeuParams = new JeuParams();
        JeuChallenger jeuChallenger = new JeuChallenger(jeuParams);
        JeuDefenseur jeuDefenseur = new JeuDefenseur(jeuParams);
        JeuDuel jeuDuel = new JeuDuel(jeuParams, jeuDefenseur, jeuChallenger);

        jeuParams.addNomJoueur();
        jeuParams.printNomJoueur();
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
                    switch (jeuDuel.getVictoireDuel()) {
                        case 1 :
                        case 3 :
                        case 5 :
                            jeuParams.finirlapartie();
                            break;
                        case 2 :
                            jeuDefenseur.runJeu();
                            break;
                        case 4 :
                            jeuChallenger.runJeu();
                            break;
                    }
                    break;
                default:
                    logger.info("Aucun de tes choix n'est le bon. GAME OVER");
                    System.exit(1);
                    break;
            }
        } while (jeuParams.getReady().equals("OUI"));
    }
}






