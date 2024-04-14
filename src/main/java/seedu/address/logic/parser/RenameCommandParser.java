package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.EditProjectNameCommand;
import seedu.address.logic.commands.EditTaskNameCommand;
import seedu.address.logic.commands.RenameCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.project.Project;
import seedu.address.model.project.Task;

/**
 * Parses input arguments and creates a new RenameCommand object
 */
public class RenameCommandParser implements Parser<RenameCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RenameCommand
     * and returns an RenameCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RenameCommand parse(String args) throws ParseException {
        try {
            if (!args.contains(" /of ")) {
                throw new ParseException("Whoops! When referring to another field like a project or task,"
                        + " always remember to put /of instead of just of.");
            }
            String newName = args.split(" /of ")[0];
            String possibleTargetName = args.split(" /of ")[1];
            if ((newName.length() == 0) || (possibleTargetName.length() == 0)) {
                throw new ParseException("Please enter both the target and new project name or both target task and "
                        + "the project it belongs to if you want to rename a specific task");
            }
            Name changedTo = ParserUtil.parseName(newName);
            if (args.contains(" /in ")) {
                return parseEditTaskNameCommand(possibleTargetName, changedTo);
            } else {
                return parseEditProjectNameCommand(possibleTargetName, changedTo);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            if (args.contains(" /in ")) {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        EditTaskNameCommand.MESSAGE_USAGE));
            } else {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        EditProjectNameCommand.MESSAGE_USAGE));
            }
        }
    }

    private EditTaskNameCommand parseEditTaskNameCommand(String possibleTargetName,
                                                         Name changedTo) throws ParseException {
        String taskName = possibleTargetName.split(" /in ")[0];
        String projectName = possibleTargetName.split(" /in ")[1];
        if ((taskName.length() == 0) || (projectName.length() == 0)) {
            throw new ParseException("Please enter both the target task name and the project it belongs to");
        }
        Name targetProjectName = ParserUtil.parseName(projectName);
        Project targetProject = new Project(targetProjectName);
        Task targetTask = new Task(taskName);
        return new EditTaskNameCommand(changedTo, targetProject, targetTask);
    }

    private EditProjectNameCommand parseEditProjectNameCommand(String possibleTargetName,
                                                               Name changedTo) throws ParseException {
        Name name = ParserUtil.parseName(possibleTargetName);
        Project targetProject = new Project(name);
        return new EditProjectNameCommand(changedTo, targetProject);
    }

}
