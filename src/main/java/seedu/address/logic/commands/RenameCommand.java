package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Rename the task or a project.
 */
public abstract class RenameCommand extends Command {
    public static final String COMMAND_WORD = "set name";
    public abstract CommandResult execute(Model model) throws CommandException;

}
