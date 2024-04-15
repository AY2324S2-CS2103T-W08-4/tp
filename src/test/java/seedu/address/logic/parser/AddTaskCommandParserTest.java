package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.person.Name;
import seedu.address.model.project.Project;
import seedu.address.model.project.Task;


class AddTaskCommandParserTest {

    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Project project = new Project(new Name("project"));
        Task task = new Task("task");

        assertParseSuccess(parser, "task /to project", new AddTaskCommand(task, project));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing project name
        assertParseFailure(parser, "task",
                expectedMessage);

        assertParseFailure(parser, "task /to ",
                expectedMessage);
    }
}
