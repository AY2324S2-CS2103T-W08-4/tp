package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddCommentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.project.Member;
import seedu.address.model.project.Project;


/**
 * Parses input arguments and creates a new AddCommentCommand object
 */
public class AddCommentCommandParser implements Parser<AddCommentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommentCommand
     * and returns an AddCommentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommentCommand parse(String args) throws ParseException {
        try {
            if (!args.contains(" /from ") || !args.contains(" /to ")) {
                // Check if the input correctly uses "/to" or "/from"
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        AddCommentCommand.MESSAGE_USAGE));
            }
            String comment = args.split(" /from ")[0].trim();
            String memberAndProject = args.split(" /from ")[1];
            if (comment.length() == 0) {
                throw new ParseException("Please enter a comment.");
            }
            String memberName = memberAndProject.split(" /to ")[0];
            String projectName = memberAndProject.split(" /to ")[1];
            if (memberName.length() == 0 || projectName.length() == 0
                || !Name.isValidName(projectName) || !Name.isValidName(memberName)) {
                throw new ParseException("Names should be alphanumerical and not empty.");
            }
            Member member = new Member(memberName);
            Name name = ParserUtil.parseName(projectName);
            Project project = new Project(name);
            return new AddCommentCommand(project, member, comment);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommentCommand.MESSAGE_USAGE));
        }
    }
}
