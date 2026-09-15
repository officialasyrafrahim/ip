package maple.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import maple.task.Deadline;
import maple.task.Event;
import maple.task.Task;
import maple.task.Todo;

/**
 * Saves and loads the task list to and from a data file.
 */
public class Storage {
    private static final String FIELD_SEPARATOR = " | ";

    private final Path filePath;

    /**
     * Constructs a storage backed by the given file path.
     */
    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    /**
     * Loads the tasks stored in the data file.
     *
     * @return the stored tasks, or an empty list if the file does not exist.
     * @throws IOException if the file exists but cannot be read.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            return tasks;
        }
        for (String line : Files.readAllLines(filePath)) {
            Task task = parseLine(line);
            if (task != null) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * Writes the given tasks to the data file, creating the folder if needed.
     *
     * @param tasks the tasks to save.
     * @throws IOException if the file cannot be written.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        if (filePath.getParent() != null) {
            Files.createDirectories(filePath.getParent());
        }
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(task.toFileFormat());
        }
        Files.write(filePath, lines);
    }

    /**
     * Parses one data file line into a task.
     */
    private static Task parseLine(String line) {
        String[] parts = line.split(Pattern.quote(FIELD_SEPARATOR));
        Task task = createTask(parts);
        if (task != null && parts[1].equals("1")) {
            task.markDone();
        }
        return task;
    }

    /**
     * Creates the task described by the given data file fields.
     */
    private static Task createTask(String[] parts) {
        String type = parts[0];
        if (type.equals(Todo.TYPE)) {
            return new Todo(parts[2]);
        } else if (type.equals(Deadline.TYPE)) {
            return new Deadline(parts[2], parts[3]);
        } else if (type.equals(Event.TYPE)) {
            return new Event(parts[2], parts[3], parts[4]);
        } else {
            return null;
        }
    }
}
