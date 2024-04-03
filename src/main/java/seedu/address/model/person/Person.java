package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.project.Comment;
import seedu.address.model.project.Member;
import seedu.address.model.project.Task;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;

    private final List<Task> taskList;
    private LocalDate deadlineDate;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    private List<Member> team = new ArrayList<>();
    private String status = "None";
    private String category;

    private List<Comment> comments = new ArrayList<>();

    /**
     * Constructs a Person object with empty taskList
     */
    public Person(Name name) {
        requireAllNonNull(name);
        this.name = name;
        List<Task> taskList = new ArrayList<>();
        this.taskList = taskList;
        status = "incomplete";
    }

    /**
     * Constructs a Person object with specified taskList for creating edited Person
     */
    private Person(Name name, List<Task> tasks) {
        this.taskList = tasks;
        this.name = name;
    }

    /**
     * Adds task to the Person object
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    public void assignTeam(List<Member> team) {
        this.team = team;
    }

    /**
     * Removes task from the Person object
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
     * Sets status of project as complete
     */
    public void setComplete() {
        this.status = "complete";
    }

    /**
     * Sets category of project
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Sets status of project as incomplete
     */
    public void setIncomplete() {
        this.status = "incomplete";
    }

    /**
     * Returns true if ptoject is complete
     */
    public boolean isCompleted() {
        return status.equals("complete");
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
     * Sets the deadline of the task
     * @param deadline the datetime string to be parsed and set as deadline
     */
    public void setDeadline(String deadline) {
        this.deadlineDate = LocalDate.parse(deadline, formatter);
    }

    public Name getName() {
        return name;
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
     * Returns true if both projects have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherProject) {
        if (otherProject == this) {
            return true;
        }

        return otherProject != null
                && otherProject.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name);
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

    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Returns true if the Person has a task that is equal to the specified task
     */
    public boolean hasTask(Task task) {
        for (Task t : taskList) {
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
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
    public Person createEditedPerson(Name newName) {
        List<Task> newTaskList = new ArrayList<>(this.taskList);
        Person newPerson = new Person(newName, newTaskList);
        return newPerson;
    }

}
