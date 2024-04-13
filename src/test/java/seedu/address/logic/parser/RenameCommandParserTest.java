package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditProjectNameCommand;
import seedu.address.logic.commands.EditTaskNameCommand;
import seedu.address.model.person.Name;
import seedu.address.model.project.Project;
import seedu.address.model.project.Task;

class RenameCommandParserTest {
    private RenameCommandParser parser = new RenameCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Project project = new Project(new Name("project"));
        Task task = new Task("task");

        assertParseSuccess(parser, "A /of project",
                new EditProjectNameCommand(new Name("A"), project));

        assertParseSuccess(parser, "A /of task /in project",
                new EditTaskNameCommand(new Name("A"), project, task));

    }

    @Test
    public void parse_missingFields_failure() {
        String expectedMessageProject = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditProjectNameCommand.MESSAGE_USAGE);
        String expectedMessageTask = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditTaskNameCommand.MESSAGE_USAGE);

        // missing project name
        assertParseFailure(parser, "A /of ",
                expectedMessageProject);

        assertParseFailure(parser, "A /of task /in ",
                expectedMessageTask);

        // missing task name
        assertParseFailure(parser, "A /of  /in proj",
                "Please enter both the target task name and the project it belongs to");
    }

}
