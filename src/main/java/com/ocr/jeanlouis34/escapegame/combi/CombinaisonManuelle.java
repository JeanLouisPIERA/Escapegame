package com.ocr.jeanlouis34.escapegame.combi;

import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * This Class is one of the 3 basic classes which deal with the combinaisons issues.
 *
 * It is used to define the content of the handmade combinaisons based on an automatic sort.
 *
 * The results are used at two differents levels :
 * to compare the combinaisons (PlayerJoueur + PlayerMachine)
 * to run 2 of the 3 operatives patterns (JeuChallenger and JeuDefenseur)
 */
public class CombinaisonManuelle extends Combinaisons{

    static Logger logger = Logger.getLogger(CombinaisonManuelle.class);
    private int modeTirageManuel;

    public CombinaisonManuelle(CombinaisonsParams combinaisonsParams) { super(combinaisonsParams); this.modeTirageManuel=0;
    }

    public CombinaisonManuelle(List<Integer> combinaison, List<Integer> combinaisonSecrete, CombinaisonsParams combinaisonsParams,int modeTirageManuel) {
        super(combinaison, combinaisonSecrete, combinaisonsParams);
        this.modeTirageManuel = modeTirageManuel;
    }

    public List<Integer> getCombinaison() {
        return combinaison;
    }

    public List<Integer> getCombinaisonSecrete() { return combinaisonSecrete;}

    public void setModeTirageManuel(int modeTirageManuel) {
        this.modeTirageManuel = modeTirageManuel;
    }

    public int getModeTirageManuel() {
        return modeTirageManuel;
    }

    /**
     * This method enables to combine the results of sorts in an indexed list.
     * It is identically shared by all the subclasses Combinaisons.
     */
    @Override
    public void combiner() {
        switch (modeTirageManuel) {
            case 1:
                while (this.combinaisonSecrete.size() < combinaisonsParams.getNbCombinaisons()) {
                    int tabs[] = null;
                    Scanner scs = new Scanner(System.in);
                    /*System.out.print("\nProposition :");*/
                    String x = scs.nextLine();
                    tabs = new int[x.length()];

                    for (int i = 0; i < tabs.length; i++) {
                        tabs[i] = x.charAt(i) - '0';
                        this.combinaisonSecrete.add(tabs[i]);
                    }
                }
                break;
            case 2:
                while (this.combinaison.size() < combinaisonsParams.getNbCombinaisons()) {
                    int tab[] = null;
                    Scanner sc = new Scanner(System.in);
                    /*System.out.print("\nProposition :");*/
                    String x = sc.nextLine();
                    tab = new int[x.length()];

                    for (int i = 0; i < tab.length; i++) {
                        tab[i] = x.charAt(i) - '0';
                        this.combinaison.add(tab[i]);
                    }
                }
                break;
                }
            }


    /**
     * This method enables to print the combinaison obtained by the method combiner.
     * It is identically shared by all the subclasses Combinaisons.
     */
    @Override
    public void printCombinaison() {
        super.printCombinaison();
    }
}

