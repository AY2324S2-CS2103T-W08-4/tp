package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;
import seedu.address.model.project.Project;
import seedu.address.model.project.Task;
import seedu.address.testutil.ProjectBuilder;

class SetDeadlineTaskCommandTest {
    private Project taskProject = new ProjectBuilder().build();

    @Test
    public void equals() {
        Project project = new Project(new Name("project"));
        Task task = new Task("task");

        SetDeadlineTaskCommand setDeadlineA = new SetDeadlineTaskCommand("Mar 22 2024", task, project);
        SetDeadlineTaskCommand setDeadlineB = new SetDeadlineTaskCommand("Mar 20 2023", task, project);

        // same object -> returns true
        assertTrue(setDeadlineA.equals(setDeadlineA));

        // same values -> returns true
        SetDeadlineTaskCommand setDeadlineACopy = new SetDeadlineTaskCommand("Mar 22 2024", task, project);
        assertTrue(setDeadlineA.equals(setDeadlineACopy));

        // different types -> returns false
        assertFalse(setDeadlineA.equals(1));

        // null -> returns false
        assertFalse(setDeadlineA.equals(null));

        // different person -> returns false
        assertFalse(setDeadlineA.equals(setDeadlineB));
    }

    @Test
    public void toStringMethod() {
        Task code = new Task("Code");
        String deadline = "Mar 23 2024";
        SetDeadlineTaskCommand setDeadlineTaskCommand = new SetDeadlineTaskCommand(deadline, code, taskProject);
        String expected = SetDeadlineTaskCommand.class.getCanonicalName() + "{setDeadline=" + deadline + "}";
        assertEquals(expected, setDeadlineTaskCommand.toString());
    }
}
