package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Category;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String deadline;

    private final List<JsonAdaptedTag> category = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("deadline") String deadline, @JsonProperty("address") String address,
            @JsonProperty("category") List<JsonAdaptedTag> category) {
        this.name = name;
        this.deadline = deadline;
        if (category != null) {
            this.category.addAll(category);
        }

    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        deadline = source.getDeadlineString();
        category.addAll(source.getCategory().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Category> personCategories = new ArrayList<>();
        for (JsonAdaptedTag tag : category) {
            personCategories.add(tag.toModelType());
        }
        final Set<Category> modelCategories = new HashSet<>(personCategories);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);
        Person toReturn = new Person(modelName);
        if (modelCategories.size() != 0) {
            toReturn = new Person(modelName, modelCategories);
        }
        if (deadline != null && deadline.length() != 0) {
            toReturn.setDeadline(deadline);
        }

        return toReturn;
    }

}
