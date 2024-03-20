package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProjects.ALICE;
import static seedu.address.testutil.TypicalProjects.getTypicalPlanner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.project.Project;
import seedu.address.testutil.ProjectBuilder;

public class AddressBookTest {

    private final Planner planner = new Planner();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), planner.getProjectList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> planner.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        Planner newData = getTypicalPlanner();
        planner.resetData(newData);
        assertEquals(newData, planner);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Project editedAlice = new ProjectBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Project> newPersons = Arrays.asList(ALICE, editedAlice);
        PlannerStub newData = new PlannerStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> planner.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> planner.hasProject(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(planner.hasProject(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        planner.addProject(ALICE);
        assertTrue(planner.hasProject(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        planner.addProject(ALICE);
        Project editedAlice = new ProjectBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(planner.hasProject(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> planner.getProjectList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = Planner.class.getCanonicalName() + "{projects=" + planner.getProjectList() + "}";
        assertEquals(expected, planner.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class PlannerStub implements ReadOnlyPlanner {
        private final ObservableList<Project> projects = FXCollections.observableArrayList();

        PlannerStub(Collection<Project> projects) {
            this.projects.setAll(projects);
        }

        @Override
        public ObservableList<Project> getProjectList() {
            return projects;
        }
    }

}
