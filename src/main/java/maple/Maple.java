package maple;

import java.util.ArrayList;

/**
 * Entry point for the Maple chatbot.
 */
public class Maple {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    /**
     * Runs the Maple chatbot main loop.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        ArrayList<Task> tasks = new ArrayList<>();
        ui.showWelcome();
        String command = ui.readCommand();
        while (!command.equals(COMMAND_BYE)) {
            executeCommand(command, tasks, ui);
            command = ui.readCommand();
        }
        ui.showExit();
    }

    /**
     * Executes a single user command.
     */
    private static void executeCommand(String command, ArrayList<Task> tasks, Ui ui) {
        String[] parts = command.split(" ", 2);
        String keyword = parts[0];
        String detail = parts.length > 1 ? parts[1].trim() : "";
        if (keyword.equals(COMMAND_LIST)) {
            ui.showTasks(tasks);
        } else if (keyword.equals(COMMAND_MARK)) {
            setDone(tasks, detail, true, ui);
        } else if (keyword.equals(COMMAND_UNMARK)) {
            setDone(tasks, detail, false, ui);
        } else if (keyword.equals(COMMAND_TODO)) {
            addTask(tasks, new Todo(detail), ui);
        } else if (keyword.equals(COMMAND_DEADLINE)) {
            addTask(tasks, parseDeadline(detail), ui);
        } else if (keyword.equals(COMMAND_EVENT)) {
            addTask(tasks, parseEvent(detail), ui);
        } else {
            ui.showUnknownCommand();
        }
    }

    /**
     * Marks or unmarks the task at the given index.
     */
    private static void setDone(ArrayList<Task> tasks, String detail, boolean isDone, Ui ui) {
        Task task = tasks.get(Integer.parseInt(detail) - 1);
        if (isDone) {
            task.markDone();
            ui.showMarked(task);
        } else {
            task.markNotDone();
            ui.showUnmarked(task);
        }
    }

    /**
     * Adds a task to the task list and confirms it to the user.
     */
    private static void addTask(ArrayList<Task> tasks, Task task, Ui ui) {
        tasks.add(task);
        ui.showAdded(task, tasks.size());
    }

    /**
     * Parses a deadline command into a deadline.
     */
    private static Deadline parseDeadline(String input) {
        String[] parts = input.split("/by");
        return new Deadline(parts[0].trim(), parts[1].trim());
    }

    /**
     * Parses an event command into an event.
     */
    private static Event parseEvent(String input) {
        String[] parts = input.split("/from");
        String[] fromAndTo = parts[1].split("/to");
        return new Event(parts[0].trim(), fromAndTo[0].trim(), fromAndTo[1].trim());
    }
}
