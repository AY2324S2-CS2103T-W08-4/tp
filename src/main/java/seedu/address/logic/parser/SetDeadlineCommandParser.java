package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import seedu.address.logic.commands.SetDeadlineCommand;
import seedu.address.logic.commands.SetDeadlineProjectCommand;
import seedu.address.logic.commands.SetDeadlineTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.Project;
import seedu.address.model.project.Task;


/**
 * Parses input arguments and creates a new SetDeadlineCommand object
 */
public class SetDeadlineCommandParser implements Parser<SetDeadlineCommand> {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d uuuu");

    /**
     * Parses the given {@code String} of arguments in the context of the SetDeadlineCommand
     * and returns an SetDeadlineCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetDeadlineCommand parse(String args) throws ParseException {
        try {
            if (!args.contains(" /to ")) {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        SetDeadlineTaskCommand.MESSAGE_USAGE));
            }
            String deadline = args.split(" /to")[0].trim();
            formatter.withResolverStyle(ResolverStyle.STRICT);
            LocalDate.parse(deadline, formatter.withResolverStyle(ResolverStyle.STRICT));
            if (!args.contains(" /in ")) {
                //SetDeadlineProjectCommand
                String projectName = args.split("/to ")[1];
                if ((projectName.length() == 0)) {
                    throw new ParseException("Please enter the project field.");
                }
                Project project = new Project(ParserUtil.parseName(projectName));
                return new SetDeadlineProjectCommand(deadline, project);
            } else {
                return parseSetDeadlineTaskCommand(args);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    SetDeadlineTaskCommand.MESSAGE_USAGE));
        } catch (DateTimeParseException e) {
            throw new ParseException("Please enter valid date.");
        }
    }

    private SetDeadlineTaskCommand parseSetDeadlineTaskCommand(String args) throws ParseException {
        String deadline = args.split(" /to")[0].trim();
        String taskAndProject = args.split(" /to")[1].trim();
        String taskName = taskAndProject.split("/in ")[0].trim();
        String projectName = taskAndProject.split("/in ")[1];
        if ((projectName.length() == 0) || (taskName.length() == 0)) {
            throw new ParseException("Please enter the project and task fields.");
        }
        Project project = new Project(ParserUtil.parseName(projectName));
        Task newTask = new Task(taskName);
        return new SetDeadlineTaskCommand(deadline, newTask, project);
    }
}
