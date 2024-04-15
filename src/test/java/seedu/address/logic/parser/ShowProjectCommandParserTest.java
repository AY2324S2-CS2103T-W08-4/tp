package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShowProjectCommand;

class ShowProjectCommandParserTest {
    private ShowProjectCommandParser parser = new ShowProjectCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        assertParseSuccess(parser, "project", new ShowProjectCommand("project"));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {

        // missing project name
        assertParseFailure(parser, "",
                "Project name should be alphanumerical and not empty.");

    }
}
