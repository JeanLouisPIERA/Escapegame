package com.ocr.jeanlouis34.escapegame.jeu;

import org.apache.log4j.Logger;

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
