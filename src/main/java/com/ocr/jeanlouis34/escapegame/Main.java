package com.ocr.jeanlouis34.escapegame;



public class Main {


    public static void main(String[] args) {
	JeuBegin jeubegin = new JeuBegin();
	PlayerMachine playermachine = new PlayerMachine();
	/*CombinaisonsAuto combinaisonsauto = new CombinaisonsAuto();
	CombinaisonManuelle combinaisonmanuelle = new CombinaisonManuelle();*/
	jeubegin.AskSomething();
	jeubegin.runModeJeu();
	playermachine.combinerAuto();
	playermachine.printComnbinaisonsAuto();
	playermachine.combinerManuelle();
	playermachine.printCombinaisonsManuelle();
	playermachine.comparerleslistes();

	/*
		try {
			combinaisonsauto.combinerAuto();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			combinaisonsauto.printComnbinaisonsAuto();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			combinaisonmanuelle.combinerManuelle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			combinaisonmanuelle.printCombinaisonsManuelle();
		} catch (Exception e) {
			e.printStackTrace();
		}*/

	}
}


