package maple;

/**
 * Represents a task that takes place between a start time and an end time.
 */
public class Event extends Task {
    private final String startTime;
    private final String endTime;

    /**
     * Constructs an event with the given description, start time and end time.
     *
     * @param description the description of the event.
     * @param startTime the date or time at which the event starts.
     * @param endTime the date or time at which the event ends.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
