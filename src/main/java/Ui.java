import java.util.Scanner;

/**
 * Handles Maple's user-facing input and output.
 */
public class Ui {
    private static final String SEPARATOR = "_".repeat(60);
    private static final String BANNER = "   __  ___          __   \n"
            + "  /  |/  /__ ____  / /__ \n"
            + " / /|_/ / _ `/ _ \\/ / -_)\n"
            + "/_/  /_/\\_,_/ .__/_/\\__/ \n"
            + "           /_/           ";

    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println(SEPARATOR);
        System.out.println(BANNER);
        System.out.println("Hello! I'm Maple.");
        System.out.println("What can I do for you?");
        System.out.println(SEPARATOR);
        System.out.println();
    }

    /**
     * Reads the next line of user input.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Echoes the given command back to the user, framed by separators.
     *
     * @param command the command to echo
     */
    public void echo(String command) {
        System.out.println(SEPARATOR);
        System.out.println("     " + command);
        System.out.println(SEPARATOR);
        System.out.println();
    }

    public void showExit() {
        System.out.println(SEPARATOR);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(SEPARATOR);
    }
}
