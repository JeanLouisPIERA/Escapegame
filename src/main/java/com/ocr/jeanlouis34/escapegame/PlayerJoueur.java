package com.ocr.jeanlouis34.escapegame;


import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * This class is the twin class of Player Machine.
 *
 * This class is only linked with the JeuDefenseur Class in the Defenseur pattern.
 *
 * This class enables to compare both automatically and handly made combinaisons.
 *
 * That is why it uses the variables of all the classes about COMBINAISON.
 *
 * WARNING : I have introduced a overrated level which enables to detect if the Human Player have cheated.
 */

public class PlayerJoueur {

    static Logger logger = Logger.getLogger(PlayerJoueur.class);
    private int victoireDefenseur;
    private int ca;
    private int cm;
    private String cr;
    private List<String> comparaisonsListesManuelle;
    private Combinaisons combinaisons;
    private CombinaisonsAuto combinaisonsAuto;
    private CombinaisonManuelle combinaisonManuelle;
    private JeuBegin jeubegin = new JeuBegin();

    public PlayerJoueur(Combinaisons combinaisons, CombinaisonsAuto combinaisonsAuto, CombinaisonManuelle combinaisonManuelle) {
        this(0,0,0,"",new ArrayList<>(), combinaisons, combinaisonsAuto, combinaisonManuelle);
    }


    public PlayerJoueur(int victoireDefenseur, int ca, int cm, String cr, List<String> comparaisonsListesManuelle, Combinaisons combinaisons, CombinaisonsAuto combinaisonsAuto, CombinaisonManuelle combinaisonManuelle) {
        this.victoireDefenseur = victoireDefenseur;
        this.ca = ca;
        this.cm = cm;
        this.cr = cr;
        this.comparaisonsListesManuelle = comparaisonsListesManuelle;
        this.combinaisons = combinaisons;
        this.combinaisonsAuto = combinaisonsAuto;
        this.combinaisonManuelle = combinaisonManuelle;
    }

    public List<String> getComparaisonsListesManuelle() {
        return comparaisonsListesManuelle;
    }

    public void setComparaisonsListesManuelle(List<String> comparaisonsListesManuelle) {
        this.comparaisonsListesManuelle = comparaisonsListesManuelle;
    }

    public int getVictoireDefenseur() {
        return victoireDefenseur;
    }

    public void setVictoireDefenseur(int victoireDefenseur) {
        this.victoireDefenseur = victoireDefenseur;
    }

    /**
     * This is the lone method of this class which enables the Human Player to compare the result of both combinaisons, key by key.
     */

    public void comparerLesListesManuelle() {
        logger.info("\nComparons step by step les 2 combinaisons ...");
        for (int k = 0; k < combinaisons.getNbCombinaisons(); k++) {
            int K = k + 1;
            this.ca = (Integer) combinaisonsAuto.getCombinaisonsAuto().get(k);
            logger.info("\nLe chiffre proposé par la machine en position " + K + " est : " + ca);
            this.cm = (Integer) combinaisonManuelle.getCombinaisonManuelle().get(k);
            logger.info("Le chiffre de ta combinaison secrète en position " + K + " est : " + cm);
            logger.info("Si la proposition de la machine correspond au chiffre de ta combinaison secrète tape =,\nsi elle est moins élevée tape -,\nsi elle est plus élevée tape +");
            String cr1 = "=";
            String cr2 = "+";
            String cr3 = "-";
            Scanner sc = new Scanner(System.in);
            try {
                this.cr = sc.nextLine();
                (comparaisonsListesManuelle).add(cr);
                if ((ca == cm && !cr.equals(cr1)) || (ca > cm && !cr.equals(cr2)) || (ca < cm && !cr.equals(cr3))) {
                    logger.info("Tu es un tricheur Pimpon. La Machine ne joue pas avec les tricheurs. FIN DE PARTIE. GAME OVER.");
                    System.exit(0);
                }
            } catch (InputMismatchException e) {
                logger.info("La saisie n'est pas correcte. Il faut recommencer ...");
                sc.next();
                comparerLesListesManuelle();
            }
        }
            logger.info("La proposition de la machine " + combinaisonsAuto.getCombinaisonsAuto() + " donne les résultats suivants : " + comparaisonsListesManuelle);
            if (combinaisonManuelle.getCombinaisonManuelle().equals(combinaisonsAuto.getCombinaisonsAuto())) {
                logger.info("\nLa combinaison proposée par la machine est la combinaison secrète.");
                victoireDefenseur = 2;
            } else {
                logger.info("\nLa machine n'a pas découvert la combinaison secrète.");
                victoireDefenseur = 1;
            }
        combinaisonsAuto.getCombinaisonsAuto().clear();
        comparaisonsListesManuelle.clear();
        }
    }
