import java.util.*;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(Constants.INFO);
            String command = scanner.nextLine();
            switch (command.toLowerCase()) {
                case Constants.COMMAND_PLAY -> {
                    setParamToGame();
                    gameLoop();
                }
                case Constants.COMMAND_EXIT -> System.exit(0);
                default -> System.out.println("Неизвестная команда, введите ответ еще раз");
            }
        }
    }

    public static void setParamToGame() {
        Hangman.secretWord = Hangman.generationSecretWord();
        Hangman.userWord = Hangman.setMaskToText(Hangman.secretWord);
        Hangman.userPastLetters = new ArrayList<>();
        Hangman.gallowsCount = 0;
    }

    public static void gameLoop() {
//        System.out.printf("Загадано [RU] слово: %s, отгадайте буквы!%nДопускается 5 ошибок%n",
//                Hangman.setMaskToText(Hangman.secretWord));
        Hangman.infoStartText();

        String formedWordUser = "";

        while (true) {
            if (Hangman.isWin(Hangman.userWord)) {
                Hangman.textForWinners();
                break;
            }
            if (Hangman.isLoss(Hangman.gallowsCount)) {
                Hangman.textForLosers();
                break;
            }
            formedWordUser = Hangman.userWord;
            String letterUser = Hangman.addLetterUser();
            if (Hangman.isPastLetters(letterUser)) {
                System.out.println(Constants.USED_LETTER);
                continue;
            }
            if (Hangman.isLetter(letterUser)) {
                Hangman.addUserLetterToList(letterUser);
                if (Hangman.isUserLetterInSecretWord(letterUser)) {
                    Hangman.userWord = Hangman.openLetterInSecretWord(formedWordUser, letterUser);
                } else {
                    System.out.println(Hangman.getGallows(Hangman.gallowsCount++));
                }
                Hangman.currentState(letterUser);
            } else {
                System.out.println(letterUser);
            }

        }
    }
}
