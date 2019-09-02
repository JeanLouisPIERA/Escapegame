package com.ocr.jeanlouis34.escapegame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class JeuBeginTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    JeuBegin jeubegin = new JeuBegin();

    @Test
    public void Given_Nothing_When_DisplayAvailableModeJeu_Then_DisplayText() {
        jeubegin.displayAvailableModeJeu();
        assertFalse(outContent.toString().isEmpty());
    }

    @Test
    public void Given_Challenger_When_DisplaySelectedModeJeu_Then_DisplayCorrectResponse() {
        jeubegin.displaySelectedModeJeu(1);
        assertEquals("BRAVO ! Tu as choisi le mode CHALLENGER. A toi de trouver la combinaison de l'ordinateur. Que la Force soit avec toi !\n",outContent.toString().replace("\r\n", "\n"));
    }

    @Test
    public void Given_TooBigValue_When_DisplaySelectedModeJeu_Then_DisplayErrorSentence() {
        jeubegin.displaySelectedModeJeu(5);
        assertEquals("Ne réponds pas n'importe quoi pour éviter le combat. Pour que tu comprennes bien, bis repetita ...\n", outContent.toString().replace("\r\n", "\n"));
    }
    @Test
    public void Given_NegativeValue_When_DisplayModeJeu_Then_DisplayErrorSentence() {
        jeubegin.displaySelectedModeJeu(-1);
        assertEquals("Ne réponds pas n'importe quoi pour éviter le combat. Pour que tu comprennes bien, bis repetita ...\n", outContent.toString().replace("\r\n", "\n"));
    }

    @Test
    public void Given_DefenseurInStandardInput_When_ModeJeuisRun_Then_DisplayCorrectProcess() {
        System.setIn(new ByteArrayInputStream("l\n".getBytes()));
        jeubegin = new JeuBegin();
        jeubegin.runModeJeu();
        String output = outContent.toString().replace("\r\n", "\n");
        assertEquals(output.endsWith("BRAVO ! Tu as choisi le mode DEFENSEUR. Tous mes voeux pour que ce satané ordinateur ne trouve pas ta combinaison !\n"), true);
        assertEquals(output.length() > "BRAVO ! Tu as choisi le mode DEFENSEUR. Tous mes voeux pour que ce satané ordinateur ne trouve pas ta combinaison !\n".length(), true);
    }

}