package seedu.address.model.project;

/**
 * Represents a comment made by a member within a project.
 * Each comment consists of a textual content and the member who made the comment.
 */
public class Comment {
    private String comment;
    private Member member;

    /**
     * Constructs a new Comment object.
     *
     * @param comment the content of the comment in string format
     * @param member the member who made the comment
     */
    public Comment(String comment, Member member) {
        this.comment = comment;
        this.member = member;
    }

    /**
     * Retrieves the member who made this comment.
     *
     * @return the member who made the comment
     */
    public Member getMember() {
        return member;
    }

    /**
     * Checks if this comment is equal to another object.
     * Two comments are considered equal if they have the same content.
     *
     * @param obj the object to compare with this comment
     * @return true if the comments have the same content, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) obj;
        return comment.equals(other.comment);
    }

    /**
     * Returns the textual content of the comment.
     *
     * @return the comment content in string format
     */
    @Override
    public String toString() {
        return comment;
    }
}
