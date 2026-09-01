package maple;

import java.util.ArrayList;

/**
 * Entry point for the Maple chatbot.
 */
public class Maple {
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
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                ui.showTasks(tasks);
            } else if (command.startsWith("mark ")) {
                int index = Integer.parseInt(command.substring(5)) - 1;
                Task task = tasks.get(index);
                task.markDone();
                ui.showMarked(task);
            } else if (command.startsWith("unmark ")) {
                int index = Integer.parseInt(command.substring(7)) - 1;
                Task task = tasks.get(index);
                task.markNotDone();
                ui.showUnmarked(task);
            } else if (command.startsWith("todo ")) {
                addTask(tasks, new Todo(command.substring(5).trim()), ui);
            } else if (command.startsWith("deadline ")) {
                addTask(tasks, parseDeadline(command.substring(9)), ui);
            } else if (command.startsWith("event ")) {
                addTask(tasks, parseEvent(command.substring(6)), ui);
            } else {
                ui.showUnknownCommand();
            }
            command = ui.readCommand();
        }
        ui.showExit();
    }

    /**
     * Adds the given task to the task list and confirms the addition to the user.
     *
     * @param tasks the task list to add to
     * @param task  the task to add
     * @param ui    the user interface used to confirm the addition
     */
    private static void addTask(ArrayList<Task> tasks, Task task, Ui ui) {
        tasks.add(task);
        ui.showAdded(task, tasks.size());
    }

    /**
     * Parses the part of a deadline command after the "deadline " keyword.
     *
     * @param input the description followed by a "/by" marker and the due time
     * @return the parsed deadline
     */
    private static Deadline parseDeadline(String input) {
        String[] parts = input.split("/by");
        return new Deadline(parts[0].trim(), parts[1].trim());
    }

    /**
     * Parses the part of an event command after the "event " keyword.
     *
     * @param input the description followed by "/from" and "/to" markers and times
     * @return the parsed event
     */
    private static Event parseEvent(String input) {
        String[] parts = input.split("/from");
        String[] fromAndTo = parts[1].split("/to");
        return new Event(parts[0].trim(), fromAndTo[0].trim(), fromAndTo[1].trim());
    }
}
