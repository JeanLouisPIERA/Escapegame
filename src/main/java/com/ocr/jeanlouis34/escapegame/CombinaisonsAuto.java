package com.ocr.jeanlouis34.escapegame;

public class CombinaisonsAuto{

    /*public List<Integer> combinaisonsAuto = new ArrayList<>();
    static Logger logger = Logger.getLogger(PlayerMachine.class);*/
    /*JeuBegin jeubegin = new JeuBegin();*/
    /*private JeuBegin jeubegin;
    private int nbCombinaisons;

    public CombinaisonsAuto(List<Integer> combinaisonsAuto, int nbCombinaisons) {
        this.combinaisonsAuto = combinaisonsAuto;
        this.nbCombinaisons = nbCombinaisons;
    }

    private void setNbCombinaisons(int nbCombinaisons) {
        this.nbCombinaisons = jeubegin.getNbCombinaisons();
        return nbCombinaisons;
    }

    public List<Integer> getCombinaisonsAuto() {
        return combinaisonsAuto;
    }

    public void setCombinaisonsAuto() {
        while (this.combinaisonsAuto.size() < this.nbCombinaisons)
            try {
                tirerAuto();
            } catch (Exception e) {
                logger.error(e);
            }
    }

    /* public List combinerAuto() {
        /*jeubegin.setNbCombinaisons();*//*
        while (this.combinaisonsAuto.size() < combinaisons.getNbCombinaisons())
            try {
                tirerAuto();
            } catch (Exception e) {
                logger.error(e);
            }
        return combinaisonsAuto;
    }*/

    /*public void printCombinaisonsAuto() {
        for (Integer i = 0; i < this.nbCombinaisons; i++) {
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
    }*/

}