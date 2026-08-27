import java.util.ArrayList;
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
     * Confirms that the given task has been added, framed by separators.
     *
     * @param task the task that was added
     */
    public void showAdded(String task) {
        System.out.println(SEPARATOR);
        System.out.println("     added: " + task);
        System.out.println(SEPARATOR);
        System.out.println();
    }

    /**
     * Displays the list of tasks, each prefixed with its position, framed by separators.
     *
     * @param tasks the tasks to display
     */
    public void showTasks(ArrayList<String> tasks) {
        System.out.println(SEPARATOR);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println(SEPARATOR);
        System.out.println();
    }

    public void showExit() {
        System.out.println(SEPARATOR);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(SEPARATOR);
    }
}
