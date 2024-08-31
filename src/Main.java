import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Main {

    private static final String START_GAME_INFO = "Начать новую игру?";
    private static final String EXIT_GAME_INFO = "Выйти из игры?";
    private static final String START_GAME = "start";
    private static final String EXIT_GAME = "exit";
    private static final File FILE = new File("src/russian-nouns.txt");
    private static final List<String> STRINGS;
    private static int errorCount = 0;

    static {
        try {
            STRINGS = Files.readAllLines(FILE.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printedGallows(){
        // прибавляем к счетчику ошибок 1,
        // рисуем части виселицы (6)
    }

    public static void generationWord(){
        // генерируем слово
    }

    public static void openingLetters(){
        // если в слове есть буква - открываем, изначально слово замаскировано
    }

    public static void endedGame(){
        // выход из игры
    }

    public static void startGame(){
        // цикл игры
    }

    public static void action(){
        // выбор действия - новая игра , выход из игры
    }

    public static void main(String[] args) {

    }
}
