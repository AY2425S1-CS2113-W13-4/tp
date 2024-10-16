package tutorlink.consolepackage;

import tutorlink.resultpackage.CommandResult;

public class Console {
    /**
     * Generic printing method that prints output onto the terminal.
     *
     * @param message <code>String</code> to be printed to the terminal
     */
    public static void printMessage(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + message);
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints the "Welcome Message" (first message users see when starting the application).
     */
    public static void printWelcomeMessage() {
        String logo = "___________      __               .____    .__        __    \n"
                + "\\__    ___/_ ___/  |_  ___________|    |   |__| ____ |  | __\n"
                + "  |    | |  |  \\   __\\/  _ \\_  __ \\    |   |  |/    \\|  |/ /\n"
                + "  |    | |  |  /|  | (  <_> )  | \\/    |___|  |   |  \\    < \n"
                + "  |____| |____/ |__|  \\____/|__|  |_______ \\__|___|  /__|_ \\\n"
                + "                                          \\/       \\/     \\/\n";
        System.out.println("\tHello from\n" + logo + "\n");
        printMessage("Hello! I'm TutorLink\n \tWhat can I do for you?");
    }

    public static void displayCommandResult(CommandResult commandResult) {
        printMessage(commandResult.toString());
    }
}
