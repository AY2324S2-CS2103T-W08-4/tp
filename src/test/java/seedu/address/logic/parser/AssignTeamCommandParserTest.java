package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AssignTeamCommand;
import seedu.address.model.person.Name;
import seedu.address.model.project.Member;
import seedu.address.model.project.Project;

class AssignTeamCommandParserTest {
    private Project taskProject = new Project(new Name("Code"));
    private Member member = new Member("James");
    private List<String> team = new ArrayList<>();
    private AssignTeamCommandParser parser = new AssignTeamCommandParser();

    @Test
    public void parse_validArgs_returnsAddPersonCommand() {
        team.add("James");
        assertParseSuccess(parser, "James /to Code", new AssignTeamCommand(team, taskProject));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {


        assertParseFailure(
                parser,
                " /to Code",
                "Please enter valid names.");

        assertParseFailure(
                parser,
                "James /to ",
                "Please enter the project and team fields.");
    }
}
