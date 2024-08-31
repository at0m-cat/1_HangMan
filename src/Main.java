import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Main {

    private static final File FILE = new File("src/russian-nouns.txt");
    private static final List<String> STRINGS;
    private static final String WIN = "Вы победили!";
    private static final String LOOSE = "Вы проиграли!";
    private static final String INFO = """
            N - new game
            E - exit""";
    private static int errorCount = 0;
    private static boolean isGame = true;
    private static boolean isOpenLetter = false;
    private static String secretWord;


    static {
        try {
            STRINGS = Files.readAllLines(FILE.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printedGallows() {
        // прибавляем к счетчику ошибок 1,
        // рисуем части виселицы (6)
        switch (errorCount) {
            case (1) -> {
                System.out.println("""
                        |
                        |   1 / 6
                        |
                        |
                        |
                        |
                        |
                        |
                        |____________________""");
            }
            case (2) -> {
                System.out.println("""
                        |-----------
                        |   2 / 6
                        |
                        |
                        |
                        |
                        |
                        |
                        |____________________""");
            }
            case (3) -> {
                System.out.println("""
                        |-----------
                        |   3 / 6  |
                        |          |
                        |
                        |
                        |
                        |
                        |
                        |____________________""");
            }
            case (4) -> {
                System.out.println("""
                        |-----------
                        |   4 / 6  |
                        |          |
                        |         (_)
                        |
                        |
                        |
                        |
                        |____________________""");
            }
            case (5) -> {
                System.out.println("""
                        |-----------
                        |   5 / 6  |
                        |          |
                        |         (_)
                        |        --|--
                        |
                        |
                        |
                        |____________________""");
            }
            case (6) -> {
                System.out.println("""
                        |-----------
                        |   6 / 6  |
                        |          |
                        |         (_)
                        |        --|--
                        |          |
                        |        _/ \\_
                        |
                        |____________________""");
            }
            default -> {
                System.out.println("ERROR");
            }
        }
    }

    public static String generationWord() {
        // генерируем слово
        boolean isSize = false;
        while (!isSize) {
            secretWord = STRINGS.get(new Random().nextInt(STRINGS.size()));
            isSize = secretWord.length() > 6;
        }
        return secretWord;
    }

    public static StringBuilder findLetter(StringBuilder word, char letter) {
        for (int i = 0; i < word.length(); i++) {
            if (Character.toLowerCase(secretWord.charAt(i)) == letter) {
                word.setCharAt(i, secretWord.charAt(i));
            }
        }
        return word;
    }

    public static void equalsWords(String word1, String word2) {
        if (word1.equals(word2)) {
            errorCount += 1;
            printedGallows();
            if (errorCount == 6) {
                System.out.println(LOOSE);
                System.out.println("Слово: " + secretWord);
                isOpenLetter = true;
            }
        }
        if (secretWord.equals(word2)) {
            System.out.println(WIN);
            isOpenLetter = true;
        }
    }

    public static void toOpenLetters(String word) {
        String openWord = word.contains("*") ? word : word.replaceAll("[а-яА-Я]", "*");
        StringBuilder updatedWord = new StringBuilder(openWord);
        ArrayList<String> symbols = new ArrayList<>();
        System.out.println("Загадано слово: " + openWord);
        while (!isOpenLetter) {
            String oldWord = updatedWord.toString();
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (line.trim().length() == 1) {
                if (symbols.contains(line)) {
                    System.out.println("Эта БУКВА уже ПРОВЕРЕНА");
                }
                if (!symbols.contains(line)) {
                    symbols.add(line);
                    updatedWord = findLetter(updatedWord, line.toLowerCase().charAt(0));
                    equalsWords(oldWord, updatedWord.toString());
                }
                System.out.println("Состояние слова: " + updatedWord);
                System.out.println("Использованные буквы: " + symbols);

            } else {
                System.out.println("Введите ОДНУ букву!");
            }
        }
        System.out.println(INFO);
    }

    public static void endedGame() {
        // выход из игры
        isGame = false;
    }

    public static void startGame() {
        // механика игры
        isOpenLetter = false;
        errorCount = 0;
        toOpenLetters(generationWord());
    }

    public static void action(String command) {
        // выбор действия - новая игра, выход из игры
        switch (command.strip().toLowerCase()) {
            case "n" -> {
                startGame();
            }
            case "e" -> {
                endedGame();
            }
            default -> System.out.println(INFO);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(INFO);
        while (isGame) {
            String line = scanner.nextLine();
            if (line.trim().length() == 1) {
                action(line);
            } else {
                System.out.println("Введите ОДНУ букву!");
            }
        }
    }
}
