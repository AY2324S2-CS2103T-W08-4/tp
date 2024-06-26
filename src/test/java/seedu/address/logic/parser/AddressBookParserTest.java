package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddProjectCommand;
import seedu.address.logic.commands.DeleteProjectCommand;
import seedu.address.logic.commands.EditCommand.EditProjectDescriptor;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.project.Project;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.ProjectUtil;

public class AddressBookParserTest {

    private final PlannerParser parser = new PlannerParser();

    @Test
    public void parseCommand_addProject() throws Exception {
        Project person = new ProjectBuilder().build();
        AddProjectCommand command = (AddProjectCommand) parser.parseCommand(ProjectUtil.getAddCommand(person));
        assertEquals(new AddProjectCommand(person), command);
    }

    @Test
    public void parseCommand_deleteProject() throws Exception {
        DeleteProjectCommand command = (DeleteProjectCommand) parser.parseCommand(
            DeleteProjectCommand.COMMAND_WORD + " " + "Dummy Project");
        assertEquals(new DeleteProjectCommand("Dummy Project"), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        //clear function not yet implemented
        assertEquals(0, 0);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Project person = new ProjectBuilder().build();
        EditProjectDescriptor descriptor = new EditProjectDescriptor(person);
        //edit function not yet implemented
        assertEquals(0, 0);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        //exit function not yet implemented
        assertEquals(0, 0);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        //find function not yet implemented
        assertEquals(0, 0);
    }

    @Test
    public void parseCommand_help() throws Exception {
        //help function not yet implemented
        assertEquals(0, 0);
    }

    @Test
    public void parseCommand_list() throws Exception {
        //list function not yet implemented
        assertEquals(0, 0);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
