package pl.jsystems.advancedjava.regex.additional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DifferentRegexTestingTool
{

    private static final Logger LOGGER = LoggerFactory.getLogger(DifferentRegexTestingTool.class);

    public static void main(String[] args)
    {
        new DifferentRegexTestingTool().run();
    }

    void run()
    {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            List<String> inputsToTest = new ArrayList<>();

            // todo stuff

            LOGGER.info("Try again?");
            String tryAgain = scanner.nextLine();
            if (!tryAgain.equals("yes")) {
                break;
            }
        }
    }
}