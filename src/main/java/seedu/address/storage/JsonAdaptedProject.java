package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.project.Comment;
import seedu.address.model.project.Member;
import seedu.address.model.project.Project;
import seedu.address.model.project.Task;


/**
 * Jackson-friendly version of {@link Project}.
 */
class JsonAdaptedProject {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Project's %s field is missing!";

    private final String name;
    private final String deadline;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final String category;
    private final String projectStatus;

    private final List<JsonAdaptedMember> team = new ArrayList<>();
    private final List<JsonAdaptedTask> doneTaskList = new ArrayList<>();
    private final List<JsonAdaptedTask> undoneTaskList = new ArrayList<>();
    private final List<JsonAdaptedComment> commentList = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedProject} with the given project details.
     */
    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("name") String name, @JsonProperty("deadline") String deadline,
                              @JsonProperty("category") String category,
                              @JsonProperty("projectStatus") String projectStatus,
                              @JsonProperty("team") List<JsonAdaptedMember> team,
                              @JsonProperty("doneTaskList") List<JsonAdaptedTask> doneTaskList,
                              @JsonProperty("undoneTaskList") List<JsonAdaptedTask> undoneTaskList,
                              @JsonProperty("commentList") List<JsonAdaptedComment> commentList) {
        this.name = name;
        this.deadline = deadline;
        this.category = category;
        this.projectStatus = projectStatus;
        if (team != null) {
            this.team.addAll(team);
        }
        if (doneTaskList != null) {
            this.doneTaskList.addAll(doneTaskList);
        }
        if (undoneTaskList != null) {
            this.undoneTaskList.addAll(undoneTaskList);
        }

        if (commentList != null) {
            this.commentList.addAll(commentList);
        }

    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedProject(Project source) {
        name = source.getName().fullName;
        deadline = source.getDeadlineString();
        category = source.getCategory();
        projectStatus = source.getStatus();
        team.addAll(source.getTeamList().stream()
                .map(JsonAdaptedMember::new)
                .collect(Collectors.toList()));
        doneTaskList.addAll(source.getDoneTasks().stream()
                .map(JsonAdaptedTask::new)
                .collect(Collectors.toList()));
        undoneTaskList.addAll(source.getUndoneTasks().stream()
                .map(JsonAdaptedTask::new)
                .collect(Collectors.toList()));

        commentList.addAll(source.getComments().stream()
                .map(JsonAdaptedComment::new)
                .collect(Collectors.toList()));

    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Project toModelType() throws IllegalValueException {
        final List<Member> members = new ArrayList<>();
        for (JsonAdaptedMember member : team) {
            members.add(member.toModelType());
        }

        final List<Task> tasks = new ArrayList<>();
        for (JsonAdaptedTask task : doneTaskList) {
            Task current = task.toModelType();
            current.setComplete();
            tasks.add(current);
        }
        for (JsonAdaptedTask task : undoneTaskList) {
            tasks.add(task.toModelType());

        }

        final List<Comment> comments = new ArrayList<>();
        for (JsonAdaptedComment comment : commentList) {
            comments.add(comment.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        Project toReturn = new Project(modelName);
        if (deadline != null && deadline.length() != 0) {
            toReturn.setDeadline(deadline);
        }
        if (category != null && category.length() != 0) {
            toReturn.setCategory(category);
        }
        if (projectStatus != null && projectStatus.length() != 0) {
            if (projectStatus.equals("complete")) {
                toReturn.setComplete();
            } else {
                toReturn.setIncomplete();
            }
        }
        toReturn.assignTeam(members);
        toReturn.setTaskList(tasks);

        toReturn.setCommentList(comments);


        return toReturn;
    }

}
