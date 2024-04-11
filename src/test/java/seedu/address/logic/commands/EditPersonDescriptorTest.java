package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditProjectDescriptor;
import seedu.address.model.person.Name;
import seedu.address.model.project.Project;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        Project descriptorWithSameValues = new Project(new Name(VALID_NAME_AMY));
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

    }

    @Test
    public void toStringMethod() {
        EditProjectDescriptor editPersonDescriptor = new EditProjectDescriptor();
        String expected = EditProjectDescriptor.class.getCanonicalName() + "{name=null}";
        assertEquals(expected, editPersonDescriptor.toString());
    }
}
