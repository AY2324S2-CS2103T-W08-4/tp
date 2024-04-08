package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a task to a project.
 */
public class SetProjectStatusCommand extends SetStatusCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " [complete/incomplete] /to PROJECT_NAME";

    public static final String MESSAGE_SUCCESS = "The project %1$s is set as %2$s.";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "Project %1$s not found: "
            + "Please make sure the project exists.";

    private final Person project;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public SetProjectStatusCommand(String status, Person project) {
        super(status);
        requireNonNull(project);
        this.project = project;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPerson(project)) {
            throw new CommandException(String.format(
                    MESSAGE_PROJECT_NOT_FOUND,
                    Messages.format(project)));
        }

        Person statusProject = model.findPerson(project.getName());

        if (isCompleted()) {
            statusProject.setComplete();
            return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(statusProject), "complete"));
        } else if (isIncompleted()) {
            statusProject.setIncomplete();
            return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(statusProject), "incomplete"));
        } else {
            throw new CommandException(String.format(MESSAGE_WRONG_FORMAT_STATUS));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetProjectStatusCommand)) {
            return false;
        }

        SetProjectStatusCommand otherSetStatusCommand = (SetProjectStatusCommand) other;
        return project.equals(otherSetStatusCommand.project)
                && super.equals(otherSetStatusCommand);
    }
}
