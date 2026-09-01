package maple;

/**
 * Represents a task that must be completed by a specific time.
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Constructs a deadline with the given description and due time.
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
