package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameEqualsPredicate;
import seedu.address.model.project.Project;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Planner planner;
    private final UserPrefs userPrefs;
    private final FilteredList<Project> filteredProjects;
    private final FilteredList<Project> currentProject;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyPlanner planner, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(planner, userPrefs);

        logger.fine("Initializing with address book: " + planner + " and user prefs " + userPrefs);

        this.planner = new Planner(planner);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredProjects = new FilteredList<>(this.planner.getProjectList());
        if (filteredProjects.size() > 0) {
            currentProject = new FilteredList<>(
                    this.planner.getProjectList(),
                    new NameEqualsPredicate(filteredProjects.get(0).getName().fullName));
        } else {
            currentProject = new FilteredList<>(this.planner.getProjectList());
        }
    }

    public ModelManager() {
        this(new Planner(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getPlannerFilePath() {
        return userPrefs.getPlannerFilePath();
    }

    @Override
    public void setPlannerFilePath(Path plannerFilePath) {
        requireNonNull(plannerFilePath);
        userPrefs.setPlannerFilePath(plannerFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setPlanner(ReadOnlyPlanner planner) {
        this.planner.resetData(planner);
    }

    @Override
    public ReadOnlyPlanner getPlanner() {
        return planner;
    }

    @Override
    public boolean hasProject(Project project) {
        requireNonNull(project);
        return planner.hasProject(project);
    }

    @Override
    public Project findProject(Name name) {
        requireNonNull(name);
        return planner.findProject(name);
    }

    @Override
    public void deleteProject(Project target) {
        planner.removeProject(target);
    }

    @Override
    public void addProject(Project project) {
        planner.addProject(project);
        updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
    }

    @Override
    public void setProject(Project target, Project editedProject) {
        requireAllNonNull(target, editedProject);

        planner.setProject(target, editedProject);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Project> getFilteredProjectList() {
        return filteredProjects;
    }

    @Override
    public void updateFilteredProjectList(Predicate<Project> predicate) {
        requireNonNull(predicate);
        filteredProjects.setPredicate(predicate);
    }

    //=========== Current Project Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Project> getCurrentProject() {
        return currentProject;
    }

    @Override
    public void updateCurrentProject(Predicate<Project> predicate) {
        requireNonNull(predicate);
        currentProject.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return planner.equals(otherModelManager.planner)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredProjects.equals(otherModelManager.filteredProjects)
                && currentProject.equals(otherModelManager.currentProject);
    }

}
