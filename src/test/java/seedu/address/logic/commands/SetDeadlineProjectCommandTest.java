package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class SetDeadlineProjectCommandTest {
    private Person taskProject = new PersonBuilder().build();

    @Test
    public void equals() {
        Person project = new Person(new Name("project"));


        AddDeadlineProjectCommand setDeadlineA = new AddDeadlineProjectCommand("Mar 22 2024", project);
        AddDeadlineProjectCommand setDeadlineB = new AddDeadlineProjectCommand("Mar 20 2023", project);

        // same object -> returns true
        assertTrue(setDeadlineA.equals(setDeadlineA));

        // same values -> returns true
        AddDeadlineProjectCommand setDeadlineACopy = new AddDeadlineProjectCommand("Mar 22 2024", project);
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
        AddDeadlineProjectCommand setDeadlineProjectCommand = new AddDeadlineProjectCommand(deadline, taskProject);
        String expected = AddDeadlineProjectCommand.class.getCanonicalName() + "{setDeadline=" + deadline + "}";
        assertEquals(expected, setDeadlineProjectCommand.toString());
    }
}
