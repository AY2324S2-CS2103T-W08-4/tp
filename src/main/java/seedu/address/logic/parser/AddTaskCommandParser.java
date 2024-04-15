package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.project.Project;
import seedu.address.model.project.Task;


/**
 * Parses input arguments and creates a new AddTaskCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns an AddTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        try {
            if (!args.contains(" /to ")) { // Check if the input correctly uses "/to"
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        AddTaskCommand.MESSAGE_USAGE));
            }
            String taskName = args.split(" /to")[0];
            String projectName = args.split("/to ")[1];
            if(!Name.isValidName(projectName) || !Name.isValidName(taskName)) {
                throw new ParseException("Names should be alphanumerical and not empty.");
            }
            ParserUtil.parseName(taskName);
            Task task = new Task(taskName);
            Name name = ParserUtil.parseName(projectName);
            Project project = new Project(name);
            return new AddTaskCommand(task, project);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException(String.format(
                MESSAGE_INVALID_COMMAND_FORMAT,
                AddTaskCommand.MESSAGE_USAGE));
        }
    }
}
