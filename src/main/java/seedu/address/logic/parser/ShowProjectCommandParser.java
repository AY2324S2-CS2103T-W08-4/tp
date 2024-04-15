package seedu.address.logic.parser;

import seedu.address.logic.commands.ShowProjectCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new ShowProjectCommand object
 */
public class ShowProjectCommandParser implements Parser<ShowProjectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ShowProjectCommand
     * and returns a ShowProjectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowProjectCommand parse(String args) throws ParseException {
        args = args.trim();
        if (args.length() == 0 || !Name.isValidName(args)) {
            throw new ParseException("Project name should be alphanumerical and not empty.");
        }
        System.out.println(args);
        return new ShowProjectCommand(args);
    }

}
