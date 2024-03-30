package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalProjects.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddProjectCommand;
import seedu.address.model.project.Project;
import seedu.address.testutil.ProjectBuilder;

public class AddProjectCommandParserTest {
    private AddProjectCommandParser parser = new AddProjectCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Project expectedPerson = new ProjectBuilder(ALICE).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + "Alice Pauline", new AddProjectCommand(expectedPerson));

    }



    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Project expectedPerson = new ProjectBuilder(ALICE).withTags().build();
        assertParseSuccess(parser, "Alice Pauline",
                new AddProjectCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProjectCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, "",
                expectedMessage);
    }

}
