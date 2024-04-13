package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.project.Project;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class ProjectCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Project project;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label projectStatus;

    @FXML
    private Label deadlineDate;
    @FXML
    private Label category;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ProjectCard(Project project, int displayedIndex) {
        super(FXML);
        this.project = project;

        id.setText(displayedIndex + ". ");
        name.setText(project.getName().fullName);
        deadlineDate.setText("Due: " + project.getDeadlineString());
        projectStatus.setText(project.isCompleted() ? "complete" : "");
        category.setText(project.getCategory());
    }
}
