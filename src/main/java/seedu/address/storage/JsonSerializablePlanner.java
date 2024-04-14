package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Planner;
import seedu.address.model.ReadOnlyPlanner;
import seedu.address.model.project.Project;

/**
 * An Immutable DevPlanPro that is serializable to JSON format.
 */
@JsonRootName(value = "planner")
class JsonSerializablePlanner {

    public static final String MESSAGE_DUPLICATE_PROJECT = "Projects list contains duplicate project(s).";

    private final List<JsonAdaptedProject> projects = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given projects.
     */
    @JsonCreator
    public JsonSerializablePlanner(@JsonProperty("projects") List<JsonAdaptedProject> projects) {
        this.projects.addAll(projects);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializablePlanner(ReadOnlyPlanner source) {
        projects.addAll(source.getProjectList().stream().map(JsonAdaptedProject::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Planner toModelType() throws IllegalValueException {
        Planner planner = new Planner();
        for (JsonAdaptedProject jsonAdaptedProject : projects) {
            Project project = jsonAdaptedProject.toModelType();
            if (planner.hasProject(project)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PROJECT);
            }
            planner.addProject(project);
        }
        return planner;
    }

}
