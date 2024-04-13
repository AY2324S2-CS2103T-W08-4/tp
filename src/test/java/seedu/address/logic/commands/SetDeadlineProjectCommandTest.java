package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;
import seedu.address.model.project.Project;
import seedu.address.testutil.ProjectBuilder;

class SetDeadlineProjectCommandTest {
    private Project taskProject = new ProjectBuilder().build();

    @Test
    public void equals() {
        Project project = new Project(new Name("project"));


        SetDeadlineProjectCommand setDeadlineA = new SetDeadlineProjectCommand("Mar 22 2024", project);
        SetDeadlineProjectCommand setDeadlineB = new SetDeadlineProjectCommand("Mar 20 2023", project);

        // same object -> returns true
        assertTrue(setDeadlineA.equals(setDeadlineA));

        // same values -> returns true
        SetDeadlineProjectCommand setDeadlineACopy = new SetDeadlineProjectCommand("Mar 22 2024", project);
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
        String deadline = "Mar 23 2024";
        SetDeadlineProjectCommand setDeadlineProjectCommand = new SetDeadlineProjectCommand(deadline, taskProject);
        String expected = SetDeadlineProjectCommand.class.getCanonicalName() + "{setDeadline=" + deadline + "}";
        assertEquals(expected, setDeadlineProjectCommand.toString());
    }
}
