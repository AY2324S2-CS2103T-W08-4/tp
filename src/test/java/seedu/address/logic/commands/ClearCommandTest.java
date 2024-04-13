package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalProjects.getTypicalPlanner;
import static seedu.address.testutil.TypicalProjects.getTypicalProjects;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        for (Project project : getTypicalProjects()) {
            model.addProject(project);
        }
        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalPlanner(), new UserPrefs());
        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS);
    }

}
