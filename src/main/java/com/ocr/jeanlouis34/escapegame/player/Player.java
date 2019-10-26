package com.ocr.jeanlouis34.escapegame.player;

/**
 * this abstract class defines the shared methods of the members the packge player
 */
public interface Player {

    /**
     * this abstract method enables to compare two combinaisons of the same length and in this game the automatic one with the manual one
     */
    public void comparerLesListes();


    /**
     * this abstract method enables to print the result of the comparisons both in the automatic and the manual pattern
     */
    public void printComparaisonsListes();

}