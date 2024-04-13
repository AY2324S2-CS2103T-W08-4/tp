package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.StringUtil.generateRandomDigitString;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.project.Project;

/**
 * Adds a task to a project.
 */
public class SetProjectStatusCommand extends SetStatusCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " [complete/incomplete] /to PROJECT_NAME";

    public static final String MESSAGE_SUCCESS = "The project %1$s is set as %2$s.";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "Project %1$s not found: "
            + "Please make sure the project exists.";

    private final Project project;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public SetProjectStatusCommand(String status, Project project) {
        super(status);
        requireNonNull(project);
        this.project = project;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasProject(project)) {
            throw new CommandException(String.format(
                    MESSAGE_PROJECT_NOT_FOUND,
                    Messages.format(project)));
        }

        Project statusProject = model.findProject(project.getName());

        Project dupProject = statusProject.createEditedProject(
                new Name(generateRandomDigitString(20).toString())); // duplicate project with dummy name
        model.setProject(statusProject, dupProject);
        Project realProject = dupProject.createEditedProject(project.getName());
        String resultString = "";
        if (isCompleted()) {
            realProject.setComplete();
            resultString = String.format(MESSAGE_SUCCESS, Messages.format(statusProject), "complete");
        } else if (isIncompleted()) {
            realProject.setIncomplete();
            resultString = String.format(MESSAGE_SUCCESS, Messages.format(statusProject), "incomplete");
        } else {
            throw new CommandException(String.format(MESSAGE_WRONG_FORMAT_STATUS));
        }
        model.setProject(dupProject, realProject);

        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(resultString);
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
