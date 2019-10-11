package com.ocr.jeanlouis34.escapegame;


import org.apache.log4j.Logger;

public class Main {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(Main.class);

        Jeu jeu = new Jeu();
        JeuChallenger jeuChallenger = new JeuChallenger(jeu);
        JeuDefenseur jeuDefenseur = new JeuDefenseur(jeu);
        JeuDuel jeuDuel = new JeuDuel(jeu, jeuDefenseur, jeuChallenger);

        jeu.addNomJoueur();
        jeu.printNomJoueur();
        jeu.runModeJeu();
        do {
            switch (jeu.getModeJeu()) {
                case 1:
                    jeuChallenger.runJeu();
                    if (jeuChallenger.getVictoire() == 1 || jeuChallenger.getTourPartie() == jeu.getNbTours()) {
                        jeu.finirlapartie();
                    }
                    break;
                case 2:
                    jeuDefenseur.runJeu();
                    if (jeuDefenseur.getVictoire() == 2 || jeuDefenseur.getTourPartie() == jeu.getNbTours()) {
                        jeu.finirlapartie();
                    }
                    break;
                case 3:
                    jeuDuel.runJeu();
                    switch (jeuDuel.getVictoireDuel()) {
                        case 1 :
                        case 3 :
                        case 5 :
                            jeu.finirlapartie();
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
        } while (jeu.getReady().equals("OUI"));
    }
}






