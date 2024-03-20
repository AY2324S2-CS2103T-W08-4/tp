package seedu.address.model.project;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.List;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Represents a Task of Project
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Project {

    // Identity fields
    private final Name projectName;

    private LocalDate deadlineDate;

    private boolean status;

    private List<Person> people;
    private List<Task> tasks;

    /**
     * Constructs a new task object
     * @param name the task name
     */
    public Project(String name) {
        requireAllNonNull(name);
        this.projectName = new Name(name);
        this.status = true;
    }

    public Project(Name name) {
        requireAllNonNull(name);
        this.projectName = name;
        this.status = true;
    }

    public boolean isSameProject(Project otherProject) {
        if (otherProject == this) {
            return true;
        }

        return otherProject != null
                && otherProject.getName().equals(getName());
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        int i = 0;
        for (Task t : tasks) {
            if (t.equals(task)) {
                tasks.remove(i);
                break;
            }
            i += 1;
        }
    }

    /**
     * Returns true if the Project has a task that is equal to the specified task
     */
    public boolean hasTask(Task task) {
        for (Task t : tasks) {
            System.out.println(task.getName().fullName);
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Assigns a Person to the task
     * @param person the person assigned to the task
     */
    public void addPerson(Person person) {
        this.people.add(person);
    }

    /**
     * Sets the task status as complete
     */
    public void setComplete() {
        this.status = false;
    }

    /**
     * Sets the task status as incomplete
     */
    public void setIncomplete() {
        this.status = true;
    }

    /**
     * Gets the status of the task as a string
     * @return the string represeting the status of the task
     */
    public String getStatus() {
        if (status) {
            return "Incomplete";
        } else {
            return "Complete";
        }
    }

    /**
     * Sets the deadline of the project
     * @param deadline the datetime string to be parsed and set as deadline
     */
    public void setDeadline(String deadline) {
        this.deadlineDate = LocalDate.parse(deadline);
    }

    /**
     * Get the name of the task
     * @return
     */
    public Name getName() {
        return projectName;
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
        return projectName.equals(other.projectName);
    }

}
