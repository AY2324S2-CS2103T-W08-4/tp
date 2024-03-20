package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProjects.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Planner;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyPlanner;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;
import seedu.address.testutil.ProjectBuilder;

public class AddProjectCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddProjectCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Project validProject = new ProjectBuilder().build();

        CommandResult commandResult = new AddProjectCommand(validProject).execute(modelStub);

        assertEquals(String.format(AddProjectCommand.MESSAGE_SUCCESS, Messages.format(validProject)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validProject), modelStub.projectsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Project validProject = new ProjectBuilder().build();
        AddProjectCommand addProjectCommand = new AddProjectCommand(validProject);
        ModelStub modelStub = new ModelStubWithPerson(validProject);

        assertThrows(CommandException.class, String.format(AddProjectCommand.MESSAGE_DUPLICATE_PROJECT,
                Messages.format(validProject)), () ->
                addProjectCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Project alice = new ProjectBuilder().withName("Alice").build();
        Project bob = new ProjectBuilder().withName("Bob").build();
        AddProjectCommand addAliceCommand = new AddProjectCommand(alice);
        AddProjectCommand addBobCommand = new AddProjectCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddProjectCommand addAliceCommandCopy = new AddProjectCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddProjectCommand addProjectCommand = new AddProjectCommand(ALICE);
        String expected = AddProjectCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addProjectCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getPlannerFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPlannerFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addProject(Project project) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Project findProject(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPlanner(ReadOnlyPlanner newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyPlanner getPlanner() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProject(Project person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteProject(Project target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProject(Project target, Project editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Project> getFilteredProjectList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredProjectList(Predicate<Project> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Project project;

        ModelStubWithPerson(Project project) {
            requireNonNull(project);
            this.project = project;
        }

        @Override
        public boolean hasProject(Project project) {
            requireNonNull(project);
            return this.project.isSameProject(project);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Project> projectsAdded = new ArrayList<>();

        @Override
        public boolean hasProject(Project project) {
            requireNonNull(project);
            return projectsAdded.stream().anyMatch(project::isSameProject);
        }

        @Override
        public void addProject(Project project) {
            requireNonNull(project);
            projectsAdded.add(project);
        }

        @Override
        public ReadOnlyPlanner getPlanner() {
            return new Planner();
        }
    }

}
