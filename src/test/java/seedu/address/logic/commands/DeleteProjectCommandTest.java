package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.DeleteProjectCommand.MESSAGE_PROJECT_NOT_FOUND;
import static seedu.address.testutil.TypicalProjects.getTypicalProjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Planner;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteProjectCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new Planner(), new UserPrefs());
        for (Project project : getTypicalProjects()) {
            model.addProject(project);
        }
    }

    @Test
    public void execute_validProject_success() {
        Project projectToDelete = model.getFilteredProjectList().get(0);
        DeleteProjectCommand deleteCommand = new DeleteProjectCommand(projectToDelete.getName().fullName);

        String expectedMessage = String.format(DeleteProjectCommand.MESSAGE_DELETE_PROJECT_SUCCESS,
                Messages.format(projectToDelete));

        assertCommandSuccess(deleteCommand, model, expectedMessage);
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
