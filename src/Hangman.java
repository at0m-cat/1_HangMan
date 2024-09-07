import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    static ArrayList<String> userPastLetters;
    static String secretWord;
    static String userWord;
    static int gallowsCount = 0;

    public static String generationSecretWord() {
        DictRuWords dict = new DictRuWords();
        String wordInDict = "";
        while (wordInDict.length() < 6) {
            wordInDict = dict.STRINGS.get(new Random().nextInt(dict.STRINGS.size()));
        }
        return wordInDict;
    }

    public static String openLetterInSecretWord(String userWord, String letter) {
        StringBuilder outUserWord = new StringBuilder(userWord);
        for (int i = 0; i < outUserWord.length(); i++) {
            if (Character.toLowerCase(secretWord.charAt(i)) == letter.charAt(0)) {
                outUserWord.setCharAt(i, secretWord.charAt(i));
            }
        }
        return String.valueOf(outUserWord);
    }

    public static void addUserLetterToList(String letter) {
        if (!isPastLetters(letter)) {
            userPastLetters.add(letter);
        }
    }

    public static void currentState(String userLetter){

        if(secretWord.contains(userLetter)){
            System.out.println("В слове есть данная буква!");
        }
        System.out.println(getUserLettersToString());
        System.out.println(getUserWord());
    }

    public static String getGallows(int gallowsCount) {
        return GallowsAnsi.gallows[gallowsCount];
    }

    public static String setMaskToText(String text) {
        return text.replaceAll(Constants.MASK_REGEX, Constants.STAR_SYMBOL.toString());
    }

    public static String getUserLettersToString() {
        return userPastLetters.toString();
    }

    public static String addLetterUser() {
        Scanner scanner = new Scanner(System.in);
        String textUser = scanner.nextLine();
        if (isLetter(textUser)) {
            return textUser.toLowerCase();
        }
        return Constants.NOT_LETTER;
    }

    public static String getUserWord() {
        return userWord;
    }

    public static boolean isLetter(String text) {
        return text.strip().length() == 1 &&
                Character.isLetter(text.charAt(0)) &&
                text.matches(Constants.MASK_REGEX);
    }

    public static boolean isWin(String userWord) {
        return secretWord.equals(userWord);
    }

    public static boolean isLoss(int countGallows) {
        return GallowsAnsi.gallows.length == countGallows;
    }

    public static boolean isPastLetters(String letterUser) {
        return userPastLetters.contains(letterUser);
    }

    public static boolean isUserLetterInSecretWord(String letter) {
        return secretWord.contains(letter);
    }

//    public static boolean isGameOver(){
//        return isWin(userWord) || isLoss(gallowsCount);
//    }

    public static void textForWinners(){
        System.out.println("Вы победили. Можете начать игру снова, либо выйти.\n");
    }

    public static void textForLosers(){
        System.out.printf("Вы проиграли. Тайное слово: %s. Можете сыграть еще, либо выйти.\n", secretWord.toUpperCase());
    }

    public static void infoStartText(){
        System.out.printf("Загадано [RU] слово: \"%s\" из %d букв!%nОтгадайте буквы! Допускается 5 ошибок. Вводи букву и жми [enter]!%n",
                setMaskToText(Hangman.secretWord), secretWord.length());
    }
}
