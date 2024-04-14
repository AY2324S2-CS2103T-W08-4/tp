package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.person.Name;

/**
 * Represents a Member of Project
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Member {

    // Identity fields
    private final Name memberName;

    /**
     * Constructs a new member object
     * @param name the member's name
     */
    public Member(String name) {
        requireAllNonNull(name);
        this.memberName = new Name(name);
    }

    /**
     * Get the name of the member
     * @return
     */
    public Name getName() {
        return memberName;
    }

    /**
     * Returns true if both members have the same name.
     * This defines a stronger notion of equality between two members.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Member)) {
            return false;
        }
        Member other = (Member) obj;
        return memberName.equals(other.memberName);
    }

    @Override
    public String toString() {
        return memberName.toString();
    }

}
