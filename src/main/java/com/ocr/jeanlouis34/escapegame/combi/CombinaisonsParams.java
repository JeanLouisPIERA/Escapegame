package com.ocr.jeanlouis34.escapegame.combi;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

/**
 * This Class enables to recover and store the predefined parameter in the properties file about the length of combinaisons
 */

public class CombinaisonsParams {

    static Logger logger = Logger.getLogger(CombinaisonsParams.class);
    // this variable enables to recover the parameter which is predifined in the properties file
    private int nbCombinaisons;

    public CombinaisonsParams() {
        this.nbCombinaisons = 0;
    }

    public CombinaisonsParams(int nbCombinaisons) {
        this.nbCombinaisons = nbCombinaisons;
    }

    public int getNbCombinaisons() {
        return nbCombinaisons;
    }

    /**
     * this is the lone method of this class to enable to define the length of both manual and automatic combinaisons during the game
     * it is a basic method that scan an int
     */
    public void addNbCombinaisons() {
        final Properties prop = new Properties();
        // this variable enables to store the value of the parameter by default that define the length of the combinaisons
        InputStream input = null;
        try {
            input = Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration.properties");
            prop.load(input);
            this.nbCombinaisons = Integer.parseInt(prop.getProperty("nbCombinaisonsByDefault"));
        } catch (final IOException ex) {
            logger.error(ex);
            Scanner sc = new Scanner(System.in).useDelimiter(" *");
            logger.info(" PROBLEME POUR UTILISER LE PARAMETRE PAR DEFAUT DE LONGUEUR DE L COMBINAISON. LE JOUEUR DOIT SAISIR CE PARAMETRE.");
            try {
                this.nbCombinaisons = sc.nextInt();
            } catch (InputMismatchException e) {
                logger.error("SAISIE INCORRECTE. LE PARAMETRE EST FIXE PAR DEFAUT A 4.");
                nbCombinaisons = 4;
            }
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (final IOException e) {
                    logger.error(e);
                }
            }
        }
    }
}

