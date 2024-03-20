package seedu.address.testutil;

import seedu.address.model.Planner;
import seedu.address.model.project.Project;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class PlannerBuilder {

    private Planner planner;

    public PlannerBuilder() {
        this.planner = new Planner();
    }

    public PlannerBuilder(Planner addressBook) {
        this.planner = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public PlannerBuilder withProject(Project project) {
        planner.addProject(project);
        return this;
    }

    public Planner build() {
        return planner;
    }
}
