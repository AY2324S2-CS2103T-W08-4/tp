package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Planner;
import seedu.address.model.project.Project;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalProjects {

    public static final Project ALICE = new ProjectBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();

    public static final Project BOB = new ProjectBuilder().withName("Bob Choo")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("bob@example.com")
            .withPhone("94351253")
            .withTags("friends").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalProjects() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static Planner getTypicalPlanner() {
        Planner ab = new Planner();
        for (Project project : getTypicalProjects()) {
            ab.addProject(project);
        }
        return ab;
    }

    public static List<Project> getTypicalProjects() {
        return new ArrayList<>(Arrays.asList(ALICE));
    }
}
