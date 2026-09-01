package maple;

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

    /**
     * Returns the status icon representing whether the task is done.
     *
     * @return "X" if the task is done, or a blank space otherwise
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}