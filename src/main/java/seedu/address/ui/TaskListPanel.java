package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.project.Comment;
import seedu.address.model.project.Task;

/**
 * Panel containing the list of persons.
 */
public class TaskListPanel extends UiPart<Region> {
    private static final String FXML = "TaskListPanel.fxml";

    public final Person currentProject;

    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private Label showingProjectName;
    @FXML
    private Label showingProjectDeadline;
    @FXML
    private ListView<Task> undoneTaskListView;
    @FXML
    private ListView<Task> doneTaskListView;

    @FXML
    private Label team;

    @FXML
    private ListView<Comment> comments;



    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public TaskListPanel(Person currentProject) {
        super(FXML);
        this.currentProject = currentProject;
        showingProjectName.setText("Showing Project: " + currentProject.getName().fullName);
        team.setText("Team Members: " + currentProject.getTeam());

        undoneTaskListView.getItems().clear();
        undoneTaskListView.getItems().addAll(currentProject.getUndoneTasks());

        undoneTaskListView.setCellFactory(param -> new TaskListCell());

        doneTaskListView.getItems().clear();
        doneTaskListView.getItems().addAll(currentProject.getDoneTasks());

        doneTaskListView.setCellFactory(param -> new TaskListCell());

        comments.getItems().clear();
        comments.getItems().addAll(currentProject.getComments());

        comments.setCellFactory(param -> new CommentListCell());
    }

    class TaskListCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);
            if (empty || task == null) {
                setGraphic(null);
            } else {
                setGraphic(new TaskCard(task).getRoot());
            }
        }
    }

    class CommentListCell extends ListCell<Comment> {
        @Override
        protected void updateItem(Comment comment, boolean empty) {
            super.updateItem(comment, empty);
            if (empty || comment == null) {
                setGraphic(null);
            } else {
                setGraphic(new CommentCard(comment).getRoot());
            }
        }
    }

}
