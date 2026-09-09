package maple.task;

/**
 * Represents a task that must be completed by a specific time.
 */
public class Deadline extends Task {
    private final String dueTime;

    /**
     * Constructs a deadline with the given description and due time.
     *
     * @param description the description of the deadline.
     * @param dueTime the date or time by which the deadline must be completed.
     */
    public Deadline(String description, String dueTime) {
        super(description);
        this.dueTime = dueTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueTime + ")";
    }
}
