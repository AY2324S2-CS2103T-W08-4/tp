package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;
import seedu.address.model.project.Project;
import seedu.address.testutil.ProjectBuilder;

class SetProjectStatusCommandTest {
    private Project taskProject = new ProjectBuilder().build();

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetProjectStatusCommand(null, taskProject));
        assertThrows(NullPointerException.class, () -> new SetProjectStatusCommand("complete", null));

    }
    @Test
    public void equals() {
        Project projectA = new Project(new Name("projectA"));
        Project projectB = new Project(new Name("projectB"));

        SetProjectStatusCommand setProjectStatusComplete = new SetProjectStatusCommand("complete", projectA);
        SetProjectStatusCommand setProjectStatusIncomplete = new SetProjectStatusCommand("incomplete", projectA);
        SetProjectStatusCommand setProjectStatusCompleteDiffProject = new SetProjectStatusCommand("complete", projectB);

        // same object -> returns true
        assertTrue(setProjectStatusComplete.equals(setProjectStatusComplete));

        // same values -> returns true
        SetProjectStatusCommand setProjectStatusCompleteCopy = new SetProjectStatusCommand("complete", projectA);
        assertTrue(setProjectStatusComplete.equals(setProjectStatusCompleteCopy));

        // different types -> returns false
        assertFalse(setProjectStatusComplete.equals(1));

        // null -> returns false
        assertFalse(setProjectStatusComplete.equals(null));

        // different status -> returns false
        assertFalse(setProjectStatusComplete.equals(setProjectStatusIncomplete));
        // different project -> returns false
        assertFalse(setProjectStatusComplete.equals(setProjectStatusCompleteDiffProject));

    }

    @Test
    public void toStringMethod() {
        String complete = "complete";
        SetProjectStatusCommand setProjectComplete = new SetProjectStatusCommand(complete, taskProject);
        String expectedComplete = SetProjectStatusCommand.class.getCanonicalName()
                + "{set status=" + complete + "}";
        assertEquals(expectedComplete, setProjectComplete.toString());

        String incomplete = "incomplete";
        SetProjectStatusCommand setProjectIncomplete = new SetProjectStatusCommand(incomplete, taskProject);
        String expectedIncomplete = SetProjectStatusCommand.class.getCanonicalName()
                + "{set status=" + incomplete + "}";
        assertEquals(expectedIncomplete, setProjectIncomplete.toString());

    }
}
