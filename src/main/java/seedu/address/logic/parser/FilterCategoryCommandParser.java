package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FilterCategoryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.CategoryEqualsPredicate;

/**
 * Parses input arguments and creates a new FilterCategoryCommand object
 */
public class FilterCategoryCommandParser implements Parser<FilterCategoryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCategoryCommand
     * and returns a FilterCategoryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCategoryCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCategoryCommand.MESSAGE_USAGE));
        }

        return new FilterCategoryCommand(new CategoryEqualsPredicate(trimmedArgs));
    }

}
