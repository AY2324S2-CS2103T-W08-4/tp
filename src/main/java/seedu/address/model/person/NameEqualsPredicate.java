package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.project.Project;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameEqualsPredicate implements Predicate<Project> {
    private final String name;

    public NameEqualsPredicate(String name) {
        this.name = name;
    }

    @Override
    public boolean test(Project project) {
        return project.getName().fullName.equals(name);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameEqualsPredicate)) {
            return false;
        }

        NameEqualsPredicate otherNameContainsKeywordsPredicate = (NameEqualsPredicate) other;
        return name.equals(otherNameContainsKeywordsPredicate.name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("name", name).toString();
    }
}
