package com.ocr.jeanlouis34.escapegame;


import com.ocr.jeanlouis34.escapegame.combi.CombinaisonManuelle;
import com.ocr.jeanlouis34.escapegame.combi.CombinaisonsAuto;
import com.ocr.jeanlouis34.escapegame.combi.CombinaisonsParams;
import com.ocr.jeanlouis34.escapegame.jeu.*;
import com.ocr.jeanlouis34.escapegame.player.PlayerJoueur;
import com.ocr.jeanlouis34.escapegame.player.PlayerMachine;
import org.apache.log4j.Logger;


public class Main {

    public static void main(String[] args) {

        JeuParams jeuParams = new JeuParams();
        JeuChallenger jeuChallenger = new JeuChallenger(jeuParams);
        JeuDefenseur jeuDefenseur = new JeuDefenseur(jeuParams);
        JeuDuel jeuDuel = new JeuDuel(jeuParams);
        JeuEscapegame jeuEscapegame = new JeuEscapegame(jeuParams, jeuChallenger, jeuDefenseur, jeuDuel);

        jeuEscapegame.runJeu();

    }
}






