package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.DeleteProjectCommand.MESSAGE_PROJECT_NOT_FOUND;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalProjects.getTypicalPlanner;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.testutil.ProjectBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteProjectCommandTest {

    private Model model = new ModelManager(getTypicalPlanner(), new UserPrefs());

    @Test
    public void execute_validProject_success() {
        Project validProject = new ProjectBuilder().build();
        try {
            Command addProject = new AddProjectCommand(validProject);
            CommandResult result = addProject.execute(model);
        } catch (Exception e) {
            throw new Error("Issue with adding project in delete project test");
        }
        Project projectToDelete = model.getFilteredProjectList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteProjectCommand deleteCommand = new DeleteProjectCommand(projectToDelete.getName().fullName);

        String expectedMessage = String.format(DeleteProjectCommand.MESSAGE_DELETE_PROJECT_SUCCESS,
                Messages.format(projectToDelete));

        assertCommandSuccess(deleteCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_invalidProject_throwsCommandException() {
        DeleteProjectCommand deleteCommand = new DeleteProjectCommand("thereisnoproject withthisname");

        assertCommandFailure(deleteCommand, model, String.format(MESSAGE_PROJECT_NOT_FOUND,
                "thereisnoproject withthisname"));
    }

    @Test
    public void equals() {
        DeleteProjectCommand deleteFirstCommand = new DeleteProjectCommand("FirstProject");
        DeleteProjectCommand deleteSecondCommand = new DeleteProjectCommand("SecondProject");

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteProjectCommand deleteFirstCommandCopy = new DeleteProjectCommand("FirstProject");
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        String targetName = "Dummy Project";
        DeleteProjectCommand deleteCommand = new DeleteProjectCommand(targetName);
        String expected = DeleteProjectCommand.class.getCanonicalName() + "{targetName=" + targetName + "}";
        assertEquals(expected, deleteCommand.toString());
    }
}
