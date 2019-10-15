This program aims to enable a human player known as the Player Joueur to play an escape game against the computer known as the Player Machine.

It is built and divided in 3 packages :

- the first one : the Combinaisons package. It is framed by an interface that defines 2 abstracts methods : tirer & combiner and
a method print as well.
2 classes override these methods :
    - the CombinaisonManuelle class enables the Human Player to define his secret combinaison by answering the computer's questions,
    one after each other, as a long time as is reached the length of the secret combinaison defined by the human player at the
    beginning of the game. At this point, the computer combines automatically these answers in an ArrayList and creates
    a combinaison called "manual". The reason why this class does not belong to the PlayerJoueur Class is that the acting
    methods of this class are partially taken over by the computer to play his own game (that is the part combiner).
    - the CombinaisonAuto class enables the Player Machine to define its secret combinaison by sorting the numbers at random
    and automatically combine them which enables to create a combinaison called "auto" (ie automatic).

- the second on : the Player package. It is framed by an interface that define 1 abstract method : comparerLeslistes and a method
print as well. 2 classes override this method, on fort the Player Machine and one for the Player Joueur.
    - PlayerJoueur class enables the Human Player to compare each number sorted by the Player Machine in its "auto" combinaison
    with the number of his combinaison at the same rank (the same key of the ArrayList of each player) : if the machine's number
    is higher, the Human Player writes "+", if it is lower he writes "-", else he writes "=". The results of each comparison
    is concateneted in a new ArrayList called Comparison. WARNING = The computer can refuse the answer of the Human Player
    if he cheats, ie he writes "+" when the Machine's number is lower.
    - PlayerMachine class enables the Machine Player to automatically compare each number defined by the Human Player to find out
    its secret combinaison. The result of this method is symetrically the same as the method comparer of the class PlayerJoueur.

- the third and last one : the package call Jeu. In this package there is a superclass called Jeu which is extended by 3 other
classes which are defined to run the game a 3 different patterns :
    - Jeu Challenger in which the Human Player tries to find out the Player Machine's secret combinaison. He is considered to
    attack and he wins if he finds out this secret combinaison within a number of rounds, predefined by the Human Player.
    - Jeu Defenseur in which the Player Machine tries to find out the Player Joueur 's secret combinaison. The Human Player is
    considered to defend and he wins if the Machine is not able to find out his secret combinaison within a number of rounds,
    predefined by the Human Player.
    - Jeu Duel in which are alternatively played one round of each previous pattern of game that is Jeu Challenger first, then
    Jeu Defenseur. At the end of both rounds, if there is no winner, it is asked to the Player if he wants to go on or
    to quit this pattern of the game.
All the shared parameters of the Game are defined by methods in an independant class of this package called JeuParams :
    - nomJoueur that enables the Human Player to write his name and the Machine to personalize its sentences,
    - modeJeu that enables to choose the pattern of game he wants to run,
    - nbTours that puts the length of the tournament in two patterns of game : Jeu Challenger & Jeu Defenseur,
    - nbcombinaisons that puts the length of the secret "manual" and "auto" combinaisons is defined by addNbCombinaisons,
    - modeDevelopper that enables the Human Player to ask the secrets combinaisons to be displayed in order to check if the game
    is properly run.
At the end of the tournament of each pattern of game, it is asked to the Human Player if he wants to quit the game. If he
answers OUI, there is an exit 0.

All classes and methods are commented in Javadoc.

Dear friend, as the creator and the referee, it remains for me to wish you good luck and that the best one wins.
