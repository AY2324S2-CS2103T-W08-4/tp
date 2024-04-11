package seedu.address.testutil;

import seedu.address.model.person.Name;
import seedu.address.model.project.Project;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditProjectDescriptorBuilder {

    private Project descriptor;

    public EditProjectDescriptorBuilder(Project descriptor) {
        this.descriptor = descriptor;
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditProjectDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

}
