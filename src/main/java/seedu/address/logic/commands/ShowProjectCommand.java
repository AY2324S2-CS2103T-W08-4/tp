package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameEqualsPredicate;
import seedu.address.model.project.Project;

/**
 * Shows a project and displays all its project details.
 */
public class ShowProjectCommand extends Command {

    public static final String COMMAND_WORD = "show project";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " PROJECT_NAME";

    public static final String MESSAGE_SHOW_PROJECT_SUCCESS = "%1$s is now being shown.";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "Project %1$s not found: "
            + "Please make sure the project exists.";

    private final String targetName;

    public ShowProjectCommand(String targetName) {
        this.targetName = targetName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        Project targetProject = new Project(new Name(targetName));
        Project projectToDelete = null;
        for (Project project : lastShownList) {
            if (project.isSameProject(targetProject)) {
                projectToDelete = project;
                break;
            }
        }

        if (projectToDelete == null) {
            throw new CommandException(String.format(
                    MESSAGE_PROJECT_NOT_FOUND,
                    Messages.format(targetProject)));
        }

        model.updateCurrentProject(new NameEqualsPredicate(projectToDelete.getName().fullName));
        return new CommandResult(String.format(MESSAGE_SHOW_PROJECT_SUCCESS, Messages.format(projectToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ShowProjectCommand)) {
            return false;
        }

        ShowProjectCommand otherDeleteCommand = (ShowProjectCommand) other;
        return targetName.equals(otherDeleteCommand.targetName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetName", targetName)
                .toString();
    }
}
