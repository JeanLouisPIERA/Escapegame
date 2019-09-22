package com.ocr.jeanlouis34.escapegame;

import org.apache.log4j.Logger;

public class JeuChallenger {

    JeuBegin jeubegin = new JeuBegin();
    PlayerMachine playerMachine = new PlayerMachine();
    static Logger logger = Logger.getLogger(JeuChallenger.class);

    public JeuChallenger() {
    }

    public void runJeuChallenger(){
        jeubegin.addNomJoueur();
        jeubegin.runModeJeu();
        jeubegin.addNbCombinaisons();
        playerMachine.combinerAuto();
        playerMachine.printCombinaisonsAuto();
        playerMachine.combinerManuelle();
        playerMachine.printCombinaisonsManuelle();
        playerMachine.comparerleslistes();
    }




}
