package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Planner;
import seedu.address.model.ReadOnlyPlanner;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Project[] getSampleProjects() {
        return new Project[] {
            new Project(new Name("CS2101 Presentation")),
            new Project(new Name("Duke chatbot")),
            new Project(new Name("IS1128 project")),
            new Project(new Name("CS2103T Ab3")),
            new Project(new Name("Coding project"))
        };
    }

    public static ReadOnlyPlanner getSamplePlanner() {
        Planner sampleAb = new Planner();
        for (Project sampleProject : getSampleProjects()) {
            sampleAb.addProject(sampleProject);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
