package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.AddTaskCommand.MESSAGE_DUPLICATE_TASK;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameEqualsPredicate;
import seedu.address.model.project.Project;
import seedu.address.model.project.Task;


/**
 * Rename a task inside a project.
 */
public class EditTaskNameCommand extends RenameCommand {
    public static final String MESSAGE_USAGE = RenameCommand.COMMAND_WORD
            + " NEW_TASK_NAME /of TARGET_TASK_NAME"
            + " /in PROJECT_NAME";

    public static final String MESSAGE_SUCCESS = "Task %1$s of project %2$s has been renamed to %3$s";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "Project %1$s not found: "
            + "Please make sure the project exists.";
    public static final String MESSAGE_TASK_NOT_FOUND = "Task %1$s not found: "
            + "Please make sure the task exists in project %2$s";
    private final Name changeTo;
    private final Project targetProject;
    private final Task targetTask;

    /**
     * Creates an EditTaskNameCommand to add the specified {@code Task}
     */
    public EditTaskNameCommand(Name newName, Project currentProject, Task currentTask) {
        requireNonNull(newName);
        requireNonNull(currentProject);
        requireNonNull(currentTask);
        this.changeTo = newName;
        this.targetProject = currentProject;
        this.targetTask = currentTask;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasProject(targetProject)) {
            throw new CommandException(String.format(
                    MESSAGE_PROJECT_NOT_FOUND,
                    Messages.format(targetProject)));
        }
        Project targetPerson = model.findProject(targetProject.getName());
        if (!targetPerson.hasTask(targetTask)) {
            throw new CommandException(String.format(
                    MESSAGE_TASK_NOT_FOUND,
                    Messages.format(targetTask),
                    Messages.format(targetPerson)));
        }
        Task duplicateTask = new Task(changeTo.fullName);
        if (targetPerson.hasTask(duplicateTask)) {
            throw new CommandException(String.format(
                    MESSAGE_DUPLICATE_TASK,
                    Messages.format(duplicateTask),
                    Messages.format(targetPerson)));
        }
        Task toBeChanged = targetPerson.findTask(targetTask.getName());
        toBeChanged.setName(changeTo);
        model.updateCurrentProject(
                new NameEqualsPredicate(
                        model.getCurrentProject().get(0).getName().fullName));
        return new CommandResult(String.format(
                MESSAGE_SUCCESS,
                Messages.format(targetTask),
                Messages.format(targetProject),
                changeTo));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTaskNameCommand)) {
            return false;
        }

        EditTaskNameCommand otherEditTaskNameCommand = (EditTaskNameCommand) other;
        return changeTo.equals(otherEditTaskNameCommand.changeTo)
                && targetProject.equals(otherEditTaskNameCommand.targetProject)
                && targetTask.equals(otherEditTaskNameCommand.targetTask);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetTask", targetTask)
                .add("changeTo", changeTo)
                .add("targetProject", targetProject)
                .toString();
    }
}
