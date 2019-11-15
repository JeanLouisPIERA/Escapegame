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

    public int getVictoire() {
        return victoire;
    }

    /**
     * This is the lone method of this class which enables the Human Player to compare the result of both combinaisons, key by key.
     */
    public void comparerLesListes() {
        comparaisonsListes.clear();
        while (this.comparaisonsListes.size() < combinaisonsParams.getNbCombinaisons()) {
            String x = " ";
            String tab[] = null;
            System.out.print("    Réponse :   ");
            Scanner sc = new Scanner(System.in);
            x = sc.nextLine();
            tab = new String[x.length()];
            for (int i = 0; i < tab.length; i++) {
                tab[i] = x.substring(i, i+1);
                this.comparaisonsListes.add(tab[i]);
            }
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
