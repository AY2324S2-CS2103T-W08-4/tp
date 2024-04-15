package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AssignPersonCommand;
import seedu.address.model.person.Name;
import seedu.address.model.project.Member;
import seedu.address.model.project.Project;
import seedu.address.model.project.Task;



class AssignPersonCommandParserTest {
    private Project taskProject = new Project(new Name("Code"));
    private Task testing = new Task("testing");
    private Member member = new Member("James");
    private AssignPersonCommandParser parser = new AssignPersonCommandParser();

    @Test
    public void parse_validArgs_returnsAddPersonCommand() {
        assertParseSuccess(parser, "James /to testing /in Code",
                new AssignPersonCommand("James", testing, taskProject));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {

        assertParseFailure(
                parser,
                " /to testing /in Code",
                "Please enter the task, project and member fields.");

        assertParseFailure(
                parser,
                "James /to testing /in ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignPersonCommand.MESSAGE_USAGE));

        assertParseFailure(
                parser,
                "James /to /in Code",
                "Please enter the task, project and member fields.");
    }
}
