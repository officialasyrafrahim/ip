package maple;

/**
 * Represents a task with no additional date or time attached.
 */
public class Todo extends Task {
    /**
     * Constructs a todo with the given description.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
