import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

class DictRuWords {
    File FILE = new File("src/russian-nouns.txt");
    List<String> STRINGS;

    {
        try {
            STRINGS = new ArrayList<>(Files.readAllLines(FILE.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
