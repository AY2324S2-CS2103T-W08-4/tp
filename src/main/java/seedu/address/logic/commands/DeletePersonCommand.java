package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.project.Member;

/**
 * Deletes a member from a project
 */
public class DeletePersonCommand extends Command {

    public static final String COMMAND_WORD = "delete person";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " PERSON_NAME /in PROJECT_NAME";

    public static final String MESSAGE_SUCCESS = "%1$s has been deleted from %2$s";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "Project %1$s not found: "
            + "Please make sure the project exists.";
    public static final String MESSAGE_PERSON_NOT_FOUND = "Member %1$s not found: "
            + "Please make sure the person exists in project %2$s";

    private final Member toDelete;
    private final Person memberProject;

    /**
     * Creates a DeleteTaskCommand to delete the specified task in a project
     */
    public DeletePersonCommand(Member member, Person memberProject) {
        requireNonNull(member);
        requireNonNull(memberProject);
        this.toDelete = member;
        this.memberProject = memberProject;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPerson(memberProject)) {
            throw new CommandException(String.format(
                    MESSAGE_PROJECT_NOT_FOUND,
                    Messages.format(memberProject)));
        }

        Person project = model.findPerson(memberProject.getName());

        if (!project.hasMember(toDelete)) {
            throw new CommandException(String.format(MESSAGE_PERSON_NOT_FOUND,
                    toDelete, Messages.format(memberProject)));
        }

        project.removeMember(toDelete);

        return new CommandResult(String.format(
                MESSAGE_SUCCESS,
                toDelete,
                Messages.format(memberProject)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTaskCommand)) {
            return false;
        }

        DeletePersonCommand otherDeletePersonCommand = (DeletePersonCommand) other;
        return toDelete.equals(otherDeletePersonCommand.toDelete)
                && memberProject.equals(otherDeletePersonCommand.memberProject);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toDelete", toDelete)
                .toString();
    }
}
