import java.util.ArrayList;

/**
 * Entry point for the Maple chatbot.
 */
public class Maple {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ArrayList<String> tasks = new ArrayList<>();
        ui.showWelcome();
        String command = ui.readCommand();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                ui.showTasks(tasks);
            } else {
                tasks.add(command);
                ui.showAdded(command);
            }
            command = ui.readCommand();
        }
        ui.showExit();
    }
}
