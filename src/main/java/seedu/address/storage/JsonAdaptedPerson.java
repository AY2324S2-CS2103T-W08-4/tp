package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.project.Member;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String deadline;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final String category;
    private final String projectStatus;

    private final List<JsonAdaptedMember> team = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("deadline") String deadline,
            @JsonProperty("category") String category, @JsonProperty("projectStatus") String projectStatus,
            @JsonProperty("team") List<JsonAdaptedMember> team) {
        this.name = name;
        this.deadline = deadline;
        this.category = category;
        this.projectStatus = projectStatus;
        if (team != null) {
            this.team.addAll(team);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        deadline = source.getDeadlineString();
        category = source.getCategory();
        projectStatus = source.getStatus();
        team.addAll(source.getTeamList().stream()
                .map(JsonAdaptedMember::new)
                .collect(Collectors.toList()));

    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Member> members = new ArrayList<>();
        for (JsonAdaptedMember member : team) {
            members.add(member.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);
        Person toReturn = new Person(modelName);
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

        return toReturn;
    }

}
