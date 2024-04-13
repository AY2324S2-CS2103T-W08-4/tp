package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameEqualsPredicate;
import seedu.address.model.project.Project;
import seedu.address.model.project.Task;

/**
 * Adds a task to a project.
 */
public class SetTaskStatusCommand extends SetStatusCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " [complete/incomplete] /of TASK_NAME /in PROJECT_NAME";

    public static final String MESSAGE_SUCCESS = "The task %1$s is set as %2$s.";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "Project %1$s not found: "
            + "Please make sure the project exists.";

    public static final String MESSAGE_TASK_NOT_FOUND = "Task %1$s not found: "
            + "Please make sure the task exists in project %2$s";

    private final Task task;
    private final Project project;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public SetTaskStatusCommand(String status, Task task, Project project) {
        super(status);
        requireNonNull(task);
        requireNonNull(project);
        this.task = task;
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

        if (!statusProject.hasTask(task)) {
            throw new CommandException(String.format(
                    MESSAGE_TASK_NOT_FOUND,
                    Messages.format(task),
                    Messages.format(project)));
        }

        Task statusTask = statusProject.findTask(task.getName());
        String resultString = "";

        if (isCompleted()) {
            statusTask.setComplete();
            resultString = String.format(MESSAGE_SUCCESS, Messages.format(statusTask), "complete");

        } else if (isIncompleted()) {
            statusTask.setIncomplete();
            resultString = String.format(MESSAGE_SUCCESS, Messages.format(statusTask), "incomplete");
        } else {
            throw new CommandException(String.format(MESSAGE_WRONG_FORMAT_STATUS));
        }

        model.updateCurrentProject(
            new NameEqualsPredicate(
                model.getCurrentProject().get(0).getName().fullName));
        return new CommandResult(resultString);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetTaskStatusCommand)) {
            return false;
        }

        SetTaskStatusCommand otherSetStatusCommand = (SetTaskStatusCommand) other;
        return project.equals(otherSetStatusCommand.project)
                && task.equals(otherSetStatusCommand.task)
                && super.equals(otherSetStatusCommand);
    }
}
