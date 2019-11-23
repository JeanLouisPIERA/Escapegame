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
 * WARNING : I have introduced a overrated level which enables to detect if the Human Player has cheated.
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
    private CombinaisonManuelle combinaisonManuelle;

    public PlayerJoueur(CombinaisonsParams combinaisonsParams, CombinaisonsAuto combinaisonsAuto, CombinaisonManuelle combinaisonManuelle) {
        this(0, 0, 0, "", new ArrayList<>(), combinaisonsParams, combinaisonsAuto, combinaisonManuelle);
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
            // this variable enables to store each value of the comparison written on the screen
            String tab [] = null;
            // this variable enables to recover each value of the comparison written on the screen
            String x = null;
            // this variable enable to validate the correct type of the value written on the console screen
            int contenu = 0;
            System.out.print("    REPONSE :   ");
            Scanner sc = new Scanner(System.in);
            try {
                x = sc.nextLine();
            } catch (InputMismatchException e) {
                logger.error("PROBLEME ENREGISTREMENT DE LA SAISIE.");
                combinaisonsAuto.printCombinaison();
                x = null;
                comparerLesListes();
            }
            try {
                tab = new String[x.length()];
            } catch (Exception e) {
                logger.error("NullPointerException");
                x = null;
                comparerLesListes();
            }
            if (x.length() != combinaisonsParams.getNbCombinaisons()) {
                logger.error("TAILLE DE LA COMBINAISON PROPOSEE = INCORRECTE.LA TAILLE EST DE " + combinaisonsParams.getNbCombinaisons() + " CARACTERES.");
                for (int i = 0; i < tab.length; i++) {
                    tab[i] = x.substring(i, i + 1);
                    if (!tab[i].equals("=") && !tab[i].equals("+") && !tab[i].equals("-")) {
                        contenu = 1;
                        break;
                    }
                }
                if (contenu == 1) {
                    logger.error("ET CONTENU DES VALEURS SAISIES = INCORRECT. (SEULS SIGNES AUTORISES + - ou =)");
                    combinaisonsAuto.printCombinaison();
                    x = null;
                    this.comparerLesListes();
                    break;
                }
                combinaisonsAuto.printCombinaison();
                x = null;
                this.comparerLesListes();
            } else {
                for (int i = 0; i < tab.length; i++) {
                    tab[i] = x.substring(i, i + 1);
                    if (!tab[i].equals("=") && !tab[i].equals("+") && !tab[i].equals("-")) {
                        contenu = 2;
                        break;
                    }
                }
                if (contenu == 2) {
                    logger.error("CONTENU DES VALEURS SAISIES = INCORRECT. (SEULS SIGNES AUTORISES + - ou =)");
                    combinaisonsAuto.printCombinaison();
                    x = null;
                    this.comparerLesListes();
                    break;
                } else {
                for (int i = 0; i < tab.length; i++) {
                    // this variable enables to recover the value "minimum" of the previous round to perform the alogoritm's running
                    int cmin = (Integer) combinaisonsAuto.getCombinaisonMin().get(i);
                    // this variable enables to recover the value "maximum" of the previous round to perform the alogoritm's running
                    int cmax = (Integer) combinaisonsAuto.getCombinaisonMax().get(i);
                    int ca = (Integer) combinaisonsAuto.getCombinaison().get(i);
                    if (cmax - cmin <= 2 && ca == (cmax + cmin) / 2 && !tab[i].equals("=")) tricheur = 1;
                    if (ca != 0 && ca != 1 && ca - cmin <= 1 & tab[i].equals("-")) tricheur = 1;
                    if (ca != 9 && cmax - ca <= 1 & tab[i].equals("+")) tricheur = 1;
                    if (ca == 9 && tab[i].equals("-")) tricheur = 2;
                    if (ca == 0 && tab[i].equals("+")) tricheur = 3;
                    this.comparaisonsListes.add(tab[i]);
                    }
                }
                if (tricheur == 1) {
                    logger.info("FIN DE PARTIE : LES COMPARAISONS PRECEDEMMENT FOURNIES A LA MACHINE ETAIENT FAUSSES.");
                    victoire = 2;
                } else if (tricheur == 2) {
                    victoire = 2;
                    logger.info("FIN DE PARTIE : LES COMPARAISONS PRECEDEMMENT FOURNIES A LA MACHINE ETAIENT FAUSSES. AUCUH CHIFFRE DE LA COMBINAISON NE PEUT ETRE SUPERIEUR A 9.");
                } else if (tricheur == 3) {
                    victoire = 2;
                    logger.info("FIN DE PARTIE : LES COMPARAISONS PRECEDEMMENT FOURNIES A LA MACHINE ETAIENT FAUSSES.AUCUN CHIFFRE DE LA COMBINAISON NE PEUT ETRE INFERIEUR A ZERO.");
                } else {
                    printComparaisonsListes();
                }
            }
        }
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
