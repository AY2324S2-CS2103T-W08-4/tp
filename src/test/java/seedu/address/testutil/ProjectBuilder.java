package seedu.address.testutil;

import seedu.address.model.person.Name;
import seedu.address.model.project.Project;

/**
 * A utility class to help with building Person objects.
 */
public class ProjectBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    // private Phone phone;
    // private Email email;
    // private Address address;
    // private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public ProjectBuilder() {
        name = new Name(DEFAULT_NAME);
        // phone = new Phone(DEFAULT_PHONE);
        // email = new Email(DEFAULT_EMAIL);
        // address = new Address(DEFAULT_ADDRESS);
        // tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public ProjectBuilder(Project projectToCopy) {
        name = projectToCopy.getName();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public ProjectBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public ProjectBuilder withTags(String ... tags) {
        // this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public ProjectBuilder withAddress(String address) {
        // this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public ProjectBuilder withPhone(String phone) {
        // this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public ProjectBuilder withEmail(String email) {
        // this.email = new Email(email);
        return this;
    }

    public Project build() {
        return new Project(name.toString());
    }

}
