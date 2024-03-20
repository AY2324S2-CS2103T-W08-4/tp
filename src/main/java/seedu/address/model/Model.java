package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Name;
import seedu.address.model.project.Project;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Project> PREDICATE_SHOW_ALL_PROJECTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getPlannerFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setPlannerFilePath(Path plannerFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setPlanner(ReadOnlyPlanner addressBook);

    /** Returns the planner */
    ReadOnlyPlanner getPlanner();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasProject(Project project);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteProject(Project target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addProject(Project project);

    Project findProject(Name name);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setProject(Project target, Project editedProject);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Project> getFilteredProjectList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredProjectList(Predicate<Project> predicate);
}
