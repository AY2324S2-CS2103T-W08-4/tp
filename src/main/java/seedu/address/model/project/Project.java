package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Name;

/**
 * Represents a Task of Project
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Project {

    // Identity fields
    private Name name;

    private final List<Task> taskList;

    private LocalDate deadlineDate;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    private List<Member> team = new ArrayList<>();
    private String status;
    private String category;

    private List<Comment> comments = new ArrayList<>();

    /**
     * Constructs a new project object
     * @param name the project name
     */
    public Project(Name name) {
        requireAllNonNull(name);
        this.name = name;
        List<Task> taskList = new ArrayList<>();
        this.taskList = taskList;
        status = "incomplete";
    }

    /**
     * Constructs a new project object
     * @param name the project name
     * @param tasks the list of tasks within the project
     */
    private Project(Name name, List<Task> tasks) {
        this.taskList = tasks;
        this.name = name;
    }

    /**
     * @param otherProject project to be compared to
     * @return boolean value (true/false) whether the two projects are the same
     */
    public boolean isSameProject(Project otherProject) {
        if (otherProject == this) {
            return true;
        }

        return otherProject != null
                && otherProject.getName().equals(getName());
    }

    /**
     * adds a task to the specified project
     * @param task task to be added to the project list
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList.addAll(taskList);
    }


    public void setCommentList(List<Comment> commentList) {
        this.comments.addAll(commentList);
    }

    /**
     * removes a project in the specified project
     * @param task task to be removed from the project list
     */
    public void removeTask(Task task) {
        int i = 0;
        for (Task t : taskList) {
            if (t.equals(task)) {
                taskList.remove(i);
                break;
            }
            i += 1;
        }
    }

    /**
     * Returns true if the Project has a task with the same name as the specified task
     */
    public boolean hasTask(Task task) {
        for (Task t : taskList) {
            System.out.println(task.getName().fullName);
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param taskName name to be matched with the tasks listed in my project
     * @return task in the project with the matching taskName
     */
    public Task findTask(Name taskName) {
        Optional<Task> foundTask = taskList.stream()
                .filter(task -> task.getName().toString().equals(taskName.toString()))
                .findFirst();
        return foundTask.get();
    }

    /**
     * Adds a Person to the team
     * @param member the person added to the team
     */
    public void addMember(Member member) {
        team.add(member);
    }

    /**
     * Removes a Person from the team
     * @param member the person removed from the team
     */
    public void removeMember(Member member) {
        int i = 0;
        for (Member m : team) {
            if (m.equals(member)) {
                team.remove(i);
                break;
            }
            i += 1;
        }
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void assignTeam(List<Member> team) {
        this.team = team;
    }

    public void setComment(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * Sets the task status as complete
     */
    public void setComplete() {
        this.status = "complete";
    }

    /**
     * Sets the task status as incomplete
     */
    public void setIncomplete() {
        this.status = "incomplete";
    }

    public boolean isCompleted() {
        return status.equals("complete");
    }

    public List<Task> getDoneTasks() {
        ArrayList<Task> tmp = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getStatus() == "Complete") {
                tmp.add(task);
            }
        }
        return tmp;
    }

    public String getCategory() {
        return category == null
                ? ""
                : category;
    }

    public List<Task> getUndoneTasks() {
        ArrayList<Task> tmp = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getStatus() == "Incomplete") {
                tmp.add(task);
            }
        }
        return tmp;
    }

    /**
     * Gets the status of the task as a string
     * @return the string represeting the status of the task
     */
    public String getStatus() {
        return status == null
                ? ""
                : status;
    }

    /**
     * Sets the deadline of the project
     * @param deadline the datetime string to be parsed and set as deadline
     */
    public void setDeadline(String deadline) {
        this.deadlineDate = LocalDate.parse(deadline, formatter);
    }

    /**
     * Get the name of the task
     * @return
     */
    public Name getName() {
        return name;
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Project)) {
            return false;
        }
        Project other = (Project) obj;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name);
    }

    public String getDeadlineString() {
        return deadlineDate == null
                ? ""
                : deadlineDate.format(formatter);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name).toString();
    }

    public String getTeam() {
        return team.stream()
                .map(Member::toString) // Assuming Member class has getName() method returning String
                .collect(Collectors.joining(", "));
    }

    public List<Member> getTeamList() {
        return team;
    }

    public List<Comment> getComments() {
        return comments;
    }

    /**
     * @param member member to be found inside the team member list
     * @return boolean value (true/false) depending on whether the member is in the team
     */
    public boolean hasMember(Member member) {
        for (Member m : team) {
            if (m.equals(member)) {
                return true;
            }
        }
        return false;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    /**
     * Returns a new person with new name but the same task (for edit person command)
     */
    public Project createEditedProject(Name newName) {
        List<Task> newTaskList = new ArrayList<>(this.taskList);
        Project newProject = new Project(newName, newTaskList);
        newProject.setDeadline(this.deadlineDate.format(formatter));
        newProject.setCategory(this.category);
        newProject.assignTeam(this.team);
        newProject.setComment(this.comments);

        if (this.isCompleted()) {
            newProject.setComplete();
        } else {
            newProject.setIncomplete();
        }

        return newProject;
    }

}
