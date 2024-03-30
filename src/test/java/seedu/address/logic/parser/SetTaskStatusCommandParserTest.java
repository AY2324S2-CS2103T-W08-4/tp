package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SetTaskStatusCommand;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.project.Task;

class SetTaskStatusCommandParserTest {
    private SetStatusCommandParser parser = new SetStatusCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person project = new Person(new Name("project"));
        Task task = new Task("task");

        assertParseSuccess(
            parser,
            "complete /of task /in project",
            new SetTaskStatusCommand("complete", task, project));

    }

    @Test
    public void parse_missingFields_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetTaskStatusCommand.MESSAGE_USAGE);

        // missing task and project name
        assertParseFailure(parser, "complete",
                expectedMessage);

        // missing project name
        assertParseFailure(parser, "complete /of task",
                expectedMessage);

    }
}
