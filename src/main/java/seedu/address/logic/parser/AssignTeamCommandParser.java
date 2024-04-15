package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.AssignTeamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.project.Project;


/**
 * Parses input arguments and creates a new AssignTeamCommand object
 */
public class AssignTeamCommandParser implements Parser<AssignTeamCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AssignTeamCommand
     * and returns an AssignTeamCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AssignTeamCommand parse(String args) throws ParseException {
        try {
            if (!args.contains(" /to ")) {
                throw new ParseException("Whoops! When referring to another field like a project,"
                        + " always remember to put /to instead of just to.");
            }
            String members = args.split(" /to")[0].trim();
            String projectName = args.split(" /to")[1].trim();
            List<String> team = Arrays.stream(members.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
            if (team.stream().anyMatch(member -> (member.length() == 0 || !Name.isValidName(member)))) {
                throw new ParseException("Please enter valid names.");
            }
            if ((team.size() == 0) || (projectName.length() == 0)) {
                throw new ParseException("Please enter the project and team fields.");
            }
            Project project = new Project(ParserUtil.parseName(projectName));
            return new AssignTeamCommand(team, project);
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AssignTeamCommand.MESSAGE_USAGE));
        }
    }
}
