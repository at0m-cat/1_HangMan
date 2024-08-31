import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Main {

    private static final String START_GAME_INFO = "Начать новую игру?";
    private static final String EXIT_GAME_INFO = "Выйти из игры?";
    private static final String START_GAME = "start";
    private static final String EXIT_GAME = "exit";
    private static final File file = new File("src/russian-nouns.txt");
    private static final List<String> strings;

    static {
        try {
            strings = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        for (String string : strings){
//            System.out.println(string);
//        }
    }
}
