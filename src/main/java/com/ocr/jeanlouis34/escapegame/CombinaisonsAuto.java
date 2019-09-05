package com.ocr.jeanlouis34.escapegame;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;




public class CombinaisonsAuto {

    public List combinaisonsAuto = new ArrayList<>();
    static Logger logger = Logger.getLogger(CombinaisonsAuto.class);

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
            e.printStackTrace();
        }
    }

}