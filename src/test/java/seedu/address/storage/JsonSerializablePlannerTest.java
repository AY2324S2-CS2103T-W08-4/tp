package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Planner;
import seedu.address.testutil.TypicalProjects;

public class JsonSerializablePlannerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializablePlannerTest");
    private static final Path TYPICAL_PROJECTS_FILE = TEST_DATA_FOLDER.resolve("typicalProjectsPlanner.json");
    private static final Path INVALID_PROJECT_FILE = TEST_DATA_FOLDER.resolve("invalidProjectPlanner.json");
    private static final Path DUPLICATE_PROJECT_FILE = TEST_DATA_FOLDER.resolve("duplicateProjectPlanner.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializablePlanner dataFromFile = JsonUtil.readJsonFile(TYPICAL_PROJECTS_FILE,
                JsonSerializablePlanner.class).get();
        Planner addressBookFromFile = dataFromFile.toModelType();
        Planner typicalPersonsAddressBook = TypicalProjects.getTypicalPlanner();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePlanner dataFromFile = JsonUtil.readJsonFile(INVALID_PROJECT_FILE,
                JsonSerializablePlanner.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializablePlanner dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PROJECT_FILE,
                JsonSerializablePlanner.class).get();
        assertThrows(IllegalValueException.class, JsonSerializablePlanner.MESSAGE_DUPLICATE_PROJECT,
                dataFromFile::toModelType);
    }

}
