package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Adds a status to a task or project.
 */
public abstract class SetStatusCommand extends Command {

    public static final String COMMAND_WORD = "set status";

    private final String status;

    /**
     * Creates an SetStatusCommand
     */
    public SetStatusCommand(String status) {
        requireNonNull(status);
        this.status = status;
    }

    protected boolean isCompleted() {
        return status.equals("complete");
    }

    protected boolean isIncompleted() {
        return status.equals("incomplete");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetStatusCommand)) {
            return false;
        }

        SetStatusCommand otherSetStatusCommand = (SetStatusCommand) other;
        return status.equals(otherSetStatusCommand.status);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("set status", status)
                .toString();
    }
}
