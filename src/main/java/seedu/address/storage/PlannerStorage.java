package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.Planner;
import seedu.address.model.ReadOnlyPlanner;

/**
 * Represents a storage for {@link Planner}.
 */
public interface PlannerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPlannerFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyPlanner}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyPlanner> readPlanner() throws DataLoadingException;

    /**
     * @see #getPlannerFilePath()
     */
    Optional<ReadOnlyPlanner> readPlanner(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyPlanner} to the storage.
     * @param planner cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePlanner(ReadOnlyPlanner planner) throws IOException;

    /**
     * @see #savePlanner(ReadOnlyPlanner)
     */
    void savePlanner(ReadOnlyPlanner planner, Path filePath) throws IOException;

}
