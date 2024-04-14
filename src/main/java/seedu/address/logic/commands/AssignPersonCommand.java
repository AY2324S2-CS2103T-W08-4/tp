package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameEqualsPredicate;
import seedu.address.model.project.Member;
import seedu.address.model.project.Project;
import seedu.address.model.project.Task;

/**
 * Assign a person to a task.
 */
public class AssignPersonCommand extends Command {

    public static final String COMMAND_WORD = "assign person";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "PERSON_NAME"
            + "/to TASK_NAME"
            + "/in PROJECT_NAME";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "Project %1$s not found: "
            + "Please make sure the project exists.";

    public static final String MESSAGE_TASK_NOT_FOUND = "Task %1s not found: "
            + "Please make sure the task exists.";

    public static final String MESSAGE_MEMBER_NOT_FOUND = "Team member %1s not found: "
            + "Please make sure the person exists.";

    public static final String MESSAGE_SUCCESS = "The person %1$s has been assigned to the following task %2$s.";

    private final Task task;
    private final Project project;

    private final Member member;

    /**
     * Creates an AssignPersonCommand to add the specified {@code Task}
     */
    public AssignPersonCommand(String member, Task task, Project project) {
        requireNonNull(task);
        this.task = task;
        this.project = project;
        this.member = new Member(member);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Project projectAssign = model.findProject(project.getName());

        if (projectAssign.equals(null)) {
            throw new CommandException(String.format(
                    MESSAGE_PROJECT_NOT_FOUND,
                    Messages.format(project)));
        }

        if (!projectAssign.hasMember(member)) {
            throw new CommandException(String.format(MESSAGE_MEMBER_NOT_FOUND, member));
        }

        Task assignTask = projectAssign.findTask(task.getName());

        if (assignTask.equals(null)) {
            throw new CommandException(String.format(
                    MESSAGE_TASK_NOT_FOUND,
                    Messages.format(task)
            ));
        }

        assignTask.assignPerson(this.member);

        model.updateCurrentProject(
            new NameEqualsPredicate(
                model.getCurrentProject().get(0).getName().fullName));

        return new CommandResult(String.format(MESSAGE_SUCCESS, member, Messages.format(task)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignPersonCommand)) {
            return false;
        }

        AssignPersonCommand otherDeletePersonCommand = (AssignPersonCommand) other;
        return task.equals(otherDeletePersonCommand.task)
                && member.equals(otherDeletePersonCommand.member)
                && project.equals(otherDeletePersonCommand.project);
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("set Member", member)
                .toString();
    }
}
