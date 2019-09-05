package com.ocr.jeanlouis34.escapegame;

import org.apache.log4j.Logger;

import java.util.*;


public class PlayerMachine {

    public List combinaisonsAuto = new ArrayList<>();
    static Logger logger = Logger.getLogger(PlayerMachine.class);


    /*JeuBegin jeubegin = new JeuBegin();
    public int CombiSize = jeubegin.getNbCombinaisons();*/

    public List combinerAuto() {
        while (this.combinaisonsAuto.size() < 4)
            try {
                tirerAuto();
            } catch (Exception e) {
                logger.error(e);
            }
        return combinaisonsAuto;
    }

    public void printComnbinaisonsAuto() {
        for (int i = 0; i < 4; i++) {
            logger.info("Élément à l'index " + i + " = " + combinaisonsAuto.get(i));
        }
    }

    public void tirerAuto() {
        Random r = new Random();
        int randint = Math.abs(r.nextInt()) % 10;
        logger.info(randint);
        try {
            ((ArrayList) this.combinaisonsAuto).add(randint);
        } catch (Exception e) {
            logger.error (e);
        }
    }

    public List combinaisonsManuelle = new ArrayList<>();
    private int TirageManuel;

    /*JeuBegin jeubegin = new JeuBegin();
    public int CombiSize = jeubegin.getNbCombinaisons();*/

    public List combinerManuelle() {
        logger.info("Bonjour, Ici le Player Machine. La combinaison que tu dois trouver a une longueur de " + "4" +" chiffres.");
        logger.info("Attention le chiffre que tu choisis doit être un nombre entier positif.");
        while (this.combinaisonsManuelle.size() < 4)
            try {
                tirerManuel();
            } catch (Exception e) {
                logger.error(e);
            }
        return combinaisonsManuelle;
    }

    public void tirerManuel() {
        logger.info("Merci d'indiquer ici le chiffre que tu choisis :");
        Scanner sc = new Scanner(System.in).useDelimiter(" *");
        try {
            this.TirageManuel = sc.nextInt();
            ((ArrayList) this.combinaisonsManuelle).add(TirageManuel);
        } catch (InputMismatchException e) {
            logger.info("La saisie n'est pas correcte. Il faut recommencer ...");
            sc.next();
            tirerManuel();
            }
        /*((ArrayList) this.combinaisonsManuelle).add(TirageManuel);*/
    }

    /*public void recommencerTirageManuel() {
        logger.info("La saisie n'est toujours pas correcte. On recommence à zéro ...");
        tirerManuel();
    }*/

    public void printCombinaisonsManuelle() {
        for (int j = 0; j < 4; j++) {
            logger.info("Élément à l'index " + j + " = " + combinaisonsManuelle.get(j));
        }
    }

    public List comparerleslistes = new ArrayList<>();

    public void comparerleslistes() {
        String A = "=";
        String B = "-";
        String C = "+";
        for (Integer k = 0; k < 4; k++) {
            int cm = (Integer) combinaisonsManuelle.get(k);
            int ca = (Integer) combinaisonsAuto.get(k);
            if (cm - ca == 0) {
                ((ArrayList) comparerleslistes).add(A);
            } else if (cm < ca) {
                ((ArrayList) comparerleslistes).add(B);
            } else if (cm > ca) {
                ((ArrayList) comparerleslistes).add(C);
            }
        }
        logger.info("Pour chacun des chiffres que tu as proposés, je vais te donner une indication : \n = si c'est bon, - si ton chiffre est inférieur, + si ton chiffre est supérieur");
        logger.info("Ta proposition " + combinaisonsManuelle + "donne les résultats suivants :" + comparerleslistes);
    }
    }

