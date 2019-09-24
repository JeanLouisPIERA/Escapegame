package com.ocr.jeanlouis34.escapegame;

import org.apache.log4j.Logger;

import java.util.*;

/*import java.util.*;*/

public class PlayerMachine {

    /*static Logger logger = Logger.getLogger(PlayerMachine.class);
    public int TirageManuel;
    private JeuBegin jeubegin;
    private CombinaisonsAuto combiAuto;
    private CombinaisonManuelle combiManuelle;
    private Integer nbCombinaisons;
    private List<Integer> combinaisonsAuto = new ArrayList<>();
    private List <Integer> combinaisonsManuelle = new ArrayList <>();*/

    /*private Integer getNbCombinaisons() {
        nbCombinaisons = jeubegin.getNbCombinaisons();
        return nbCombinaisons;
    }

    private List getCombinaisonsAuto() {
        combinaisonsAuto = combiAuto.getCombinaisonsAuto();
        return combinaisonsAuto;
    }*/

    /*public List combinerAuto() {
        combiAuto.combinerAuto();
        return combinaisonsAuto;
    }*/

    /*public void printCombinaisonsAuto() {
        combiAuto.printCombinaisonsAuto();
    }*/

    /*public List combinerManuelle() {
        combiManuelle.combinerManuelle();
        return combinaisonsManuelle;
    }

    public void printCombinaisonsManuelle() {
        combiManuelle.printCombinaisonsManuelle();
    }*/

    static Logger logger = Logger.getLogger(PlayerMachine.class);
    private List<Integer> combinaisonsAuto;
    private List<Integer> combinaisonsManuelle;
    private List<String> comparaisonsListes;
    private int tirageManuel;
    private JeuBegin jeubegin = new JeuBegin();


    public PlayerMachine() {
        this(new ArrayList<Integer>(), new ArrayList<Integer>(), new ArrayList<String>(),tirageManuel: 0);
    }

    public PlayerMachine(List<Integer> combinaisonsAuto, List<Integer> combinaisonsManuelle, List<String> comparaisonsListes, int tirageManuel) {
        this.combinaisonsAuto = combinaisonsAuto;
        this.combinaisonsManuelle = combinaisonsManuelle;
        this.comparaisonsListes = comparaisonsListes;
        this.tirageManuel = tirageManuel;
        this.jeubegin = new JeuBegin();
    }

    public List<Integer> getCombinaisonsAuto() {
        return combinaisonsAuto;
    }

    public void setCombinaisonsAuto(ArrayList<Integer> combinaisonsAuto) {
        this.combinaisonsAuto = combinaisonsAuto;
    }

    public List<Integer> getCombinaisonsManuelle() {
        return combinaisonsManuelle;
    }

    public void setCombinaisonsManuelle(ArrayList<Integer> combinaisonsManuelle) {
        this.combinaisonsManuelle = combinaisonsManuelle;
    }

    public List getComparaisonsListes() {
        return comparaisonsListes;
    }

    public void setComparaisonsListes(ArrayList<String> comparaisonsListes) {
        this.comparaisonsListes = comparaisonsListes;
    }

    public int getTirageManuel() {
        return tirageManuel;
    }

    public void setTirageManuel(int tirageManuel) {
        this.tirageManuel = tirageManuel;
    }

    public void combinerAuto() {

        if (this.combinaisonsAuto == null) {
            return
            // /!\ attention this.combinaisonsAuto est null (car pas instancie).
            // Solution 1 : l'instancier ici et continuer
            // Solution 2 : ne pas l'instancier ici, et afficher un message d'erreur avec return qui arrete l'execution de la méthode
            while (this.combinaisonsAuto.size() < jeubegin.getNbCombinaisons()) {
                try {
                    tirerAuto();
                } catch (Exception e) {
                    logger.error(e);
                }
            }
            // /!\ setCombinaisonsAuto est inutile, il est fait dans tirerAuto
            // setCombinaisonsAuto(this.combinaisonsAuto);
        }
    }

    public void printCombinaisonsAuto() {
        for (int i = 0; i < jeubegin.getNbCombinaisons(); i++) {
            logger.info("Élément à l'index " + i + " = " + combinaisonsAuto.get(i));
        }
    }

    public void tirerAuto() {
        Random r = new Random();
        int randint = Math.abs(r.nextInt()) % 10;
        logger.info(randint);
        try {
            this.combinaisonsAuto.add(randint);
        } catch (Exception e) {
            logger.error (e);
        }
    }

    public void combinerManuelle() {

        logger.info("Bonjour, Ici le Player Machine. La combinaison que tu dois trouver a une longueur de " + jeubegin.getNbCombinaisons() +" chiffres.");
        logger.info("Attention le chiffre que tu choisis doit être un nombre entier positif.");
        while (this.combinaisonsManuelle.size() < jeubegin.getNbCombinaisons())
            try {
                tirerManuel();
            } catch (Exception e) {
                logger.error(e);
            }
        /*setCombinaisonsManuelle(this.combinaisonsManuelle);*/
    }

    public void tirerManuel() {

        logger.info("Merci d'indiquer ici le chiffre que tu choisis :");
        Scanner sc = new Scanner(System.in).useDelimiter(" *");
        try {
            this.tirageManuel = sc.nextInt();
            (/*(ArrayList)*/ this.combinaisonsManuelle).add(tirageManuel);
        } catch (InputMismatchException e) {
            logger.info("La saisie n'est pas correcte. Il faut recommencer ...");
            sc.next();
            tirerManuel();
            }
        /*setTirageManuel(this.tirageManuel);*/
    }

    public void printCombinaisonsManuelle() {
        for (int j = 0; j < jeubegin.getNbCombinaisons(); j++) {
            logger.info("Élément à l'index " + j + " = " + combinaisonsManuelle.get(j));
        }
    }

    public void comparerleslistes() {
        String A = "=";
        String B = "-";
        String C = "+";
        for ( int k = 0; k < jeubegin.getNbCombinaisons(); k++) {
            int cm = (Integer) combinaisonsManuelle.get(k);
            int ca = (Integer) combinaisonsAuto.get(k);
            if (cm == ca) {
                (/*(ArrayList)*/ comparaisonsListes).add(A);
            } else if (cm < ca) {
                (/*(ArrayList)*/ comparaisonsListes).add(B);
            } else {
                (/*(ArrayList)*/ comparaisonsListes).add(C);
            }
        }
        logger.info("Pour chacun des chiffres que tu as proposés, je vais te donner une indication : \n = si c'est bon, - si ton chiffre est inférieur, + si ton chiffre est supérieur");
        logger.info("Ta proposition " + combinaisonsManuelle + "donne les résultats suivants :" + comparaisonsListes);
        if (combinaisonsManuelle.equals(combinaisonsAuto)) {
            logger.info("Bravo tu as gagné. Ta combinaison est la combinaison secrète.");
        } else {
            logger.info("Tu n'as pas encore découvert la combinaison secrète. Tu as le droit de jouer une partie supplémentaire.");
            this.combinerManuelle();
        }
        /*setComparaisonsListes(this.comparaisonsListes);*/
    }
}

