package maple;

/**
 * Represents a task that takes place between a start time and an end time.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Constructs an event with the given description, start time and end time.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
