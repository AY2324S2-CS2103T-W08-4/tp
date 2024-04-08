package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SetDeadlineProjectCommand;
import seedu.address.logic.commands.SetDeadlineTaskCommand;
import seedu.address.model.person.Name;
import seedu.address.model.project.Project;
import seedu.address.model.project.Task;

class SetDeadlineCommandParserTest {
    private SetDeadlineCommandParser parser = new SetDeadlineCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Project project = new Project(new Name("project"));
        Task task = new Task("task");

        assertParseSuccess(parser, "Mar 22 2024 /to task /in project",
                new SetDeadlineTaskCommand("Mar 22 2024", task, project));

        assertParseSuccess(parser, "Mar 22 2024 /to project",
                new SetDeadlineProjectCommand("Mar 22 2024", project));

    }

    @Test
    public void parse_missingFields_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetDeadlineTaskCommand.MESSAGE_USAGE);

        // missing task and project name
        assertParseFailure(parser, "Mar 22 2024 /to ",
                expectedMessage);
    }
}
