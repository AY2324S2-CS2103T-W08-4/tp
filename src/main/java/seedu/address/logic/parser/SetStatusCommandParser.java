package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SetProjectStatusCommand;
import seedu.address.logic.commands.SetStatusCommand;
import seedu.address.logic.commands.SetTaskStatusCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.Project;
import seedu.address.model.project.Task;


/**
 * Parses input arguments and creates a new SetStatusCommand object
 */
public class SetStatusCommandParser implements Parser<SetStatusCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SetStatusCommand
     * and returns an SetStatusCommand object for execution.
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
            if (!status.equals("complete") && !status.equals("incomplete")) {
                throw new ParseException("Please enter a valid status: complete/incomplete.");
            }
            String taskAndProject = args.split(" /of")[1].trim();
            if (!args.contains(" /in ")) {
                if ((taskAndProject.length() == 0) || (status.length() == 0)) {
                    throw new ParseException("Please enter the status and project fields.");
                }
                Project project = new Project(ParserUtil.parseName(taskAndProject));
                return new SetProjectStatusCommand(status, project);
            }
            return parseSetTaskStatusCommand(taskAndProject, status);
        } catch (IndexOutOfBoundsException e) {
            if (args.contains(" /in ")) {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        SetTaskStatusCommand.MESSAGE_USAGE));
            }
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    SetProjectStatusCommand.MESSAGE_USAGE));
        }
    }

    private SetTaskStatusCommand parseSetTaskStatusCommand(String taskAndProject, String status) throws ParseException {
        if ((taskAndProject.length() == 0) || (status.length() == 0)) {
            throw new ParseException("Please enter the status, project and task fields.");
        }
        String taskName = taskAndProject.split("/in ")[0].trim();
        String projectName = taskAndProject.split("/in ")[1];
        if ((projectName.length() == 0) || (taskName.length() == 0) || (status.length() == 0)) {
            throw new ParseException("Please enter the status, project and task fields.");
        }
        Project project = new Project(ParserUtil.parseName(projectName));
        Task newTask = new Task(taskName);
        return new SetTaskStatusCommand(status, newTask, project);
    }

}
