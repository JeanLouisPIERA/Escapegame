package com.ocr.jeanlouis34.escapegame.combi;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This Class is one of the 3 basic classes which deal with the combinaisons issues.
 *
 * It is used to define the content of the automatic combinaisons based on an automatic sort.
 *
 * The results are used at two levels :
 * to compare the combinaisons (PlayerJoueur + PlayerMachine)
 * to run 2 of the 3 operatives patterns (JeuChallenger and JeuDefenseur)
 */
public class CombinaisonsAuto extends Combinaisons {

    static Logger logger = Logger.getLogger(CombinaisonsAuto.class);
    // this variable enables to define if the method combiner complete the array combinaison or the array combinaisonSecrete
    private int modeTirageAuto;
    // this variable represents the current value of the current automatic combinaison registered at the key k
    private int ca;
    // this variable represents the new value of the automatic combinaison registered at the key k
    private int nca;
    // this variable represents the lower bound of the iterative approximation
    private int min = 0;
    // this variable represents the upper bound of the iterative approximation
    private int max = 9;
    // this variable enables to extract the values of the combinaison
    private String withoutBrackets = "";
    // this variable enbales to register the lower bounds of the iterative approximation at its key k in a combinaison in an arraylist
    private List<Integer> combinaisonMin;
    // this variable enbales to register the upper bounds of the iterative approximation at its key k in a combinaison in an arraylist
    private List<Integer> combinaisonMax;
    /* during the iterative approximation, this variable enbales to register in an arrayList at the same k key
    the int value which corresponds to the converted value of the equivalent comparison character
    in the comparison combinaison created by the human player during the previous round*/
    private List<Integer> combinaisonModeTirageAuto;


    public CombinaisonsAuto(CombinaisonsParams combinaisonsParams) {
        super(combinaisonsParams);
        this.modeTirageAuto = 0;
        this.ca = 1;
        this.nca = 1;
        this.min = 0;
        this.max = 9;
        this.combinaisonMin = new ArrayList<>();
        this.combinaisonMax = new ArrayList<>();
        this.combinaisonModeTirageAuto = new ArrayList<>();

    }

    public CombinaisonsAuto(List<Integer> combinaison, List<Integer> combinaisonSecrete, CombinaisonsParams combinaisonsParams, int modeTirageAuto, int ca, int nca, int min, int max, List<Integer> combinaisonMin, List<Integer> combinaisonMax, List<Integer> combinaisonModeTirageAuto) {
        super(combinaison, combinaisonSecrete, combinaisonsParams);
        this.modeTirageAuto = modeTirageAuto;
        this.ca = ca;
        this.nca = nca;
        this.min = min;
        this.max = max;
        this.combinaisonMin = combinaisonMin;
        this.combinaisonMax = combinaisonMax;
        this.combinaisonModeTirageAuto = combinaisonModeTirageAuto;
    }

    public List<Integer> getCombinaison() {
        return combinaison;
    }

    public List<Integer> getCombinaisonSecrete() { return combinaisonSecrete;}

    public List<Integer> getCombinaisonMin() {
        return combinaisonMin;
    }

    public List<Integer> getCombinaisonMax() {
        return combinaisonMax;
    }

    public List<Integer> getCombinaisonModeTirageAuto() {
        return combinaisonModeTirageAuto;
    }

    public void setModeTirageAuto(int modeTirageAuto) {

        combinaisonModeTirageAuto.clear();
        this.modeTirageAuto = modeTirageAuto;
        for (int k = 0; k < combinaisonsParams.getNbCombinaisons(); k++) {
            (combinaisonModeTirageAuto).add(modeTirageAuto);
        }
    }

    /**
     * This method enables to combine the results of sorts in an indexed list.
     * It is identically shared by all the subclasses Combinaisons.
     */
    @Override
    public void combiner() {
        this.combinaison.clear();
        for (int k = 0; k < combinaisonsParams.getNbCombinaisons(); k++) {
            modeTirageAuto = combinaisonModeTirageAuto.get(k);
            switch(modeTirageAuto) {
                case 1:
                    Random r = new Random();
                    nca = Math.abs(r.nextInt()) % 10;
                    this.combinaison.add(nca);
                    this.combinaisonMin.add(0);
                    this.combinaisonMax.add(10);
                    break;
                case 2:
                    min = combinaisonMin.get(k);
                    max = combinaisonMax.get(k);
                    nca = min;
                    this.combinaison.add(nca);
                    break;
                case 3:
                    min = combinaisonMin.get(k);
                    max = combinaisonMax.get(k);
                    nca = (max+min)/2;
                    this.combinaison.add(nca);
                    break;
                case 4:
                    min = combinaisonMin.get(k);
                    max = combinaisonMax.get(k);
                    nca = (min+max)/2;
                    this.combinaison.add(nca);
                    break;
                case 5:
                    Random randInt = new Random();
                    nca = Math.abs(randInt.nextInt()) % 10;
                    this.combinaisonSecrete.add(nca);
                    break;
            }
        }
    }




    /**
     * This method enables to print the combinaison obtained by the method combiner.
     * It is modified comparing with the one in the super class.
     */

    @Override
    public void printCombinaison() {
        String withoutBrackets = combinaison.toString()
                .replace(",", "")  //remove the commas
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();           //remove trailing spaces from partially initialized arrays
        System.out.print(" --> PROPOSITION DE LA MACHINE :    " + withoutBrackets);
    }
}



