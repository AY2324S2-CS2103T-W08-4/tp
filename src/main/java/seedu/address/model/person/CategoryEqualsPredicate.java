package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class CategoryEqualsPredicate implements Predicate<Person> {
    private final String category;

    public CategoryEqualsPredicate(String category) {
        this.category = category;
    }

    @Override
    public boolean test(Person person) {
        return person.getCategory().equals(category);
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

        CategoryEqualsPredicate otherCategoryEqualsPredicate = (CategoryEqualsPredicate) other;
        return category.equals(otherCategoryEqualsPredicate.category);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("category", category).toString();
    }
}
