package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalProjects.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.model.project.Project;
import seedu.address.testutil.ProjectBuilder;

public class PersonTest {


    @Test
    public void isSameProject() {
        // same object -> returns true
        assertTrue(ALICE.isSameProject(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameProject(null));

        // same name, all other attributes different -> returns true
        Project editedAlice = new ProjectBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameProject(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new ProjectBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameProject(editedAlice));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedAlice = new ProjectBuilder(ALICE).withName(nameWithTrailingSpaces).build();
        assertFalse(ALICE.isSameProject(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Project aliceCopy = new ProjectBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different name -> returns false
        Project editedAlice = new ProjectBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }

}
