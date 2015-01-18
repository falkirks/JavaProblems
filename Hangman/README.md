Hangman
=======
Hangman is a multi-threaded implementation of a cheating player in the popular word game [hangman](http://en.wikipedia.org/wiki/Hangman_\(game\)). It is built in a modular fashion, with the cheating (or dodgy) player encapsulating a standard player.

### The "Cheating" Algorithm

The cheating algorithm is fast and effective. It cheats by letter only and not by arrangement of letters (as per project specification). It starts by creating a list of all the `n` letter words in the loaded dictionary. It then runs `Callable` workers to determine what words will have to be removed for all 26 letters in the alphabet. Basically these tasks just remove words with the said letter from the list and return a modified list. When the end user enters a letter, the result from that task becomes the current list. If the result from the task contains no words (all words in the list have the letter), then the algorithm will pick a random word from the old list and act as a normal player.

### The GUI

Hangman is bundled with standard GUI components in the `gui` package. For even greater convenience, Hangman comes with game "modes" which act as clients to the cheating player, one of these utilizes the GUI package.