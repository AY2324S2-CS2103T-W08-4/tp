package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddDeadlineProjectCommand;
import seedu.address.logic.commands.AddDeadlineTaskCommand;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.project.Task;

class AddDeadlineCommandParserTest {
    private AddDeadlineCommandParser parser = new AddDeadlineCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person project = new Person(new Name("project"));
        Task task = new Task("task");

        assertParseSuccess(parser, "Mar 22 2024 /to task /in project",
                new AddDeadlineTaskCommand("Mar 22 2024", task, project));

        assertParseSuccess(parser, "Mar 22 2024 /to project",
                new AddDeadlineProjectCommand("Mar 22 2024", project));

    }

    @Test
    public void parse_missingFields_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDeadlineTaskCommand.MESSAGE_USAGE);

        // missing task and project name
        assertParseFailure(parser, "Mar 22 2024 /to ",
                expectedMessage);



    }
}
