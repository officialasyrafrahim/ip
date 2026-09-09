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
    private static final String DEADLINE_MARKER = "/by";
    private static final String EVENT_START_MARKER = "/from";
    private static final String EVENT_END_MARKER = "/to";

    /**
     * Runs the Maple chatbot main loop.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        ArrayList<Task> tasks = new ArrayList<>();

        ui.showWelcome();
        String command = ui.readCommand().trim();

        while (!COMMAND_BYE.equals(command)) {
            try {
                executeCommand(command, tasks, ui);
            } catch (MapleException exception) {
                ui.showError(exception.getMessage());
            }
            command = ui.readCommand().trim();
        }

        ui.showExit();
    }

    /**
     * Executes a single user command.
     *
     * @param command the command entered by the user.
     * @param tasks the task list to update.
     * @param ui the interface used to display the result.
     * @throws MapleException if the command is invalid.
     */
    private static void executeCommand(String command, ArrayList<Task> tasks, Ui ui) throws MapleException {
        String[] parts = command.split("\\s+", 2);
        String keyword = parts[0];
        String detail = parts.length > 1 ? parts[1].trim() : "";

        switch (keyword) {
        case COMMAND_LIST:
            if (!detail.isEmpty()) {
                throw new MapleException("I don't recognize that command.");
            }
            ui.showTasks(tasks);
            break;
        case COMMAND_MARK:
            Task taskToMark = getTask(tasks, detail, COMMAND_MARK);
            taskToMark.markDone();
            ui.showMarked(taskToMark);
            break;
        case COMMAND_UNMARK:
            Task taskToUnmark = getTask(tasks, detail, COMMAND_UNMARK);
            taskToUnmark.markNotDone();
            ui.showUnmarked(taskToUnmark);
            break;
        case COMMAND_TODO:
            addTask(tasks, parseTodo(detail), ui);
            break;
        case COMMAND_DEADLINE:
            addTask(tasks, parseDeadline(detail), ui);
            break;
        case COMMAND_EVENT:
            addTask(tasks, parseEvent(detail), ui);
            break;
        default:
            throw new MapleException("I don't recognize that command.");
        }
    }

    private static Task getTask(ArrayList<Task> tasks, String detail, String action) throws MapleException {
        if (detail.isEmpty()) {
            throw new MapleException("Specify the number of the task to " + action + ".");
        }

        int index;
        try {
            index = Integer.parseInt(detail) - 1;
        } catch (NumberFormatException exception) {
            throw new MapleException("A task number must be a whole number.");
        }

        if (tasks.isEmpty()) {
            throw new MapleException("There are no tasks to update.");
        }
        if (index < 0 || index >= tasks.size()) {
            throw new MapleException("Choose a task number from 1 to " + tasks.size() + ".");
        }
        return tasks.get(index);
    }

    /**
     * Adds a task to the task list and confirms it to the user.
     */
    private static void addTask(ArrayList<Task> tasks, Task task, Ui ui) {
        tasks.add(task);
        ui.showAdded(task, tasks.size());
    }

    private static Todo parseTodo(String description) throws MapleException {
        if (description.isEmpty()) {
            throw new MapleException("A todo needs a description.");
        }
        return new Todo(description);
    }

    private static Deadline parseDeadline(String input) throws MapleException {
        int markerIndex = findMarker(input, DEADLINE_MARKER, 0);
        String description = markerIndex < 0 ? input : input.substring(0, markerIndex).trim();
        if (description.isEmpty()) {
            throw new MapleException("A deadline needs a description.");
        }
        if (markerIndex < 0) {
            throw new MapleException("A deadline needs a date or time after /by.");
        }

        String dueTime = input.substring(markerIndex + DEADLINE_MARKER.length()).trim();
        if (dueTime.isEmpty()) {
            throw new MapleException("A deadline needs a date or time after /by.");
        }
        return new Deadline(description, dueTime);
    }

    private static Event parseEvent(String input) throws MapleException {
        int startMarkerIndex = findMarker(input, EVENT_START_MARKER, 0);
        int endMarkerStart = startMarkerIndex < 0
                ? 0
                : startMarkerIndex + EVENT_START_MARKER.length();
        int endMarkerIndex = findMarker(input, EVENT_END_MARKER, endMarkerStart);
        String description = startMarkerIndex < 0 ? input : input.substring(0, startMarkerIndex).trim();
        if (description.isEmpty()) {
            throw new MapleException("An event needs a description.");
        }
        if (startMarkerIndex < 0) {
            throw new MapleException("An event needs a start after /from.");
        }
        if (endMarkerIndex < 0) {
            throw new MapleException("An event needs an end after /to.");
        }

        String startTime = input.substring(
                startMarkerIndex + EVENT_START_MARKER.length(), endMarkerIndex).trim();
        String endTime = input.substring(endMarkerIndex + EVENT_END_MARKER.length()).trim();
        if (startTime.isEmpty()) {
            throw new MapleException("An event needs a start after /from.");
        }
        if (endTime.isEmpty()) {
            throw new MapleException("An event needs an end after /to.");
        }
        return new Event(description, startTime, endTime);
    }

    private static int findMarker(String input, String marker, int startIndex) {
        int markerIndex = input.indexOf(marker, startIndex);
        while (markerIndex >= 0) {
            int markerEndIndex = markerIndex + marker.length();
            boolean hasLeadingBoundary = markerIndex == 0 || Character.isWhitespace(input.charAt(markerIndex - 1));
            boolean hasTrailingBoundary = markerEndIndex == input.length()
                    || Character.isWhitespace(input.charAt(markerEndIndex));
            if (hasLeadingBoundary && hasTrailingBoundary) {
                return markerIndex;
            }
            markerIndex = input.indexOf(marker, markerEndIndex);
        }
        return -1;
    }
}
