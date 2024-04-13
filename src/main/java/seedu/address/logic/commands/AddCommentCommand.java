package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameEqualsPredicate;
import seedu.address.model.project.Comment;
import seedu.address.model.project.Member;
import seedu.address.model.project.Project;

/**
 * Adds a project to the DevPlanPro.
 */
public class AddCommentCommand extends Command {

    public static final String COMMAND_WORD = "add comment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " /from PERSON_NAME"
            + " /to PROJECT_NAME";

    public static final String MESSAGE_SUCCESS = "The comment %1$s has been added to the project %2$s.";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "Project %1$s not found: "
            + "Please make sure the project exists.";

    public static final String MESSAGE_MEMBER_NOT_FOUND = "Team member %1s not found: "
            + "Please make sure the person exists.";

    private final Project commentProject;

    private final Member commentFrom;

    private Comment comment;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommentCommand(Project project, Member member, String comment) {
        requireNonNull(project);
        requireNonNull(member);
        requireNonNull(comment);
        this.commentProject = project;
        this.commentFrom = member;
        this.comment = new Comment(comment, member);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasProject(commentProject)) {
            throw new CommandException(String.format(MESSAGE_PROJECT_NOT_FOUND,
                    Messages.format(commentProject)));
        }

        Project foundProject = model.findProject(commentProject.getName());

        if (!foundProject.hasMember(commentFrom)) {
            throw new CommandException(String.format(MESSAGE_MEMBER_NOT_FOUND, commentFrom));
        }

        foundProject.addComment(comment);

        model.updateCurrentProject(
            new NameEqualsPredicate(
                model.getCurrentProject().get(0).getName().fullName));

        return new CommandResult(String.format(MESSAGE_SUCCESS, comment, Messages.format(commentProject)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommentCommand)) {
            return false;
        }

        AddCommentCommand otherAddCommentCommand = (AddCommentCommand) other;
        return commentProject.equals(otherAddCommentCommand.commentProject)
                && commentFrom.equals(otherAddCommentCommand.commentFrom)
                && comment.equals(otherAddCommentCommand.comment);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAddTo", commentProject)
                .add("toAdd", comment)
                .add("toAddFrom", commentFrom)
                .toString();
    }
}
