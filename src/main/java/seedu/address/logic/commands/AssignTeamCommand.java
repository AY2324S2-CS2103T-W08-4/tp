package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.project.Member;

/**
 * Adds a task to a project.
 */
public class AssignTeamCommand extends Command {

    public static final String COMMAND_WORD = "assign team";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "PERSON_NAME, PERSON_NAME, PERSON_NAME"
            + "/to PROJECT_NAME";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "Project %1$s not found: "
            + "Please make sure the project exists.";

    public static final String MESSAGE_SUCCESS = "The team %1$s has been assigned to the following project %2$s.";
    private final Person project;

    private final List<Member> team;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AssignTeamCommand(List<String> team, Person project) {
        requireNonNull(project);
        requireNonNull(team);

        this.project = project;
        this.team = new ArrayList<>();
        for (String name : team) {
            this.team.add(new Member(name));
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person projectAssign = model.findPerson(project.getName());

        if (projectAssign.equals(null)) {
            throw new CommandException(String.format(
                    MESSAGE_PROJECT_NOT_FOUND,
                    Messages.format(project)));
        }

        projectAssign.assignTeam(this.team);

        return new CommandResult(String.format(MESSAGE_SUCCESS, team, Messages.format(projectAssign)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignTeamCommand)) {
            return false;
        }

        AssignTeamCommand otherDeletePersonCommand = (AssignTeamCommand) other;
        return project.equals(otherDeletePersonCommand.project)
                && team.equals(otherDeletePersonCommand.team);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("set Team", team)
                .toString();
    }
}
