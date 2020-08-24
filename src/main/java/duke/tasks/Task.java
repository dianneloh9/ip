package duke.tasks;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toFileString() {
        return String.format("? | %d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}
