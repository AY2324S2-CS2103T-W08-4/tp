package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.project.Project;
import seedu.address.model.project.Task;
import seedu.address.testutil.ProjectBuilder;

class AssignPersonCommandTest {
    private Project taskProject = new ProjectBuilder().build();

    @Test
    public void toStringMethod() {
        String member = "Bob";
        Task code = new Task("Code");
        AssignPersonCommand assignPersonCommand = new AssignPersonCommand(member, code, taskProject);
        String expected = AssignPersonCommand.class.getCanonicalName() + "{set Member=" + member + "}";
        assertEquals(expected, assignPersonCommand.toString());
    }
}
