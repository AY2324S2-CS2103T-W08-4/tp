package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.project.Member;
import seedu.address.model.project.Project;

/**
 * Parses input arguments and creates a new AddPersonCommand object
 */
public class AddPersonCommandParser implements Parser<AddPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPersonCommand
     * and returns an AddPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPersonCommand parse(String args) throws ParseException {
        try {
            if (!args.contains(" /to ")) { // Check if the input correctly uses "/to"
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        AddPersonCommand.MESSAGE_USAGE));
            }
            String memberName = args.split(" /to")[0];
            String projectName = args.split("/to ")[1];
            memberName = memberName.trim();
            projectName = projectName.trim();
            if ((memberName.length() == 0) || (projectName.length() == 0)) {
                throw new ParseException("Please enter the member and project fields.");
            }
            Member member = new Member(memberName);
            Name name = ParserUtil.parseName(projectName);
            Project project = new Project(name);
            return new AddPersonCommand(member, project);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    AddPersonCommand.MESSAGE_USAGE));
        } catch (IllegalArgumentException e) {
            throw new ParseException("Please enter valid names.");
        }
    }
}
