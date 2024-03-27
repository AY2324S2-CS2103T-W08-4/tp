package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddDeadlineCommand;
import seedu.address.logic.commands.AddDeadlineProjectCommand;
import seedu.address.logic.commands.AddDeadlineTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.project.Task;


/**
 * Parses input arguments and creates a new SetDeadlineCommand object
 */
public class AddDeadlineCommandParser implements Parser<AddDeadlineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDeadlineCommand parse(String args) throws ParseException {
        try {
            if (!args.contains(" /to ")) {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        AddDeadlineTaskCommand.MESSAGE_USAGE));
            }
            String deadline = args.split(" /to")[0].trim();
            if (!args.contains(" /in ")) {
                //SetDeadlineProjectCommand
                String projectName = args.split("/to ")[1];
                if ((projectName.length() == 0)) {
                    throw new ParseException("Please enter the project field");
                }
                Person project = new Person(ParserUtil.parseName(projectName));
                return new AddDeadlineProjectCommand(deadline, project);
            } else {
                //SetDeadlineTaskCommand
                String taskAndProject = args.split(" /to")[1].trim();
                String taskName = taskAndProject.split("/in ")[0].trim();
                String projectName = taskAndProject.split("/in ")[1];
                if ((projectName.length() == 0) || (taskName.length() == 0)) {
                    throw new ParseException("Please enter the project and task fields");
                }
                Person project = new Person(ParserUtil.parseName(projectName));
                Task newTask = new Task(taskName);
                return new AddDeadlineTaskCommand(deadline, newTask, project);
            }


        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    AddDeadlineTaskCommand.MESSAGE_USAGE));
        }
    }
}
