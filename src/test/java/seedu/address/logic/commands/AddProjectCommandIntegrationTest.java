package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalProjects.getTypicalPlanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.testutil.ProjectBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddProjectCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPlanner(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Project validProject = new ProjectBuilder().build();
        Command addProject = new AddProjectCommand(validProject);
        Model expectedModel = new ModelManager(model.getPlanner(), new UserPrefs());

        try {
            CommandResult result = addProject.execute(expectedModel);
        } catch (Exception e) {
            throw new Error("Issue with adding project in duplicate project test");
        }

        assertCommandSuccess(new AddProjectCommand(validProject), model,
                String.format(AddProjectCommand.MESSAGE_SUCCESS, Messages.format(validProject)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Project validProject = new ProjectBuilder().build();

        Command addProject = new AddProjectCommand(validProject);
        Model newModel = new ModelManager(model.getPlanner(), new UserPrefs());
        try {
            CommandResult result = addProject.execute(newModel);
        } catch (Exception e) {
            throw new Error("Issue with adding project in duplicate project test");
        }

        assertCommandFailure(new AddProjectCommand(validProject), newModel,
                String.format(AddProjectCommand.MESSAGE_DUPLICATE_PROJECT, Messages.format(validProject)));
    }

}
