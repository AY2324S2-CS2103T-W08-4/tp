package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalProjects.getTypicalProjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Planner;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.project.Project;
import seedu.address.testutil.ProjectBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new Planner(), new UserPrefs());
        for (Project project : getTypicalProjects()) {
            model.addProject(project);
        }
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Project editedPerson = new ProjectBuilder().build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, editedPerson);

        String expectedMessage = String.format(
            EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
            Messages.format(editedPerson));

        assertCommandSuccess(editCommand, model, expectedMessage);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredProjectList().size());
        Project lastPerson = model.getFilteredProjectList().get(indexLastPerson.getZeroBased());

        ProjectBuilder personInList = new ProjectBuilder(lastPerson);
        Project editedPerson = personInList.withName(VALID_NAME_BOB).build();

        Project descriptor = new Project(new Name(VALID_NAME_BOB));

        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        assertCommandSuccess(editCommand, model, expectedMessage);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        Project editedPerson = model.getFilteredProjectList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, editedPerson);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));
        assertCommandSuccess(editCommand, model, expectedMessage);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Project personInFilteredList = model.getFilteredProjectList().get(INDEX_FIRST_PERSON.getZeroBased());
        Project editedPerson = new ProjectBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
                new Project(new Name(VALID_NAME_BOB)));

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson));

        assertCommandSuccess(editCommand, model, expectedMessage);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Project firstPerson = model.getFilteredProjectList().get(INDEX_FIRST_PERSON.getZeroBased());
        Project descriptor = new ProjectBuilder(firstPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertEquals(0, 0);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Project personInList = model.getPlanner().getProjectList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, personInList);

        // Filtering not yet implemented
        assertEquals(0, 0);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        Project descriptor = new Project(new Name(VALID_NAME_BOB));
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, new Project(new Name(VALID_NAME_AMY)));

        // same values -> returns true
        Project copyDescriptor = new Project(new Name(VALID_NAME_AMY));
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON,
                new Project(new Name(VALID_NAME_AMY)))));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        Project project = new Project(new Name(VALID_NAME_AMY));
        EditCommand editCommand = new EditCommand(index, project);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editPersonDescriptor="
                + project + "}";
        assertEquals(expected, editCommand.toString());
    }

}
