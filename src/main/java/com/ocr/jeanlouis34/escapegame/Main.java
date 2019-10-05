package com.ocr.jeanlouis34.escapegame;


import org.apache.log4j.Logger;

public class Main {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(Main.class);

        JeuBegin jeubegin = new JeuBegin();
        JeuEnd jeuEnd = new JeuEnd(jeubegin);
        JeuChallenger jeuChallenger = new JeuChallenger(jeubegin, jeuEnd);
        JeuDefenseur jeuDefenseur = new JeuDefenseur(jeubegin, jeuEnd);
        JeuDuel jeuDuel = new JeuDuel(jeubegin, jeuDefenseur, jeuChallenger);

        jeubegin.addNomJoueur();
        jeubegin.runModeJeu();
        do {
            switch (jeubegin.getModeJeu()) {
                case 1:
                    jeuChallenger.runJeuChallenger();
                    if (jeuChallenger.getVictoireJoueur() == 1 || jeuChallenger.getTourPartie() == jeubegin.getNbTours()) {
                        jeuEnd.finirlapartie();
                    }
                    break;
                case 2:
                    jeuDefenseur.runJeuDefenseur();
                    if (jeuDefenseur.getVictoireJoueurDef() == 2 || jeuDefenseur.getTourPartie1() == jeubegin.getNbTours()) {
                        jeuEnd.finirlapartie();
                    }
                    break;
                case 3:
                    jeuDuel.runJeuDuel();
                    switch (jeuDuel.getVictoireJoueurDuel()) {
                        case 1 :
                        case 3 :
                        case 5 :
                            jeuEnd.finirlapartie();
                            break;
                        case 2 :
                            jeuDefenseur.runJeuDefenseur();
                            break;
                        case 4 :
                            jeuChallenger.runJeuChallenger();
                            break;
                    }
                    break;
                default:
                    logger.info("Aucun de tes choix n'est le bon. GAME OVER");
                    System.exit(1);
                    break;
            }
        } while (jeuEnd.getReady().equals("OUI"));
    }
}






