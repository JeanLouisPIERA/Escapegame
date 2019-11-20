package com.ocr.jeanlouis34.escapegame.player;


import com.ocr.jeanlouis34.escapegame.combi.CombinaisonManuelle;
import com.ocr.jeanlouis34.escapegame.combi.CombinaisonsAuto;
import com.ocr.jeanlouis34.escapegame.combi.CombinaisonsParams;
import com.ocr.jeanlouis34.escapegame.jeu.JeuChallenger;
import com.ocr.jeanlouis34.escapegame.jeu.JeuDefenseur;
import com.ocr.jeanlouis34.escapegame.jeu.JeuDuel;
import com.ocr.jeanlouis34.escapegame.jeu.JeuParams;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * This class is the twin class of Player Machine.
 *
 * This class enables to manually compare both automatically and handly made combinaisons.
 *
 * That is why it uses the variables of all the classes about COMBINAISON.
 *
 * WARNING : I have introduced a overrated level which enables to detect if the Human Player have cheated.
 */
public class PlayerJoueur implements Player {

    static Logger logger = Logger.getLogger(PlayerJoueur.class);
    // this variable enables to store the result of each round of the game
    private int victoire;
    // this variable enables to recover and store the value of the automatic combinaison at the key k
    private int ca;
    // this variable enables to recover and store the value of the manual combinaison at the key k
    private int cm;
    private String cr;
    // this variable enables to store the comparison characters +,-,= of the manual comparison
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

    public int getVictoire() {
        return victoire;
    }

    /**
     * This is the lone method of this class which enables the Human Player to compare the result of both combinaisons, key by key.
     */
    public void comparerLesListes() {
        comparaisonsListes.clear();
        int tricheur = 0;
        while (this.comparaisonsListes.size() < combinaisonsParams.getNbCombinaisons()) {
            String tab[] = null;
            String x = null;
            System.out.print("    REPONSE :   ");
            Scanner sc = new Scanner(System.in);
            x = sc.nextLine();
            tab = new String[x.length()];
            if (x.length() != combinaisonsParams.getNbCombinaisons()) {
                logger.error("TAILlE DE LA COMBINAISON PROPOSEE = INCORRECTE.");
                combinaisonsAuto.printCombinaison();
                x = null;
                this.comparerLesListes();
            } else {
                for (int i = 0; i < tab.length; i++) {
                    tab[i] = x.substring(i, i + 1);
                    int cm = (Integer) combinaisonManuelle.getCombinaisonSecrete().get(i);
                    int ca = (Integer) combinaisonsAuto.getCombinaison().get(i);
                    if (cm == ca && !tab[i].equals("=")) {
                        tricheur = 1;
                        (comparaisonsListes).add("=");
                    } else if (cm < ca && !tab[i].equals("+")) {
                        tricheur = 1;
                        (comparaisonsListes).add("+");
                    } else if (cm > ca && !tab[i].equals("-")) {
                        (comparaisonsListes).add("-");
                        tricheur = 1;
                    } else {
                        this.comparaisonsListes.add(tab[i]);
                    }
                }
            }
        }
        if (tricheur == 1) {
            logger.info("ATTENTION AU TRICHEUR. LES SIGNES DE COMPARAISON SONT FAUX ET ONT ETE CORRIGES AUTOMATIQUEMENT.");
            // this variable enables to print the values of an array list without brackets
            String withoutBrackets = comparaisonsListes.toString()
                    .replace(",", "")  //remove the commas
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "")  //remove the left bracket
                    .trim();           //remove trailing spaces from partially initialized arrays
            System.out.println(" --> REPONSE CORRECTE :    " + withoutBrackets + "   ");
        }
        printComparaisonsListes();
    }

        public void printComparaisonsListes(){
            if (combinaisonManuelle.getCombinaisonSecrete().equals(combinaisonsAuto.getCombinaison())) {
                logger.info("\nVICTOIRE DE LA MACHINE.");
                victoire = 2;
            } else {
                victoire = 1;
        }
    }
}
