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
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.project.Project;
import seedu.address.testutil.ProjectBuilder;

public class PlannerTest {

    private final Planner planner = new Planner();

    @Test
    public void constructor() {
        planner.resetData(new Planner());
        assertEquals(Collections.emptyList(), planner.getProjectList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        planner.resetData(new Planner());
        assertThrows(NullPointerException.class, () -> planner.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        planner.resetData(new Planner());
        Planner newData = getTypicalPlanner();
        planner.resetData(newData);
        assertEquals(newData, planner);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        planner.resetData(new Planner());
        // Two persons with the same identity fields
        Project editedAlice = new ProjectBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Project> newPersons = Arrays.asList(ALICE, editedAlice);
        PlannerStub newData = new PlannerStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> planner.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        planner.resetData(new Planner());
        assertThrows(NullPointerException.class, () -> planner.hasProject(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        planner.resetData(new Planner());
        assertFalse(planner.hasProject(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        planner.resetData(new Planner());
        planner.addProject(ALICE);
        assertTrue(planner.hasProject(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        planner.resetData(new Planner());
        planner.addProject(ALICE);
        Project editedAlice = new ProjectBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(planner.hasProject(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        planner.resetData(new Planner());
        assertThrows(UnsupportedOperationException.class, () -> planner.getProjectList().remove(0));
    }

    @Test
    public void toStringMethod() {
        planner.resetData(new Planner());
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
