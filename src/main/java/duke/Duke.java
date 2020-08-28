package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.exceptions.DukeException;
import duke.inputoutput.InputOutput;
import duke.parsers.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/** Main class where the program is run. */
public class Duke {

    private InputOutput inputOutput;
    private TaskList taskList;
    private Storage storage;

    /** Constructs a Duke object. */
    public Duke() {
        this.inputOutput = new InputOutput();
        this.storage = new Storage();
        this.taskList = storage.load();
    }

    /** Main driver method. */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /** Runs the program until termination. */
    public void run() {
        inputOutput.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            String input = inputOutput.readCommand();
            try {
                CommandResult result = getResult(input);
                inputOutput.show(result.getFeedbackToUser());
                storage.save(taskList);
                isExit = result.isExit();
            } catch (Exception ex) {
                inputOutput.show("\t " + ex.getMessage());
            }
        }
        System.exit(0);
    }

    /** Gets the CommandResult from user input.
     *
     * @param input The user input.
     * @return The CommandResult.
     */
    public CommandResult getResult(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(taskList);
        } catch (DukeException e) {
            return new CommandResult(e.getMessage(), false);
        }
    }
}
