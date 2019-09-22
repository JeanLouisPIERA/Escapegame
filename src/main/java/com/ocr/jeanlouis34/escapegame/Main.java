package com.ocr.jeanlouis34.escapegame;


import org.apache.log4j.Logger;

public class Main {

	public static void main(String[] args){

	Logger logger = Logger.getLogger(Main.class);

	/*PlayerJoueur NomJoueur = new PlayerJoueur();*/
	/*JeuBegin jeubegin = new JeuBegin();
	PlayerMachine playermachine = new PlayerMachine();*/
	JeuChallenger jeuChallenger = new JeuChallenger();
	/*Combinaisons combinaisons;
	CombinaisonsAuto combinaisonsAuto;*/
	/*CombinaisonsAuto combinaisonsauto = new CombinaisonsAuto();
	CombinaisonManuelle combinaisonmanuelle = new CombinaisonManuelle();*/

	jeuChallenger.runJeuChallenger();
	/*jeubegin.runModeJeu();
	jeubegin.setModeJeu();
	jeubegin.setNbCombinaisons();*/
	/*combinaisonsAuto.setCombinaisonsAuto();*/
	/*playermachine.combinerAuto();*/
	/*playermachine.getCombinaisonsAuto();*/
	/*combinaisonsAuto.printCombinaisonsAuto();
	playermachine.combinerManuelle();
	playermachine.printCombinaisonsManuelle();
	playermachine.comparerleslistes();*/


		/*try {
			combinaisonsauto.getCombinaisonsAuto();
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			combinaisonsauto.printComnbinaisonsAuto();
		} catch (Exception ew) {
			logger.error(ew);
		}
		/*try {
			combinaisonmanuelle.setCombinaisonsManuelle();
		} catch (Exception ex) {
			logger.error(ex);
		}
		try {
			combinaisonmanuelle.printCombinaisonsManuelle();
		} catch (Exception ey) {
			logger.error(ey);
		}
		try {
			playermachine.comparerleslistes();
		} catch (Exception ez) {
			logger.error(ez);
		}*/
	}
}


