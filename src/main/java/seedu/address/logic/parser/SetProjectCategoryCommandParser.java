package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SetProjectCategoryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

/**
 * Parses input arguments and creates a new SetProjectCategoryCommand object
 */
public class SetProjectCategoryCommandParser implements Parser<SetProjectCategoryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetProjectCategoryCommand parse(String args) throws ParseException {
        try {
            if (!args.contains(" /to ")) {
                throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        SetProjectCategoryCommand.MESSAGE_USAGE));
            }
            String category = args.split(" /to")[0].trim();
            String projectName = args.split("/to ")[1];
            if ((projectName.length() == 0)) {
                throw new ParseException("Please enter the project field");
            }
            Person project = new Person(ParserUtil.parseName(projectName));
            return new SetProjectCategoryCommand(category, project);

        } catch (Exception e) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    SetProjectCategoryCommand.MESSAGE_USAGE));
        }
    }

}
