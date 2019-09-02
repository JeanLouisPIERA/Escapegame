package com.ocr.jeanlouis34.escapegame;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CombinaisonManuelle {

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
        for (int i = 0; i < 4; i++) {
            System.out.println("Élément à l'index " + i + " = " + combinaisonsManuelle.get(i));
        }
    }
}
