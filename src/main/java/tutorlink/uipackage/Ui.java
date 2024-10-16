package tutorlink.uipackage;

import tutorlink.exceptionspackage.MissingNextLineException;

import java.util.Scanner;

public class Ui {
    private Scanner in = new Scanner(System.in);

    public String collectUserInput() throws MissingNextLineException {
        if (!in.hasNextLine()) {
            throw new MissingNextLineException("No lines to parse!");
        }
        return in.nextLine();
    }
}
