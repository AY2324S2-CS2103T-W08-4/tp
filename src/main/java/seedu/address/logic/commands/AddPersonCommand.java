package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Member;
import seedu.address.model.project.Project;

/**
 * Adds a task to a project.
 */
public class AddPersonCommand extends Command {

    public static final String COMMAND_WORD = "add person";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " /to PROJECT_NAME";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "Project %1$s not found: "
            + "Please make sure the project exists.";

    public static final String MESSAGE_SUCCESS = "The person %1$s has been assigned to the following project %2$s.";
    private final Project project;

    private final Member member;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddPersonCommand(Member member, Project project) {
        requireNonNull(project);
        requireNonNull(member);
        this.project = project;
        this.member = member;
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

        projectAssign.addMember(member);

        return new CommandResult(String.format(MESSAGE_SUCCESS, member, Messages.format(projectAssign)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddPersonCommand)) {
            return false;
        }

        AddPersonCommand otherDeletePersonCommand = (AddPersonCommand) other;
        return project.equals(otherDeletePersonCommand.project)
                && member.equals(otherDeletePersonCommand.member);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("setMember", member)
                .toString();
    }
}
