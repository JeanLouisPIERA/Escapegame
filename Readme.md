#TITLE OF THIS APPLICATION : ESCAPE GAME
    AUTHOR : Jean-Louis PIERA
    CONTACT : jeanlouispiera@yahoo.fr
    VERSION : November 2019

    ## Table of contents
    * [General info](#general-info)
    * [Technologies](#technologies)
    * [Setup](#setup)
    * [Detailed Description of Packages](#detailed_description_of_Packages)

    ## General info
    This training project is an academic work and has been developped to enable the project 3 in OpenClassrooms' Java Developer
    Cursus to be validated.
    Practically, this program aims to enable a human player known as the Player Joueur to play an escape game against the computer
    known as the Player Machine.
    A secret code of several numbers has to be found by the Player who attacks, the human or the mchine.

    ## Technologies
    Project is written in Java and created with
    * JDk version 12.0.2
    * Log4j version 1.2.17
    It is encoded in UTF-8
    All classes and methods are commented in Javadoc.
    Users can run it under minimum installed environnements from :
    * Apache Maven version 3.6.2
    * JRE 1.8.0_231


    ## Setup
    * To run this project, install it locally using the directory escape-game that you have to copy whereever you want on your disk.
    Please verify that it contains 3 other directories and their very necessary contents as well as differents other files :
        ** Directory Java which contains 2 Packages : Java.lang one and Javal.util one. Java.lang.reflect provides classses and
        interfaces for obtaining reflective information about classes and objects and java.util provides the collections framework
        printing and scanning, array manipulation utilities, event model date and time facilities, internationalzation and
        miscellaneous utility classes.
        ** Directory Javadoc for dévelopers in which you can consult in different sub-directories (such as com or index files)
        different html views of the project about the construction tree and the contained commented code of the classes
        ** Classes in which there are 2 subdirectories, one called main in which there is the java classes of the projet and one
        called resources in which there are 2 important files :
            *** configuration.properties : this files contains the program's parameters by default. THIS IS HERE that these game
            parameters can be defined to be called by the program in case of exceptions are run when the human player chooses the
            practical conditions of his game that is :
                **** nbCombinaisons parameter = the length of every combinaison (auto or manual). The default parameter
                nbCombinaisonsByDefault equals 4
                **** nbTours parameter = the number of rounds of each tournament of the game (that is for each mode of game
                jeuChallenger, jeuDefenseur or jeuDuel. For each one an explication is given in the next chapter). The default
                parameter nbToursByDefault equals 2
                **** modeDeveloper parameter = the human can decide to see the secret combinaison displayed on the screen as soon
                as it is automatically or manually defined. In this cas, the modeDeveloper parameter has to be put on OUI. The
                default parameter modeDeveloperBydefault is NON.
            ***log4j.xml : log4j is a tool to help the programmer output log statements to a variety of output targets. In case of
            problems with an application, it is helpful to enable logging so that the problem can be located. With log4j it is
            possible to enable logging at runtime without modifying the application binary.
        ** file pom.xml A Project Object Model or POM is the fundamental unit of work in Maven. It is an XML file that contains
        information about the project and configuration details used by Maven to build the project.
        WARNING : WITHOUT THIS CORRECT FILE IN YOUR PROGRAM DIRECTORY, IT WILL BE IMPOSSIBLE TO DEPLOY AND RUN THE PROGRAM WITH
        THE MAVEN TOOL
        ** and eventually, this file README.md your are now reading.

    * Launch the Command Prompt from your installed Directory escape-game on your disk and check if MAVEN tool is correctly
    installed and parametered as well as its environnement :
        ** type command mvn -version : the answer must be ApacheMaven 3.6.2
        ** type command %MAVEN_ HOME% : the answer has to point the directory of your MAVEN repository on your disk. On the contrary,
        that means that your path is not well parametered and needs being.
        ** ensure your machine uses the correct level encoding required for the UTF-8

    * Then type the command "mvn package" : an executable file called Escapegame-1.0-SNAPSHOT-jar-with-dependencies.jar
    will be generated in your directory on your disk.

    * To run the game type the command java -jar Escapegame-1.0-SNAPSHOT-jar-with-dependencies.jar

    * In case of any problem during the execution, you can consult by a double-click on it the generated file crawlxml.log in
    which can be read with horodated logs at INFO-level or ERROR-level the whole execution of the program.

    ## Detailed descriprion of Packages
    * This Program is built and divided in 3 packages :
        ** the first one : the Combinaisons package called combi. It is framed by an interface that defines 2 abstracts methods :
        tirer & combiner and a method print as well.
        2 classes override these methods :
            *** the CombinaisonManuelle class enables the Human Player to define his secret combinaison by answering the computer's
            questions, one after each other, as a long time as is reached the length of the secret combinaison defined by the human
            player at the beginning of the game. At this point, the computer combines automatically these answers in an ArrayList
            and creates a combinaison called "manual". The reason why this class does not belong to the PlayerJoueur Class is that
            the acting methods of this class are partially taken over by the computer to play his own game (that is the part
            combiner) and that the action of combining does not define the object player but another object (the object combinaisons)
            that doesn't define completely each player, even if they are linked.
            *** the CombinaisonAuto class enables the Player Machine to define its secret combinaison by sorting the numbers at random
            and automatically combine them which enables to create a combinaison called "auto" (ie automatic).
        One independant class CombinaisonsParams is devoted to define shared parameters taken over in each subclass Combinaisons :
        for the moment the only parameter is the length of combinaisons called nbCombinaisons. In case of error, the program run an
        exception and a parameter by default is used.

        ** the second on : the Player package called player. It is framed by an interface that define 1 abstract method :
        comparerLeslistes and a method print as well. 2 classes override this method, on fort the Player Machine and one for the
        Player Joueur.
            *** PlayerJoueur class enables the Human Player to compare each number sorted by the Player Machine in its "auto"
            combinaison with the number of his combinaison at the same rank (the same key of the ArrayList of each player) :
            if the machine's number is higher, the Human Player writes "+", if it is lower he writes "-", else he writes "=".
            The results of each comparison is concateneted in a new ArrayList called Comparison. WARNING = The computer can refuse
            the answer of the Human Player if he cheats, ie he writes "+" when the Machine's number is lower.
            *** PlayerMachine class enables the Machine Player to automatically compare each number defined by the Human Player
            to find out its secret combinaison. The result of this method is symetrically the same as the method comparer of
            the class PlayerJoueur.

        ** the third and last one : the Jeu package called jeu. In this package there is a superclass called Jeu which is
        extended by 3 other classes which are defined to run the game a 3 different patterns and in one class to run the game :
            *** Jeu Challenger in which the Human Player tries to find out the Player Machine's secret combinaison. He is considered
            to attack and he wins if he finds out this secret combinaison within a number of rounds, predefined by the Human Player.
            *** Jeu Defenseur in which the Player Machine tries to find out the Player Joueur 's secret combinaison. The Human Player
            is considered to defend and he wins if the Machine is not able to find out his secret combinaison within a number of
            rounds, predefined by the Human Player.
            *** Jeu Duel in which are alternatively played one round of each previous pattern of game that is Jeu Challenger first,
            then Jeu Defenseur. At the end of both rounds, if there is no winner, it is asked to the Player if he wants to go on or
            to quit this pattern of the game.
            *** JeuEscape Game which enables to run the entire game in the Main Class
        All the shared parameters of the Game are defined by methods in an independant class of this package called JeuParams :
            **** nomJoueur that enables the Human Player to write his name and the Machine to personalize its sentences,
            **** modeJeu that enables to choose the pattern of game he wants to run,
            **** nbTours that puts the length of the tournament in two patterns of game : Jeu Challenger & Jeu Defenseur,
            **** nbcombinaisons that puts the length of the secret "manual" and "auto" combinaisons is defined by addNbCombinaisons,
            **** modeDevelopper that enables the Human Player to ask the secrets combinaisons to be displayed in order to check if
            the game is properly run.
            In case of error for both nbTours and modeDeveloper parameters, the program run an exception and a parameter by default
            is used.

    At the end of the tournament of each pattern of game, it is asked to the Human Player if he wants to quit the game.

Dear friend, as the creator and the referee, it remains for me to wish you good luck : enjoy the game and that the best one wins.
