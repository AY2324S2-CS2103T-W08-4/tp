package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProjects.ALICE;
import static seedu.address.testutil.TypicalProjects.HOON;
import static seedu.address.testutil.TypicalProjects.IDA;
import static seedu.address.testutil.TypicalProjects.getTypicalPlanner;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.Planner;
import seedu.address.model.ReadOnlyPlanner;

public class JsonPlannerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonPlannerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyPlanner> readAddressBook(String filePath) throws Exception {
        return new JsonPlannerStorage(Paths.get(filePath)).readPlanner(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readAddressBook("notJsonFormatPlanner.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readAddressBook("invalidProjectPlanner.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readAddressBook("invalidAndValidProjectPlanner.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        Planner original = getTypicalPlanner();
        JsonPlannerStorage jsonAddressBookStorage = new JsonPlannerStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.savePlanner(original, filePath);
        ReadOnlyPlanner readBack = jsonAddressBookStorage.readPlanner(filePath).get();
        assertEquals(original, new Planner(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addProject(HOON);
        original.removeProject(ALICE);
        jsonAddressBookStorage.savePlanner(original, filePath);
        readBack = jsonAddressBookStorage.readPlanner(filePath).get();
        assertEquals(original, new Planner(readBack));

        // Save and read without specifying file path
        original.addProject(IDA);
        jsonAddressBookStorage.savePlanner(original); // file path not specified
        readBack = jsonAddressBookStorage.readPlanner().get(); // file path not specified
        assertEquals(original, new Planner(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyPlanner addressBook, String filePath) {
        try {
            new JsonPlannerStorage(Paths.get(filePath))
                    .savePlanner(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new Planner(), null));
    }
}
