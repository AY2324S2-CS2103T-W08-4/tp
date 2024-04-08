package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;
import seedu.address.model.project.Member;
import seedu.address.model.project.Project;
import seedu.address.testutil.ProjectBuilder;

class DeletePersonCommandTest {
    private Project taskProject = new ProjectBuilder().build();
    private Member member = new Member("James");

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeletePersonCommand(member, null));
        assertThrows(NullPointerException.class, () -> new DeletePersonCommand(null, taskProject));
    }

    @Test
    public void equals() {
        Member memberBob = new Member("Bob");
        Project projectB = new Project(new Name("B"));
        DeletePersonCommand deleteJames = new DeletePersonCommand(member, taskProject);
        DeletePersonCommand deleteBob = new DeletePersonCommand(memberBob, taskProject);
        DeletePersonCommand deleteJamesDiffProject = new DeletePersonCommand(member, projectB);


        // same object -> returns true
        assertTrue(deleteJames.equals(deleteJames));

        // same values -> returns true
        DeletePersonCommand deleteJamesCopy = new DeletePersonCommand(member, taskProject);
        assertTrue(deleteJames.equals(deleteJamesCopy));

        // different types -> returns false
        assertFalse(deleteJames.equals(1));

        // null -> returns false
        assertFalse(deleteJames.equals(null));

        // different comment -> returns false
        assertFalse(deleteJames.equals(deleteBob));
        assertFalse(deleteJames.equals(deleteJamesDiffProject));

    }
    @Test
    public void toStringMethod() {
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(member, taskProject);
        String expected = DeletePersonCommand.class.getCanonicalName()
                + "{toDelete=" + member + "}";
        assertEquals(expected, deletePersonCommand.toString());
    }

}
