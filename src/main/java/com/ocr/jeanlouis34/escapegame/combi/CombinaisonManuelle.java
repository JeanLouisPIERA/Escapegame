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
    // this variable enables to define if the method combiner complete the array combinaison or the array combinaisonSecrete
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
                    // this local variable enables to register the console input as a chained list
                    int tabs[] = null;
                    Scanner scs = new Scanner(System.in);
                    String x = scs.nextLine();
                    try {
                        // this local variable is introduced to catch the NumberFormatException
                        int xi = Integer.parseInt(x);
                    } catch (NumberFormatException e) {
                        logger.error("FORMAT DES VALEURS SAISIES = INCORRECT.");
                        if (x.length() != combinaisonsParams.getNbCombinaisons()) {
                            logger.error("ET TAILLE DE LA COMBINAISON PROPOSEE = INCORRECTE.LA TAILLE EST DE " + combinaisonsParams.getNbCombinaisons() + " CARACTERES.");
                        }
                        x = null;
                        System.out.print("VOTRE COMBINAISON SECRETE :    ");
                        this.combiner();
                        break;
                    }
                    tabs = new int[x.length()];

                    for (int i = 0; i < tabs.length; i++) {
                        tabs[i] = x.charAt(i) - '0';
                        this.combinaisonSecrete.add(tabs[i]);
                    }
                    if (x.length()!=combinaisonsParams.getNbCombinaisons()) {
                        logger.error("TAILLE DE LA COMBINAISON PROPOSEE = INCORRECTE. LA TAILLE EST DE " + combinaisonsParams.getNbCombinaisons() + " CARACTERES. ");
                        this.combinaisonSecrete.clear();
                        x = null;
                        System.out.print("VOTRE PROPOSITION :    ");
                        this.combiner();
                        break;
                    }
                }
            break;
            case 2:
                while (this.combinaison.size() < combinaisonsParams.getNbCombinaisons()) {
                    // this local variable enables to register the console input as a chained list
                    int tab[] = null;
                    Scanner sc = new Scanner(System.in);
                    String x = null;
                    x = sc.nextLine();
                    try {
                        // this local variable is introduced to catch the NumberFormatException
                        int xi = Integer.parseInt(x);
                    } catch (NumberFormatException e) {
                        logger.error("FORMAT DES VALEURS SAISIES = INCORRECT.");
                        if (x.length() != combinaisonsParams.getNbCombinaisons()) {
                            logger.error("ET TAILLE DE LA COMBINAISON PROPOSEE = INCORRECTE.LA TAILLE EST DE " + combinaisonsParams.getNbCombinaisons() + " CARACTERES.");
                        }
                        x = null;
                        System.out.print("VOTRE PROPOSITION :    ");
                        this.combiner();
                        break;
                    }
                    tab = new int[x.length()];

                    for (int i = 0; i < tab.length; i++) {
                        tab[i] = x.charAt(i) - '0';
                        this.combinaison.add(tab[i]);
                    }
                    if (x.length()!=combinaisonsParams.getNbCombinaisons()) {
                        logger.error("TAILLE DE LA COMBINAISON PROPOSEE = INCORRECTE. LA TAILLE EST DE " + combinaisonsParams.getNbCombinaisons() + " CARACTERES. ");
                        this.combinaison.clear();
                        x = null;
                        System.out.print("VOTRE PROPOSITION :    ");
                        this.combiner();
                        break;
                    }
                }
            break;
        }
    }


    /**
     * This method enables to print the combinaison obtained by the method combiner.
     */
    @Override
    public void printCombinaison() {
        super.printCombinaison();
    }
}

