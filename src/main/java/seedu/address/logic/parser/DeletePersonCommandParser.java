package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.project.Member;
import seedu.address.model.project.Project;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeletePersonCommandParser implements Parser<DeletePersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeletePersonCommand parse(String args) throws ParseException {
        try {
            if (!args.contains(" /in ")) {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        DeletePersonCommand.MESSAGE_USAGE));
            }
            String memberName = args.split(" /in")[0];
            String projectName = args.split("/in ")[1];

            if ((memberName.length() == 0) || (projectName.length() == 0)) {
                throw new ParseException("Please enter the member and the project field");
            }
            return new DeletePersonCommand(new Member(memberName), new Project(new Name(projectName)));
        } catch (Exception e) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    DeletePersonCommand.MESSAGE_USAGE));
        }
    }

}
