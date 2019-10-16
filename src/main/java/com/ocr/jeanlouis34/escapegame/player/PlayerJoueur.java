package com.ocr.jeanlouis34.escapegame.player;


import com.ocr.jeanlouis34.escapegame.combi.CombinaisonManuelle;
import com.ocr.jeanlouis34.escapegame.combi.CombinaisonsAuto;
import com.ocr.jeanlouis34.escapegame.combi.CombinaisonsParams;
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

public class PlayerJoueur implements Player {

    static Logger logger = Logger.getLogger(PlayerJoueur.class);
    private int victoire;
    private int ca;
    private int cm;
    private String cr;
    private List<String> comparaisonsListes;
    private CombinaisonsParams combinaisonsParams;
    private CombinaisonsAuto combinaisonsAuto;
    private CombinaisonManuelle combinaisonManuelle ;

    public PlayerJoueur(CombinaisonsParams combinaisonsParams, CombinaisonsAuto combinaisonsAuto, CombinaisonManuelle combinaisonManuelle) {
        this(0,0,0,"",new ArrayList<>(),combinaisonsParams, combinaisonsAuto, combinaisonManuelle);
    }

    public PlayerJoueur(int victoire, int ca, int cm, String cr, List<String> comparaisonsListes, CombinaisonsParams combinaisonsParams, CombinaisonsAuto combinaisonsAuto, CombinaisonManuelle combinaisonManuelle) {
        this.victoire = victoire;
        this.ca = ca;
        this.cm = cm;
        this.cr = cr;
        this.comparaisonsListes = comparaisonsListes;
        this.combinaisonsParams = combinaisonsParams;
        this.combinaisonsAuto = combinaisonsAuto;
        this.combinaisonManuelle = combinaisonManuelle;
    }

    public List<String> getComparaisonsListes() {
        return comparaisonsListes;
    }

    public void setComparaisonsListesManuelle(List<String> comparaisonsListes) {
        this.comparaisonsListes = comparaisonsListes;
    }

    public int getVictoire() {
        return victoire;
    }

    public void setVictoireDefenseur(int victoireDefenseur) {
        this.victoire = victoire;
    }

    /**
     * This is the lone method of this class which enables the Human Player to compare the result of both combinaisons, key by key.
     */

    public void comparerLesListes() {
        for (int k = 0; k < combinaisonsParams.getNbCombinaisons(); k++) {
            int K = k + 1;
            this.ca = (Integer) combinaisonsAuto.getCombinaison().get(k);
            logger.info("\nLe chiffre proposé par la machine en position " + K + " est : " + ca);
            this.cm = (Integer) combinaisonManuelle.getCombinaison().get(k);
            logger.info("Le chiffre de ta combinaison secrète en position " + K + " est : " + cm);
            logger.info("Si la proposition de la machine correspond au chiffre de ta combinaison secrète tape =,\nsi elle est moins élevée tape -,\nsi elle est plus élevée tape +");
            String cr1 = "=";
            String cr2 = "+";
            String cr3 = "-";
            Scanner sc = new Scanner(System.in);
            try {
                this.cr = sc.nextLine();
                (comparaisonsListes).add(cr);
                if ((ca == cm && !cr.equals(cr1)) || (ca > cm && !cr.equals(cr2)) || (ca < cm && !cr.equals(cr3))) {
                    logger.info("Tu es un tricheur Pimpon. La Machine ne joue pas avec les tricheurs. FIN DE PARTIE. GAME OVER.");
                    System.exit(0);
                }
            } catch (InputMismatchException e) {
                logger.info("La saisie n'est pas correcte. Il faut recommencer ...");
                sc.next();
                comparerLesListes();
            }
        }
        printComparaisonsListes();
        combinaisonsAuto.getCombinaison().clear();
        comparaisonsListes.clear();
        }

        public void printComparaisonsListes(){
            logger.info("La proposition de la machine " + combinaisonsAuto.getCombinaison() + " donne les résultats suivants : " + comparaisonsListes);
            if (combinaisonManuelle.getCombinaison().equals(combinaisonsAuto.getCombinaison())) {
                logger.info("\nLa combinaison proposée par la machine est la combinaison secrète.");
                victoire = 2;
            } else {
                logger.info("\nLa machine n'a pas découvert la combinaison secrète.");
                victoire = 1;
            }
        }
    }
