package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalProjects.getTypicalProjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Planner;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.model.project.Task;
import seedu.address.testutil.ProjectBuilder;

class DeleteTaskCommandTest {
    private Model model;

    private Task tempTask1 = new Task("rehearse");
    private Task tempTask2 = new Task("Write script");

    private Project presentation = new ProjectBuilder().withName("Presentation").build();

    private Project codingProject = new ProjectBuilder().withName("Coding Project").build();

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new Planner(), new UserPrefs());
        for (Project project : getTypicalProjects()) {
            model.addProject(project);
        }
    }

    @Test
    public void equals() {
        DeleteTaskCommand deleteFirstCommand = new DeleteTaskCommand(tempTask1, presentation);
        DeleteTaskCommand deleteSecondCommand = new DeleteTaskCommand(tempTask1, codingProject);
        DeleteTaskCommand deleteThirdCommand = new DeleteTaskCommand(tempTask2, presentation);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteTaskCommand deleteFirstCommandCopy = new DeleteTaskCommand(tempTask1, presentation);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different project, same task -> returns false (FAILED!)
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // same project, different task -> returns false
        assertFalse(deleteFirstCommand.equals(deleteThirdCommand));

        // different project, different task -> returns false
        assertFalse(deleteSecondCommand.equals(deleteThirdCommand));

    }
    @Test
    public void toStringMethod() {
        String targetName = "rehearse";
        DeleteTaskCommand deleteCommand = new DeleteTaskCommand(tempTask1, presentation);
        String expected = DeleteTaskCommand.class.getCanonicalName() + "{toDelete=" + tempTask1 + "}";
        assertEquals(expected, deleteCommand.toString());
    }
}
