package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;
import seedu.address.model.project.Project;
import seedu.address.testutil.ProjectBuilder;



class EditProjectNameCommandTest {
    private Project taskProject = new ProjectBuilder().build();

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditProjectNameCommand(null, taskProject));
        assertThrows(NullPointerException.class, () -> new EditProjectNameCommand(new Name("A"), null));
    }

    @Test
    public void equals() {
        Project project = new Project(new Name("project"));

        EditProjectNameCommand editProjectNameToA = new EditProjectNameCommand(new Name("A"), project);
        EditProjectNameCommand editProjectNameToB = new EditProjectNameCommand(new Name("B"), project);
        EditProjectNameCommand editProjectNameToADiffProject = new EditProjectNameCommand(new Name("A"), taskProject);


        // same object -> returns true
        assertTrue(editProjectNameToA.equals(editProjectNameToA));

        // same values -> returns true
        EditProjectNameCommand editProjectNameToACopy = new EditProjectNameCommand(new Name("A"), project);
        assertTrue(editProjectNameToA.equals(editProjectNameToACopy));

        // different types -> returns false
        assertFalse(editProjectNameToA.equals(1));

        // null -> returns false
        assertFalse(editProjectNameToA.equals(null));

        // different name, same project -> returns false
        assertFalse(editProjectNameToA.equals(editProjectNameToB));
        // different project, same name -> returns false
        assertFalse(editProjectNameToA.equals(editProjectNameToADiffProject));
    }

    @Test
    public void toStringMethod() {
        Name newNameA = new Name("A");
        EditProjectNameCommand editProjectNameToA = new EditProjectNameCommand(newNameA, taskProject);
        String expectedComplete = EditProjectNameCommand.class.getCanonicalName()
                + "{changeTo=A, " + "targetProject=" + taskProject.toString() + "}";
        assertEquals(expectedComplete, editProjectNameToA.toString());

        Name newNameB = new Name("B");
        EditProjectNameCommand editProjectNameToB = new EditProjectNameCommand(newNameB, taskProject);
        String expectedIncomplete = EditProjectNameCommand.class.getCanonicalName()
                + "{changeTo=B, " + "targetProject=" + taskProject.toString() + "}";
        assertEquals(expectedIncomplete, editProjectNameToB.toString());


    }
}
