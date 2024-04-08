package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.project.Member;



class AddPersonCommandParserTest {
    private Person taskProject = new Person(new Name("Code"));
    private Member member = new Member("James");
    private AddPersonCommandParser parser = new AddPersonCommandParser();

    @Test
    public void parse_validArgs_returnsAddPersonCommand() {
        assertParseSuccess(parser, "James /to Code", new AddPersonCommand(member, taskProject));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(
                parser,
                "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE));

        assertParseFailure(
                parser,
                "/to Code",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE));

        assertParseFailure(
                parser,
                "James /to",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE));
    }

}
