package com.ocr.jeanlouis34.escapegame;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;




public class CombinaisonsAuto {

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

}