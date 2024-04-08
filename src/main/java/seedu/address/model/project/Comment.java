package seedu.address.model.project;

/**
 * Comments within projects
 * comments have a string field and a member field
 */
public class Comment {
    private String comment;
    private Member member;

    /**
     * constructor for comment object
     * @param comment comment in string format
     * @param member person who made the comment
     */
    public Comment(String comment, Member member) {
        this.comment = comment;
        this.member = member;
    }

    /**
     * @return project member who made the comment
     */
    public Member getMember() {
        return member;
    }

    /**
     * Returns true if both comments have the same content and author.
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
     * @return comment in string format
     */
    @Override
    public String toString() {
        return comment;
    }
}
