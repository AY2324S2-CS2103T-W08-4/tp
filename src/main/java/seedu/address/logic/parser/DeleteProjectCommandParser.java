package seedu.address.logic.parser;

import seedu.address.logic.commands.DeleteProjectCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new DeleteProjectCommand object
 */
public class DeleteProjectCommandParser implements Parser<DeleteProjectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteProjectCommand
     * and returns a DeleteProjectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteProjectCommand parse(String args) throws ParseException {
        args = args.trim();
        if (args.length() == 0 || !Name.isValidName(args)) {
            throw new ParseException("Project name should be alphanumerical and not empty.");
        }
        return new DeleteProjectCommand(args);
    }

}
