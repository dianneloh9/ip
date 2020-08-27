package duke;

import duke.commands.Command;
import duke.parsers.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Main class where the program is run. */
public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /** Constructs a Duke object. */
    public Duke() {
        this.ui = new Ui();
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
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();
            try {
                Command command = Parser.parse(input);
                command.execute(taskList, ui);
                storage.save(taskList);
                isExit = command.isExit();
            } catch (Exception ex) {
                ui.show("\t " + ex.getMessage());
            }
        }
        System.exit(0);
    }
}
