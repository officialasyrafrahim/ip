package maple;

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

    /**
     * Prints the welcome message with the Maple banner, framed by separators.
     */
    public void showWelcome() {
        System.out.println(SEPARATOR);
        System.out.println(BANNER);
        System.out.println(" Hello! I'm Maple.");
        System.out.println(" What can I do for you?");
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
     * @param task      the task that was added
     * @param taskCount the number of tasks now in the list
     */
    public void showAdded(Task task, int taskCount) {
        System.out.println(SEPARATOR);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        System.out.println(SEPARATOR);
        System.out.println();
    }

    /**
     * Displays the list of tasks, each prefixed with its position and done status.
     *
     * @param tasks the tasks to display
     */
    public void showTasks(ArrayList<Task> tasks) {
        System.out.println(SEPARATOR);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(SEPARATOR);
        System.out.println();
    }

    /**
     * Confirms that the given task has been marked as done.
     *
     * @param task the task that was marked as done
     */
    public void showMarked(Task task) {
        System.out.println(SEPARATOR);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        System.out.println(SEPARATOR);
        System.out.println();
    }

    /**
     * Confirms that the given task has been marked as not done.
     *
     * @param task the task that was marked as not done
     */
    public void showUnmarked(Task task) {
        System.out.println(SEPARATOR);
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        System.out.println(SEPARATOR);
        System.out.println();
    }

    /**
     * Informs the user that the command entered is not recognised.
     */
    public void showUnknownCommand() {
        System.out.println(SEPARATOR);
        System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(SEPARATOR);
        System.out.println();
    }

    /**
     * Prints the farewell message, framed by separators.
     */
    public void showExit() {
        System.out.println(SEPARATOR);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(SEPARATOR);
    }
}
