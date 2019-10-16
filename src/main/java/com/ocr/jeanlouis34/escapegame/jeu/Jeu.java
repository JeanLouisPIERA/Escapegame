package com.ocr.jeanlouis34.escapegame.jeu;

import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public interface Jeu {

    /**
     * This abstract method is only one taken over in the sub classes of the Class Jeu that is JeuChallenger, JeuDefenseur & JeuDuel
     */

    public void runJeu();
}



