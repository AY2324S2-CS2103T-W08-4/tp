package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddCommentCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.project.Member;


/**
 * Parses input arguments and creates a new AddProjectCommand object
 */
public class AddCommentCommandParser implements Parser<AddCommentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddProjectCommand
     * and returns an AddProjectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommentCommand parse(String args) throws ParseException {
        try {
            if (!args.contains(" /from ")) { // Check if the input correctly uses "/to"
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        AddCommentCommand.MESSAGE_USAGE));
            }
            if (!args.contains(" /to ")) { // Check if the input correctly uses "/to"
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        AddCommentCommand.MESSAGE_USAGE));
            }
            String comment = args.split(" /from ")[0];
            String memberAndProject = args.split(" /from ")[1];
            if (comment.length() == 0) {
                throw new ParseException("Please enter a comment");
            }
            String memberName = memberAndProject.split(" /to ")[0];
            String projectName = memberAndProject.split(" /to ")[1];
            if (memberName.length() == 0 || projectName.length() == 0) {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        AddTaskCommand.MESSAGE_USAGE));
            }
            Member member = new Member(memberName);
            Name name = ParserUtil.parseName(projectName);
            Person project = new Person(name);
            return new AddCommentCommand(project, member, comment);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommentCommand.MESSAGE_USAGE));
        }
    }
}
