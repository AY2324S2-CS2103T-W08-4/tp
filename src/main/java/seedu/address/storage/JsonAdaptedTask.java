package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.project.Member;
import seedu.address.model.project.Task;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {
    private final String name;
    private final String deadline;
    private final String member;


    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String name, @JsonProperty("deadline") String deadline,
                           @JsonProperty("member") String member) {
        this.name = name;
        this.deadline = deadline;
        this.member = member;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        name = source.getName().fullName;
        deadline = source.getDeadlineString();
        member = source.getMemberName();
    }



    /**
     * Converts this Jackson-friendly adapted Member object into the model's {@code Member} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Member.
     */
    public Task toModelType() throws IllegalValueException {
        Task current = new Task(name);
        if (deadline != null && deadline.length() != 0) {
            current.setDeadline(deadline);
        }
        if (member != null && member.length() != 0) {
            current.assignPerson(new Member(member));
        }
        return current;
    }
}

