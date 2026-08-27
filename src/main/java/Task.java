/**
 * Represents a task in Maple's task list.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a new undone task with the given description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}
