package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SetProjectStatusCommand;
import seedu.address.logic.commands.SetStatusCommand;
import seedu.address.logic.commands.SetTaskStatusCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.project.Task;


/**
 * Parses input arguments and creates a new SetStatusCommand object
 */
public class SetStatusCommandParser implements Parser<SetStatusCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetStatusCommand parse(String args) throws ParseException {
        try {
            if (!args.contains(" /of ")) {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        SetTaskStatusCommand.MESSAGE_USAGE));
            }

            String status = args.split(" /of")[0].trim();
            String taskAndProject = args.split(" /of")[1].trim();

            if (!args.contains(" /in ")) {
                if ((taskAndProject.length() == 0) || (status.length() == 0)) {
                    throw new ParseException("Please enter the status and project fields");
                }
                Person project = new Person(ParserUtil.parseName(taskAndProject));
                return new SetProjectStatusCommand(status, project);
            }

            String taskName = taskAndProject.split("/in ")[0].trim();
            String projectName = taskAndProject.split("/in ")[1];
            if ((projectName.length() == 0) || (taskName.length() == 0) || (status.length() == 0)) {
                throw new ParseException("Please enter the status, project and task fields");
            }
            Person project = new Person(ParserUtil.parseName(projectName));
            Task newTask = new Task(taskName);
            return new SetTaskStatusCommand(status, newTask, project);
        } catch (IndexOutOfBoundsException e) {
            if (args.contains(" /in ")) {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        SetTaskStatusCommand.MESSAGE_USAGE));
            } else {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        SetProjectStatusCommand.MESSAGE_USAGE));
            }

        }
    }
}
