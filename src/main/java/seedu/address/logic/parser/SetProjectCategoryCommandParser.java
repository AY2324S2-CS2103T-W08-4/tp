package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SetProjectCategoryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.Project;

/**
 * Parses input arguments and creates a new SetProjectCategoryCommand object
 */
public class SetProjectCategoryCommandParser implements Parser<SetProjectCategoryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SetProjectCategoryCommand
     * and returns a SetProjectCategoryCommand object for execution.
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
            if (category.length() == 0) {
                throw new ParseException("Please enter the category.");
            }
            Project project = new Project(ParserUtil.parseName(projectName));
            return new SetProjectCategoryCommand(category, project);

        } catch (IllegalArgumentException e) {
            throw new ParseException("Names should be alphanumerical and not empty.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException(String.format(
                        MESSAGE_INVALID_COMMAND_FORMAT,
                        SetProjectCategoryCommand.MESSAGE_USAGE));
        }
    }

}
