package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.StringUtil.generateRandomDigitString;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.project.Project;

/**
 * Adds a category to a project.
 */
public class SetProjectCategoryCommand extends Command {
    public static final String COMMAND_WORD = "set category";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " CATEGORY /to PROJECT_NAME";

    public static final String MESSAGE_SHOW_PROJECT_SUCCESS = "The project %1$s category is set as %2$s.";

    public static final String MESSAGE_PROJECT_NOT_FOUND = "Project %1$s not found: "
            + "Please make sure the project exists.";

    private final Project project;
    private final String category;

    /**
     * Creates an SetProjectCategoryCommand to add the specified {@code project}
     */

    public SetProjectCategoryCommand(String categoryName, Project project) {
        requireNonNull(project);
        requireNonNull(categoryName);
        this.project = project;
        this.category = categoryName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasProject(project)) {
            throw new CommandException(String.format(
                    MESSAGE_PROJECT_NOT_FOUND,
                    Messages.format(project)));
        }

        Project categoryProject = model.findProject(project.getName());
        Project dupProject = categoryProject.createEditedProject(
                new Name(generateRandomDigitString(20).toString())); // duplicate project with dummy name
        model.setProject(categoryProject, dupProject);
        Project realProject = dupProject.createEditedProject(project.getName());
        realProject.setCategory(category);
        model.setProject(dupProject, realProject);
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(MESSAGE_SHOW_PROJECT_SUCCESS,
                Messages.format(categoryProject), category));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetProjectCategoryCommand)) {
            return false;
        }

        SetProjectCategoryCommand otherSetProjectCategoryCommand = (SetProjectCategoryCommand) other;
        return project.equals(otherSetProjectCategoryCommand.project)
                && category.equals(otherSetProjectCategoryCommand.category);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("setCategory", category)
                .toString();
    }
}
