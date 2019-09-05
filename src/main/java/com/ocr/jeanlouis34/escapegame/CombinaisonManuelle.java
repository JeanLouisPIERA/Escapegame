package com.ocr.jeanlouis34.escapegame;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CombinaisonManuelle {

    public List combinaisonsManuelle = new ArrayList<>();
    private int TirageManuel;
    static Logger logger = Logger.getLogger(CombinaisonManuelle.class);

    /*JeuBegin jeubegin = new JeuBegin();
    public int CombiSize = jeubegin.getNbCombinaisons();*/

    public List combinerManuelle() {
        logger.info("Bonjour, Ici le Player Machine. La combinaison que tu dois trouver a une longueur de " + "4" +" chiffres.");
        logger.info("Attention le chiffre que tu choisis doit être un nombre entier positif.");
        while (this.combinaisonsManuelle.size() < 4)
            try {
                tirerManuel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return combinaisonsManuelle;
    }

    public void tirerManuel() {
        logger.info("Merci d'indiquer ici le chiffre que tu choisis :");
        Scanner sc = new Scanner(System.in);
        try {
            this.TirageManuel = sc.nextInt();
        } catch (Exception e) {
            logger.info("La saisie du nombre du mode de jeu n'est pas correcte. Il faut recommencer ...");
            sc.next();
            try {
                this.TirageManuel = sc.nextInt();
            } catch (Exception ex) {
                recommencerTirageManuel();
            }
        }
        ((ArrayList) this.combinaisonsManuelle).add(TirageManuel);
    }

    public void recommencerTirageManuel() {
        logger.info("La saisie n'est toujours pas correcte. On recommence à zéro ...");
        tirerManuel();
    }

    public void printCombinaisonsManuelle() {
        for (int i = 0; i < 4; i++) {
            logger.info("Élément à l'index " + i + " = " + combinaisonsManuelle.get(i));
        }
    }
}
