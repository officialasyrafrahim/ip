package maple;

/**
 * Represents a task that must be completed by a specific time.
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Constructs a new deadline with the given description and due time.
     *
     * @param description the description of the deadline
     * @param by          the time by which the task must be completed
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
