package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.project.Comment;
import seedu.address.model.project.Member;

/**
 * Container for comments written within projects
 */
public class CommentCard extends UiPart<Region> {
    private static final String FXML = "CommentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private final Member member;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;

    @FXML
    private Label comment;

    /**
     * Creates a {@code CommentCard} with the given {@code Comment}.
     */
    public CommentCard(Comment commentAdd) {
        super(FXML);
        this.member = commentAdd.getMember();
        name.setText(member.getName().fullName);
        comment.setText(commentAdd.toString());
    }
}
