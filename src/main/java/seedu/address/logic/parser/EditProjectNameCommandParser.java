package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.EditProjectNameCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class EditProjectNameCommandParser implements Parser<EditProjectNameCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditProjectNameCommand
     * and returns an EditProjectNameCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditProjectNameCommand parse(String args) throws ParseException {
        try {
            if (!args.contains(" /of ")) {
                throw new ParseException("Whoops! When referring to another field like a project,"
                        + " always remember to put /of instead of just of.");
            }
            String newName = args.split(" /of ")[0];
            String targetName = args.split(" /of ")[1];
            if ((newName.length() == 0) || (targetName.length() == 0)) {
                throw new ParseException("Please enter both the target and new project name");
            }
            Name name = ParserUtil.parseName(targetName);
            Name changedTo = ParserUtil.parseName(newName);
            Person targetProject = new Person(name);
            return new EditProjectNameCommand(changedTo, targetProject);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    EditProjectNameCommand.MESSAGE_USAGE));
        }
    }
}
