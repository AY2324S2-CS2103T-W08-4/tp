package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalProjects.getTypicalProjects;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Planner;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.testutil.ProjectBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddProjectCommandIntegrationTest {

    private Model model;

    @Test
    public void execute_newPerson_success() {
        model = new ModelManager(new Planner(), new UserPrefs());
        Project validProject = new ProjectBuilder().build();

        assertCommandSuccess(new AddProjectCommand(validProject), model,
                String.format(AddProjectCommand.MESSAGE_SUCCESS, Messages.format(validProject)));
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        model = new ModelManager(new Planner(), new UserPrefs());
        for (Project project : getTypicalProjects()) {
            model.addProject(project);
        }
        List<Project> projects = model.getPlanner().getProjectList();
        Project projectInList = projects.get(0);
        assertCommandFailure(new AddProjectCommand(projectInList), model,
                String.format(AddProjectCommand.MESSAGE_DUPLICATE_PROJECT, Messages.format(projectInList)));
    }

}
