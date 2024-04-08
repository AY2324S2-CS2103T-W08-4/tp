package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.project.Task;
import seedu.address.testutil.PersonBuilder;



class EditTaskNameCommandTest {
    private Person taskProject = new PersonBuilder().build();
    private Task code = new Task("Code");
    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditTaskNameCommand(null, taskProject, code));
        assertThrows(NullPointerException.class, () -> new EditTaskNameCommand(new Name("A"), null, code));
        assertThrows(NullPointerException.class, () -> new EditTaskNameCommand(new Name("A"), taskProject, null));

    }

    @Test
    public void equals() {
        Person project = new Person(new Name("project"));
        Task speech = new Task("Speech");


        EditTaskNameCommand editTaskNameToA = new EditTaskNameCommand(new Name("A"), project, code);
        EditTaskNameCommand editTaskNameToB = new EditTaskNameCommand(new Name("B"), project, code);
        EditTaskNameCommand editTaskNameToADiffProject = new EditTaskNameCommand(new Name("A"), taskProject, code);
        EditTaskNameCommand editTaskNameToADiffTask = new EditTaskNameCommand(new Name("A"), taskProject, speech);

        // same object -> returns true
        assertTrue(editTaskNameToA.equals(editTaskNameToA));

        // same values -> returns true
        EditTaskNameCommand editTaskNameToACopy = new EditTaskNameCommand(new Name("A"), project, code);
        assertTrue(editTaskNameToA.equals(editTaskNameToACopy));

        // different types -> returns false
        assertFalse(editTaskNameToA.equals(1));

        // null -> returns false
        assertFalse(editTaskNameToA.equals(null));

        // different name, same project and task -> returns false
        assertFalse(editTaskNameToA.equals(editTaskNameToB));
        // different project, same name and task -> returns false
        assertFalse(editTaskNameToA.equals(editTaskNameToADiffProject));
        // different task, same project and name -> returns false
        assertFalse(editTaskNameToA.equals(editTaskNameToADiffTask));


    }

    @Test
    public void toStringMethod() {
        Name newNameA = new Name("A");
        EditTaskNameCommand editTaskNameToA = new EditTaskNameCommand(newNameA, taskProject, code);
        String expectedComplete = EditTaskNameCommand.class.getCanonicalName()
                + "{targetTask=" + code.toString()
                + ", changeTo=A, " + "targetProject=" + taskProject.toString() + "}";
        assertEquals(expectedComplete, editTaskNameToA.toString());

        Name newNameB = new Name("B");
        EditTaskNameCommand editTaskNameToB = new EditTaskNameCommand(newNameB, taskProject, code);
        String expectedIncomplete = EditTaskNameCommand.class.getCanonicalName()
                + "{targetTask=" + code.toString()
                + ", changeTo=B, " + "targetProject=" + taskProject.toString() + "}";
        assertEquals(expectedIncomplete, editTaskNameToB.toString());


    }
}
