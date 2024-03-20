package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;

/**
 * Adds a person to the address book.
 */
public class AddProjectCommand extends Command {

    public static final String COMMAND_WORD = "add project";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a project to the planner. "
            + "Parameters: "
            + "PROJECT_NAME ";

    public static final String MESSAGE_SUCCESS = "%1$s has been added to the project list.";
    public static final String MESSAGE_DUPLICATE_PROJECT = "Project %1$s already exists";

    private final Project toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddProjectCommand(Project project) {
        requireNonNull(project);
        toAdd = project;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProject(toAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PROJECT, Messages.format(toAdd)));
        }

        model.addProject(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddProjectCommand)) {
            return false;
        }

        AddProjectCommand otherAddProjectCommand = (AddProjectCommand) other;
        return toAdd.equals(otherAddProjectCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
