package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalProjects.getTypicalProjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Planner;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListProjectCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new Planner(), new UserPrefs());
        for (Project project : getTypicalProjects()) {
            model.addProject(project);
        }
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListProjectCommand(), model, ListProjectCommand.MESSAGE_SUCCESS);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListProjectCommand(), model, ListProjectCommand.MESSAGE_SUCCESS);
    }
}
