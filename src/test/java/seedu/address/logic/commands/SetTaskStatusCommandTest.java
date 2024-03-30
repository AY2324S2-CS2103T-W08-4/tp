package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.project.Task;
import seedu.address.testutil.PersonBuilder;

class SetTaskStatusCommandTest {
    private Person taskProject = new PersonBuilder().build();

    @Test
    public void equals() {
        Person project = new Person(new Name("project"));
        Task task = new Task("task");

        SetTaskStatusCommand setStatusComplete = new SetTaskStatusCommand("complete", task, project);
        SetTaskStatusCommand setStatusIncomplete = new SetTaskStatusCommand("incomplete", task, project);

        // same object -> returns true
        assertTrue(setStatusComplete.equals(setStatusComplete));

        // same values -> returns true
        SetTaskStatusCommand setStatusCompleteCopy = new SetTaskStatusCommand("complete", task, project);
        assertTrue(setStatusComplete.equals(setStatusCompleteCopy));

        // different types -> returns false
        assertFalse(setStatusComplete.equals(1));

        // null -> returns false
        assertFalse(setStatusComplete.equals(null));

        // different person -> returns false
        assertFalse(setStatusComplete.equals(setStatusIncomplete));
    }

    @Test
    public void toStringMethod() {
        Task code = new Task("Code");
        String statusComplete = "complete";
        SetTaskStatusCommand setStatusCommandComplete = new SetTaskStatusCommand("complete", code, taskProject);
        String expectedComplete = SetTaskStatusCommand.class.getCanonicalName()
                + "{set status=" + statusComplete + "}";
        assertEquals(expectedComplete, setStatusCommandComplete.toString());

        String statusIncomplete = "incomplete";
        SetTaskStatusCommand setStatusCommandIncomplete = new SetTaskStatusCommand("incomplete", code, taskProject);
        String expectedIncomplete = SetTaskStatusCommand.class.getCanonicalName()
                + "{set status=" + statusIncomplete + "}";
        assertEquals(expectedIncomplete, setStatusCommandIncomplete.toString());

    }
}
