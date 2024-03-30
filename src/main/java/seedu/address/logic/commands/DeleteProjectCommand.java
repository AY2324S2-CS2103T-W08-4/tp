package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;

/**
 * Deletes a project identified using it's name from the project list.
 */
public class DeleteProjectCommand extends Command {

    public static final String COMMAND_WORD = "delete project";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " PROJECT_NAME\n";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "Project %1$s not found: "
            + "Please make sure the project exists.";

    public static final String MESSAGE_DELETE_PROJECT_SUCCESS = "%1$s has been deleted from the project list.";

    private final String targetName;

    private final Project project;

    /**
     * @param targetName name of project which needs to be deleted
     */
    public DeleteProjectCommand(String targetName) {
        this.targetName = targetName;
        project = new Project(targetName);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasProject(project)) {
            throw new CommandException(String.format(MESSAGE_PROJECT_NOT_FOUND, Messages.format(project)));
        }

        List<Project> lastShownList = model.getFilteredProjectList();

        Project targetProject = new Project(targetName);
        Project projectToDelete = null;
        for (Project project : lastShownList) {
            if (project.isSameProject(targetProject)) {
                projectToDelete = project;
                break;
            }
        }


        model.deleteProject(projectToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PROJECT_SUCCESS, Messages.format(projectToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteProjectCommand)) {
            return false;
        }

        DeleteProjectCommand otherDeleteCommand = (DeleteProjectCommand) other;
        return targetName.equals(otherDeleteCommand.targetName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetName", targetName)
                .toString();
    }
}
