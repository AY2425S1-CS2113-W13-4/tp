//@@author yeekian
package tutorlink.parser;

import org.junit.jupiter.api.Test;
import tutorlink.command.AddStudentCommand;
import tutorlink.command.Command;
import tutorlink.command.DeleteStudentCommand;
import tutorlink.command.ExitCommand;
import tutorlink.command.FindStudentCommand;
import tutorlink.command.ListStudentCommand;
import tutorlink.exceptions.InvalidCommandException;


import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    void getCommand_addStudentCommand_addStudentCommandReturned() {

        Parser parser = new Parser();
        String input = "add_student i/A1234567X n/John";
        Command actualCommand = parser.getCommand(input);

        assertEquals(AddStudentCommand.class, actualCommand.getClass());
    }

    @Test
    void getCommand_deleteStudentCommand_deleteStudentCommandReturned() {
        Parser parser = new Parser();
        String input = "delete_student i/A1234567X";
        Command actualCommand = parser.getCommand(input);

        assertEquals(DeleteStudentCommand.class, actualCommand.getClass());
    }

    @Test
    void getCommand_exitCommand_exitCommandReturned() {
        Parser parser = new Parser();
        String input = "bye";
        Command actualCommand = parser.getCommand(input);

        assertEquals(ExitCommand.class, actualCommand.getClass());
    }

    @Test
    void getCommand_listStudentCommand_listStudentCommandReturned() {
        Parser parser = new Parser();
        String input = "list_student";
        Command actualCommand = parser.getCommand(input);

        assertEquals(ListStudentCommand.class, actualCommand.getClass());
    }

    @Test
    void getCommand_findStudentCommand_findStudentCommandReturned() {
        Parser parser = new Parser();
        String input = "find_student i/A1234567X n/John Doe";
        Command actualCommand = parser.getCommand(input);

        assertEquals(FindStudentCommand.class, actualCommand.getClass());
    }

    @Test
    void getCommand_invalidCommand_invalidCommandExceptionThrow() {
        Parser parser = new Parser();
        String input = "test_input";
        assertThrows(InvalidCommandException.class, () -> parser.getCommand(input));
    }

    @Test
    void getArguments_match_correctly() {
        Parser parser = new Parser();
        String[] prefixes = new String[]{"i/", "n/"};

        // Standard format
        String input = "add_student i/A1234567X n/John";
        HashMap<String, String> arguments = parser.getArguments(prefixes, input);
        assertEquals(2, arguments.size());
        assertEquals(arguments.get("i/"), "A1234567X");
        assertEquals(arguments.get("n/"), "John");

        // String with space
        String secondInput = "add_student i/A1234567X n/John Doe";
        HashMap<String, String> secondArguments = parser.getArguments(prefixes, secondInput);
        assertEquals(2, secondArguments.size());
        assertEquals(secondArguments.get("i/"), "A1234567X");
        assertEquals(secondArguments.get("n/"), "John Doe");

        // Missing argument
        String thirdInput = "add_student n/John Doe";
        HashMap<String, String> thirdArguments = parser.getArguments(prefixes, thirdInput);
        assertEquals(1, thirdArguments.size());
        assertNull(thirdArguments.get("i/"));
        assertEquals(thirdArguments.get("n/"), "John Doe");

        // Duplicate argument
        String fourthInput = "add_student n/John Doe i/A1234567X n/Evil John Doe";
        HashMap<String, String> fourthArguments = parser.getArguments(prefixes, fourthInput);
        assertEquals(2, fourthArguments.size());
        assertEquals(fourthArguments.get("i/"), "A1234567X n/Evil John Doe");
        assertEquals(fourthArguments.get("n/"), "John Doe");
    }

    @Test
    void getArguments_addStudentCommand_addStudentCommandHashMapReturned() {
        Parser parser = new Parser();
        String line = "add_student i/A1234567X n/John Doe";

        Command currentCommand = new AddStudentCommand();
        String[] argumentPrefixes = currentCommand.getArgumentPrefixes();

        HashMap<String, String> arguments = parser.getArguments(argumentPrefixes, line);

        assertEquals(2, arguments.size());
        assertEquals("A1234567X", arguments.get("i/")); // Check matriculation number
        assertEquals("John Doe", arguments.get("n/")); // Check
    }

    @Test
    void getArguments_exitCommandNoArgumentPrefix_exitCommandHashMapReturned() {
        Parser parser = new Parser();
        String line = "bye";

        Command currentCommand = new ExitCommand();
        String[] argumentPrefixes = currentCommand.getArgumentPrefixes();

        HashMap<String, String> arguments = parser.getArguments(argumentPrefixes, line);

        assertEquals(0, arguments.size());
    }

    @Test
    void getArguments_addStudentCommandExtraArguments_ignoreExtraTag() {
        Parser parser = new Parser();
        String line = "add_student i/A1234567X n/John Doe t/extraTag";

        Command currentCommand = new AddStudentCommand();
        String[] argumentPrefixes = currentCommand.getArgumentPrefixes();

        HashMap<String, String> arguments = parser.getArguments(argumentPrefixes, line);

        assertEquals(2, arguments.size());
        assertEquals("A1234567X", arguments.get("i/")); // Check matriculation number
        assertEquals("John Doe", arguments.get("n/")); // Check
    }

    @Test
    void getArguments_addStudentCommandSwappedArguments_addStudentCommandHashMapReturned() {
        Parser parser = new Parser();
        String line = "add_student n/John Doe i/A1234567X ";

        Command currentCommand = new AddStudentCommand();
        String[] argumentPrefixes = currentCommand.getArgumentPrefixes();

        HashMap<String, String> arguments = parser.getArguments(argumentPrefixes, line);

        assertEquals(2, arguments.size());
        assertEquals("A1234567X", arguments.get("i/")); // Check matriculation number
        assertEquals("John Doe", arguments.get("n/")); // Check
    }

    @Test
    void getArguments_addStudentCommandMissingArguments_hashMapWithOnlyGivenArgumentsReturned() {
        Parser parser = new Parser();
        String line = "add_student n/John Doe";

        Command currentCommand = new AddStudentCommand();
        String[] argumentPrefixes = currentCommand.getArgumentPrefixes();

        HashMap<String, String> arguments = parser.getArguments(argumentPrefixes, line);

        assertEquals(1, arguments.size());
        assertEquals("John Doe", arguments.get("n/")); // Check
    }

    @Test
    void getArguments_addStudentCommandInvalidArguments_emptyHashMapReturned() {
        Parser parser = new Parser();
        String line = "add_student j/test";

        Command currentCommand = new AddStudentCommand();
        String[] argumentPrefixes = currentCommand.getArgumentPrefixes();

        HashMap<String, String> arguments = parser.getArguments(argumentPrefixes, line);

        assertEquals(0, arguments.size());
    }
}
//@@author
