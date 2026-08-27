/**
 * Entry point for the Maple chatbot.
 */
public class Maple {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcome();
        String command = ui.readCommand();
        while (!command.equals("bye")) {
            ui.echo(command);
            command = ui.readCommand();
        }
        ui.showExit();
    }
}
