package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommentCommand;
import seedu.address.model.person.Name;
import seedu.address.model.project.Member;
import seedu.address.model.project.Project;



class AddCommentCommandParserTest {
    private Project taskProject = new Project(new Name("Code"));
    private Member member = new Member("James");
    private AddCommentCommandParser parser = new AddCommentCommandParser();

    @Test
    public void parse_validArgs_returnsAddPersonCommand() {
        assertParseSuccess(parser, "A /from James /to Code", new AddCommentCommand(taskProject, member, "A"));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(
                parser,
                "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommentCommand.MESSAGE_USAGE));

        assertParseFailure(
                parser,
                "/from James /to Code",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommentCommand.MESSAGE_USAGE));

        assertParseFailure(
                parser,
                "A /from James /to",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommentCommand.MESSAGE_USAGE));

        assertParseFailure(
                parser,
                "A /from /to Code",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommentCommand.MESSAGE_USAGE));
    }


}
