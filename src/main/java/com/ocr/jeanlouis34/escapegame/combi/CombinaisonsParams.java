package com.ocr.jeanlouis34.escapegame.combi;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

public class CombinaisonsParams {

    static Logger logger = Logger.getLogger(CombinaisonsParams.class);
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
        InputStream input = null;
        try {
            input = Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration.properties");
            // chargement du fichier properties
            prop.load(input);
            this.nbCombinaisons = Integer.parseInt(prop.getProperty("nbCombinaisonsByDefault"));
            // récupération de la valeur de la propriété
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

