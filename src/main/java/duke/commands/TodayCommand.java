package duke.commands;

import static duke.utils.Messages.MESSAGE_TODAY;

import java.util.ArrayList;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.tasks.TimeBased;

/**
 * Represents the command that displays all tasks happening today to the user when executed.
 */
public class TodayCommand extends Command {

    /**
     * Returns a CommandResult containing all tasks happening today to the user.
     *
     * @param taskList The taskList involved.
     * @return The result of the command.
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        ArrayList<Task> tasksToday = getTasksToday(taskList);
        // todo: sort tasks
        String response = ListCommand.tasksToString(tasksToday, MESSAGE_TODAY);
        return new CommandResult(response, false);
    }

    private ArrayList<Task> getTasksToday(TaskList taskList) {
        ArrayList<Task> tasksToday = new ArrayList<>();
        taskList.getTasks().forEach(task -> {
            if (task instanceof TimeBased) {
                TimeBased timeBased = (TimeBased) task;
                if (timeBased.getTime().isToday()) {
                    tasksToday.add(task);
                }
            }
        });
        return tasksToday;
    }

}
