package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;
import seedu.address.model.project.Project;
import seedu.address.testutil.ProjectBuilder;

class SetProjectCategoryCommandTest {
    private Project taskProject = new ProjectBuilder().build();

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetProjectCategoryCommand(null, taskProject));
    }
    @Test
    public void equals() {
        Project project = new Project(new Name("project"));

        SetProjectCategoryCommand setProjectCategoryA = new SetProjectCategoryCommand("A", project);
        SetProjectCategoryCommand setProjectCategoryB = new SetProjectCategoryCommand("B", project);

        // same object -> returns true
        assertTrue(setProjectCategoryA.equals(setProjectCategoryA));

        // same values -> returns true
        SetProjectCategoryCommand setProjectCategoryACopy = new SetProjectCategoryCommand("A", project);
        assertTrue(setProjectCategoryA.equals(setProjectCategoryACopy));

        // different types -> returns false
        assertFalse(setProjectCategoryA.equals(1));

        // null -> returns false
        assertFalse(setProjectCategoryA.equals(null));

        // different person -> returns false
        assertFalse(setProjectCategoryA.equals(setProjectCategoryB));
    }

    @Test
    public void toStringMethod() {
        String categoryA = "A";
        SetProjectCategoryCommand setProjectCategoryA = new SetProjectCategoryCommand("A", taskProject);
        String expectedComplete = SetProjectCategoryCommand.class.getCanonicalName()
                + "{setCategory=" + categoryA + "}";
        assertEquals(expectedComplete, setProjectCategoryA.toString());

        String categoryB = "B";
        SetProjectCategoryCommand setProjectCategoryB = new SetProjectCategoryCommand("B", taskProject);
        String expectedIncomplete = SetProjectCategoryCommand.class.getCanonicalName()
                + "{setCategory=" + categoryB + "}";
        assertEquals(expectedIncomplete, setProjectCategoryB.toString());

    }

}
