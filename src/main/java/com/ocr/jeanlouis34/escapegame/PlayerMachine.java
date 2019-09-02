package com.ocr.jeanlouis34.escapegame;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PlayerMachine {

    public LinkedList combinaisonsAuto = new LinkedList();

    /*JeuBegin jeubegin = new JeuBegin();
    public int CombiSize = jeubegin.getNbCombinaisons();*/

    public List combinerAuto() {
        while (this.combinaisonsAuto.size() < 4)
            try {
                tirerAuto();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return combinaisonsAuto;
    }

    public void printComnbinaisonsAuto() {
        for (int i = 0; i < 4; i++) {
            System.out.println("Élément à l'index " + i + " = " + combinaisonsAuto.get(i));
        }
    }

    public void tirerAuto() {
        Random r = new Random();
        int randint = Math.abs(r.nextInt()) % 10;
        System.out.println(randint);
        try {
            ((LinkedList) this.combinaisonsAuto).addLast(randint);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LinkedList combinaisonsManuelle = new LinkedList();
    private int TirageManuel;

    /*JeuBegin jeubegin = new JeuBegin();
    public int CombiSize = jeubegin.getNbCombinaisons();*/

    public List combinerManuelle() {
        System.out.println("Bonjour, Ici le Player Machine. La combinaison que tu dois trouver a une longueur de " + "4" +" chiffres.");
        System.out.println("Attention le chiffre que tu choisis doit être un nombre entier positif.");
        while (this.combinaisonsManuelle.size() < 4)
            try {
                tirerManuel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return combinaisonsManuelle;
    }

    public void tirerManuel() {
        System.out.println("Merci d'indiquer ici le chiffre que tu choisis :");
        Scanner sc = new Scanner(System.in);
        try {
            this.TirageManuel = sc.nextInt();
        } catch (Exception e) {
            System.out.println("La saisie du nombre du mode de jeu n'est pas correcte. Il faut recommencer ...");
            sc.next();
            try {
                this.TirageManuel = sc.nextInt();
            } catch (Exception ex) {
                recommencerTirageManuel();
            }
        }
        ((LinkedList) this.combinaisonsManuelle).addLast(TirageManuel);
    }

    public void recommencerTirageManuel() {
        System.out.println("La saisie n'est toujours pas correcte. On recommence à zéro ...");
        tirerManuel();
    }

    public void printCombinaisonsManuelle() {
        for (int j = 0; j < 4; j++) {
            System.out.println("Élément à l'index " + j + " = " + combinaisonsManuelle.get(j));
        }
    }

    public LinkedList comparerleslistes = new LinkedList();

    public void comparerleslistes() {
        String A = "=";
        String B = "-";
        String C = "+";
        for (Integer k = 0; k < 4; k++) {
            int cm = (Integer) combinaisonsManuelle.get(k);
            int ca = (Integer) combinaisonsAuto.get(k);
            if (cm - ca == 0) {
                ((LinkedList) comparerleslistes).addLast(A);
            } else if (cm < ca) {
                ((LinkedList) comparerleslistes).addLast(B);
            } else if (cm > ca) {
                ((LinkedList) comparerleslistes).addLast(C);
            }
        }
        System.out.println("Pour chacun des chiffres que tu as proposés, je vais te donner une indication : \n = si c'est bon, - si ton chiffre est inférieur, + si ton chiffre est supérieur");
        System.out.println("Ta proposition " + combinaisonsManuelle + "donne les résultats suivants :" + comparerleslistes);
    }
    }

