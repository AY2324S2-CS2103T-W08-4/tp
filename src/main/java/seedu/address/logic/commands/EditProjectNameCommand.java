package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;



/**
 * Edits the name of an existing project in the address book.
 */
public class EditProjectNameCommand extends RenameCommand {

    public static final String MESSAGE_USAGE = RenameCommand.COMMAND_WORD + "NEW_PROJECT_NAME /of TARGET_PROJECT_NAME";

    public static final String MESSAGE_SUCCESS = "Project %1$s has been renamed to %2$s";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "Project %1$s not found: "
            + "Please make sure the project exists.";
    private final Name changeTo;
    private final Person targetProject;
    /**
     * @param newName name to be changed to
     * @param currentProject the current project, name of which to be changed to newName
     */
    public EditProjectNameCommand(Name newName, Person currentProject) {
        requireNonNull(newName);
        requireNonNull(currentProject);
        this.changeTo = newName;
        this.targetProject = currentProject;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasPerson(targetProject)) {
            throw new CommandException(String.format(
                    MESSAGE_PROJECT_NOT_FOUND,
                    Messages.format(targetProject)));
        }
        Person personToEdit = model.findPerson(targetProject.getName());
        Person newPerson = personToEdit.createEditedPerson(changeTo);
        model.setPerson(personToEdit, newPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(
                MESSAGE_SUCCESS,
                Messages.format(targetProject),
                changeTo));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditProjectNameCommand)) {
            return false;
        }

        EditProjectNameCommand otherEditProjectNameCommand = (EditProjectNameCommand) other;
        return changeTo.equals(otherEditProjectNameCommand.changeTo)
                && targetProject.equals(otherEditProjectNameCommand.targetProject);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("changeTo", changeTo)
                .add("targetProject", targetProject)
                .toString();
    }


}
