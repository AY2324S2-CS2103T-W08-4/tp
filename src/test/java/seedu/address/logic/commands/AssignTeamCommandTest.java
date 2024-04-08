package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.AssignTeamCommandParser;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


class AssignTeamCommandTest {

    private Person taskProject = new PersonBuilder().build();
    private List<String> team = new ArrayList<>();
    private AssignTeamCommandParser parser = new AssignTeamCommandParser();

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        team.add("James");
        assertThrows(NullPointerException.class, () -> new AssignTeamCommand(team, null));
        assertThrows(NullPointerException.class, () -> new AssignTeamCommand(null, taskProject));

    }

    @Test
    public void equals() {
        List<String> teamA = new ArrayList<>();
        List<String> teamB = new ArrayList<>();
        teamA.add("James");
        teamB.add("Rachel");
        Person projectB = new Person(new Name("B"));
        AssignTeamCommand deleteJames = new AssignTeamCommand(teamA, taskProject);
        AssignTeamCommand deleteBob = new AssignTeamCommand(teamB, taskProject);
        AssignTeamCommand deleteJamesDiffProject = new AssignTeamCommand(teamA, projectB);


        // same object -> returns true
        assertTrue(deleteJames.equals(deleteJames));

        // same values -> returns true
        AssignTeamCommand deleteJamesCopy = new AssignTeamCommand(teamA, taskProject);
        assertTrue(deleteJames.equals(deleteJamesCopy));

        // different types -> returns false
        assertFalse(deleteJames.equals(1));

        // null -> returns false
        assertFalse(deleteJames.equals(null));

        // different comment -> returns false
        assertFalse(deleteJames.equals(deleteBob));
        assertFalse(deleteJames.equals(deleteJamesDiffProject));

    }
    @Test
    public void toStringMethod() {
        team.add("James");
        AssignTeamCommand addCommentCommand = new AssignTeamCommand(team, taskProject);
        String expected = AssignTeamCommand.class.getCanonicalName() + "{set Team=" + team + "}";
        assertEquals(expected, addCommentCommand.toString());
    }

}
