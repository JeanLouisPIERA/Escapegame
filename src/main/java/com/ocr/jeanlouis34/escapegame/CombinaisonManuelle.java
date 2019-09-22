package com.ocr.jeanlouis34.escapegame;

public class CombinaisonManuelle {

    /*public List<Integer> combinaisonsManuelle = new ArrayList<>();
    private int TirageManuel;
    static Logger logger = Logger.getLogger(CombinaisonManuelle.class);*/
    /*JeuBegin jeubegin = new JeuBegin();*/
    /*JeuBegin jeubegin*/;
    /*Combinaisons combinaisons;*/

    /*public List combinerManuelle() {
        logger.info("Bonjour, Ici le Player Machine. La combinaison que tu dois trouver a une longueur de " + combinaisons.getNbCombinaisons() +" chiffres.");
        logger.info("Attention le chiffre que tu choisis doit être un nombre entier positif.");*/
        /*jeubegin.setNbCombinaisons();*/
        /*while (this.combinaisonsManuelle.size() < combinaisons.getNbCombinaisons())
            try {
                tirerManuel();
            } catch (Exception e) {
                logger.error(e);
            }
        return combinaisonsManuelle;
    }

    public void tirerManuel() {
        logger.info("Merci d'indiquer ici le chiffre que tu choisis :");
        Scanner sc = new Scanner(System.in).useDelimiter(" *");
        try {
            this.TirageManuel = sc.nextInt();
            ((ArrayList) this.combinaisonsManuelle).add(TirageManuel);
        } catch (InputMismatchException e) {
            logger.info("La saisie n'est pas correcte. Il faut recommencer ...");
            sc.next();
            tirerManuel();
        }
    }

    public void printCombinaisonsManuelle() {
        for (int j = 0; j < combinaisons.getNbCombinaisons(); j++) {
            logger.info("Élément à l'index " + j + " = " + combinaisonsManuelle.get(j));
        }
    }*/

}
