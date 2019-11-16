package com.ocr.jeanlouis34.escapegame.jeu;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

public class JeuParams {

    private int modeJeu;
    private int nbTours;
    private String ready;
    private int choixJeu;
    private int victoire;
    private int tourPartie;
    private String ready1;
    private String modeDeveloper;
    private int nbCombinaisons;
    static Logger logger = Logger.getLogger(JeuParams.class);

    public JeuParams() {
        this(0 ,0,"",0,0,0,"","", 0);
    }

    public JeuParams(int modeJeu, int nbTours, String ready, int choixJeu, int victoire, int tourPartie, String ready1, String modeDeveloper, int nbCombinaisons) {
        this.modeJeu = modeJeu;
        this.nbTours = nbTours;
        this.ready = ready;
        this.choixJeu = choixJeu;
        this.victoire = victoire;
        this.tourPartie = tourPartie;
        this.ready1 = ready1;
        this.modeDeveloper = modeDeveloper;
        this.nbCombinaisons = nbCombinaisons;
    }

    public int getModeJeu() { return modeJeu;
    }

    /**
     * This is a sub-method of the method of this Class runModeJeu
     * This method enables to scan the number of the choosen game pattern
     *
     * Inputmismatch Exception if the human player answer with a letter, a word or a negative number.
     * If he writes a number greater than 10, only the first int is scanned.
     */
    public void addModeJeu () {
        Scanner sc = new Scanner(System.in)/*.useDelimiter(" *")*/ ;
        try {
            this.modeJeu = sc.nextInt();
        } catch (InputMismatchException ex) {
            logger.error("SAISIE INCORRECTE");
            this.modeJeu = 0;
            sc.next();
            displayAvailableModeJeu();
            addModeJeu();
        }
    }

    public int getNbTours() {
        return nbTours;
    }

    public void setNbTours(int nbTours) {
        this.nbTours = nbTours;
    }

    /**
     * This method enables to define the number of rounds for each of both patterns JeuChallenger and JeuDefenseur
     * The pattern JeuDuel is not intended
     *
     * Inputmismatch Exception if the human player answer with a letter, a word or a negative number.
     * If he writes a number greater than 10, only the first int is scanned.
     *
     */
    public void addNbTours () {
        final Properties prop = new Properties();
        InputStream input = null;
        try {
            input = Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration.properties");
            // chargement du fichier properties
            prop.load(input);
            this.nbTours= Integer.parseInt(prop.getProperty("nbToursByDefault"));
            // récupération de la valeur de la propriété
        } catch (final IOException ex) {
            logger.error(ex);
            Scanner sc = new Scanner(System.in).useDelimiter(" *");
            logger.info(" PROBLEME POUR UTILISER LE PARAMETRE PAR DEFAUT DU NOMBRE DE TOURS DE LA PARTIE. LE JOUEUR DOIT SAISIR CE PARAMETRE.");
            try {
                this.nbTours = sc.nextInt();
            } catch (InputMismatchException e) {
                logger.error("SAISIE INCORRECTE. LA PARTIE VA SE JOUER PAR DEFAUT EN 5 TOURS.");
                nbTours = 2;
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

    /**
     * This is a sub-method of the method of this Class runModeJeu
     */
    public void displayAvailableModeJeu(){
        logger.info ("\nPOUR CHOISIR LE MODE DE JEU TAPER 1 (CHALLENGER), 2(DEFENSEUR) OU 3 (DUEL) : ");
    }

    public String getModeDeveloper() {
        return modeDeveloper;
    }

    /**
     * This is a sub-method of the displaySeletedModeJeu which is a sub-method of the Class runModeJeu
     */
    public void modeDeveloper() {
        final Properties prop = new Properties();
        InputStream input = null;
        try {
            input = Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration.properties");
            // chargement du fichier properties
            prop.load(input);
            this.modeDeveloper = prop.getProperty("modeDeveloperByDefault");
            // récupération de la valeur de la propriété
        } catch (final IOException ex) {
            logger.error(ex);
            this.modeDeveloper = "NON";
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

    /**
     * This is a sub-method of the method of this Class runModeJeu
     */
    public void displaySelectedModeJeu() {
        switch (modeJeu) {
            case 1:
                logger.info("PARTIE EN MODE CHALLENGER");
                modeDeveloper();
                addNbTours();
                break;
            case 2:
                logger.info("PARTIE EN MODE DEFENSEUR");
                modeDeveloper();
                addNbTours();
                break;
            case 3:
                logger.info("PARTIE EN MODE DUEL");
                modeDeveloper();
                nbTours = 100;
                break;
            default:
                logger.info("SAISIE INCORRECTE.");
                runModeJeu();
                break;
        }
    }

    /**
     * This method enables to scan the pattern choosen by the Player and to display the Rules and the results
     * This method is run in the Main ClassThis method
     */
    public void runModeJeu() {
        this.displayAvailableModeJeu();
        addModeJeu();
        this.displaySelectedModeJeu();
    }

    public String getReady() {
        return ready;
    }

    /**
     * The lone method of the class
     * This method enables to choose one more time between the three operative patterns.
     */
    public void finirlapartie() {
        logger.info("\nPOUR JOUER UNE NOUVELLE PARTIE, TAPER OUI ");
        Scanner sc = new Scanner(System.in);
        try {
            ready = sc.nextLine();
        } catch (InputMismatchException e) {
            logger.info("SAISIE INCORRECTE.");
            sc.next();
            ready = sc.nextLine();
        }
        if (ready.equals("OUI") || ready.equals("Oui") || ready.equals("oUi") || ready.equals("ouI") || ready.equals("oUI") || ready.equals("OUi") || ready.equals("oui")) {
            ready = "OUI";
            runModeJeu();
            switch (modeJeu) {
                case 1:
                    choixJeu = 1;
                    break;
                case 2:
                    choixJeu = 2;
                    break;
                case 3:
                    choixJeu = 3;
                    break;
                default:
                    logger.info("SAISIE INCORRECTE.");
                    addModeJeu();
                    break;
            }
        } else {
            logger.info("FIN DE PARTIE.");
            System.exit(0);
        }
    }
}
