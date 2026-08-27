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
            } else {
                tasks.add(new Task(command));
                ui.showAdded(command);
            }
            command = ui.readCommand();
        }
        ui.showExit();
    }
}