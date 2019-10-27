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
            input = new FileInputStream("src/main/resources/configuration.properties");
            // chargement du fichier properties
            prop.load(input);
            // récupération et impression de la valeur de la propriété
            logger.info("Comme je te l'ai dit, une combinaison secrète doit être découverte. \nPar défaut le nombre de chiffres de cette combinaison est : ");
            System.out.println(prop.getProperty("nbCombinaisonsByDefault"));
        } catch (final IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Scanner sc = new Scanner(System.in).useDelimiter(" *");
        logger.info("Si ce chiffre par défaut te convient, tape le à nouveau pour confirmer sinon indique combien tu veux que cette combinaison contienne de chiffres ?");
        try {
            logger.info("Attention, pense à entrer un nombre chiffre positif entre 2 et 9 ...");
            this.nbCombinaisons = sc.nextInt();
        } catch (InputMismatchException ex) {
            logger.error("La saisie n'est pas correcte. On recommence à zéro ...");
            sc.next();
            addNbCombinaisons();
        }
        int i = Integer.parseInt(prop.getProperty("nbCombinaisonsByDefault"));
        if(nbCombinaisons!=i) {
            OutputStream output = null;
            String nbCombinaisonsModified = String.valueOf(nbCombinaisons);
            try {

                output = new FileOutputStream("src/main/resources/configuration.properties");

                // modifier la valeur
                prop.setProperty("nbCombinaisonsModified", nbCombinaisonsModified);

                // sauver la valeur dans le fichier configuration.properties
                prop.store(output," Ce fichier de configuration présente 3 paramètres modifiables : \nle nombre de chiffres dans la combinaison (nbCombinaisons), \nle nombre de tours dans la partie (nbTours) \net le mode Développeur (modeDéveloper) \nLe suffixe ByDefault est accolé pour définir la valeur par défaut. \nLe suffixe Modified est accolé pour indiquer sa valeur lors de la dennière partie.");

            } catch (final IOException io) {
                io.printStackTrace();
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
    }


    /**
     * this method enables to display the length choosen by the player for the combinaison
     */
    public void printNbcombinaisons() {
        if (nbCombinaisons <= 9 && nbCombinaisons > 1) {
            logger.info("C'est noté. \nTu as décidé que la combinaison secrète auras une longueur de " + this.nbCombinaisons +"  chiffres.");
            logger.info("Pour connaitre le nombre de combinaisons possibles, multiplie 10 par lui même autant de fois que tu as choisi de chiffres dans ta combinaison");
        } else {
            logger.info("Ta saisie n'est pas correcte. Je fixe donc la longueur de la combinaison secrète à 4 chiffres.");
            this.nbCombinaisons = 4;
        }
    }
}
