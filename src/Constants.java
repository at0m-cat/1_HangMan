public class Constants {

    static final String COMMAND_PLAY = "1";
    static final String COMMAND_EXIT = "2";
    static final String INFO = "HANGMAN!%n%s - new game \n%s - exit\n".formatted(COMMAND_PLAY, COMMAND_EXIT);
    static final String NOT_LETTER = "Введите букву!";
    static final String USED_LETTER = "Буква уже была использована!";
    static final Character STAR_SYMBOL = '*';
    static final String MASK_REGEX = "[а-яa-zA-ZА-Я]";
    static final String INPUT = "input: ";

}
