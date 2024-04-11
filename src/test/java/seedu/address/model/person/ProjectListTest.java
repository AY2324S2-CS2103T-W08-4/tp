package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProjects.ALICE;
import static seedu.address.testutil.TypicalProjects.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectList;
import seedu.address.testutil.ProjectBuilder;

public class ProjectListTest {

    private ProjectList projectList = new ProjectList();

    @BeforeEach
    public void setUp() {
        projectList = new ProjectList();
    }

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> projectList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(projectList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        projectList.add(ALICE);
        assertTrue(projectList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        projectList.add(ALICE);
        Project editedAlice = new ProjectBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(projectList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> projectList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        projectList.add(ALICE);
        assertThrows(DuplicatePersonException.class, () -> projectList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> projectList.setProject(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> projectList.setProject(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> projectList.setProject(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        projectList.add(ALICE);
        projectList.setProject(ALICE, ALICE);
        ProjectList expectedProjectList = new ProjectList();
        expectedProjectList.add(ALICE);
        assertEquals(expectedProjectList, projectList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        projectList.add(ALICE);
        Project editedAlice = new ProjectBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        projectList.setProject(ALICE, editedAlice);
        ProjectList expectedProjectList = new ProjectList();
        expectedProjectList.add(editedAlice);
        assertEquals(expectedProjectList, projectList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        projectList.add(ALICE);
        projectList.setProject(ALICE, BOB);
        ProjectList expectedProjectList = new ProjectList();
        expectedProjectList.add(BOB);
        assertEquals(expectedProjectList, projectList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        projectList.add(ALICE);
        projectList.add(BOB);
        assertThrows(DuplicatePersonException.class, () -> projectList.setProject(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> projectList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> projectList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        projectList.add(ALICE);
        projectList.remove(ALICE);
        ProjectList expectedProjectList = new ProjectList();
        assertEquals(expectedProjectList, projectList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> projectList.setProjects((ProjectList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        projectList.add(ALICE);
        ProjectList expectedProjectList = new ProjectList();
        expectedProjectList.add(BOB);
        projectList.setProjects(expectedProjectList);
        assertEquals(expectedProjectList, expectedProjectList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> projectList.setProjects((List<Project>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        projectList.add(ALICE);
        List<Project> personList = Collections.singletonList(BOB);
        projectList.setProjects(personList);
        ProjectList expectedProjectList = new ProjectList();
        expectedProjectList.add(BOB);
        assertEquals(expectedProjectList, projectList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Project> listWithDuplicateProjects = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePersonException.class, () -> projectList.setProjects(listWithDuplicateProjects));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> projectList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(projectList.asUnmodifiableObservableList().toString(), projectList.toString());
    }
}
