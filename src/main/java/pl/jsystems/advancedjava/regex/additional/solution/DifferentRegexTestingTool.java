package pl.jsystems.advancedjava.regex.additional.solution;

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
            LOGGER.info("Enter new inputs to test (empty new line will mean no more inputs are expected):");
            while (true)
            {
                String newInput = scanner.nextLine();
                if (newInput.isEmpty()) {
                    break;
                }
                inputsToTest.add(newInput);
            }

            while (true) {
                LOGGER.info("Enter regex that matches all inputs:");
                String regex = scanner.nextLine();
                if (regex.isEmpty()) {
                    break;
                }

                int matchedCount = 0;
                int notMatchedCount = 0;
                for (String input : inputsToTest) {
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(input);
                    boolean matches = matcher.matches();
                    int ignored = matches ? matchedCount++ : notMatchedCount++;
                    LOGGER.info("Input {} does {}match {}", input, matches  ? "" : "NOT ", regex);
                }
                LOGGER.info("Matched: {}, not matched: {}", matchedCount, notMatchedCount);
            }

            LOGGER.info("Try again?");
            String tryAgain = scanner.nextLine();
            if (!tryAgain.equals("yes")) {
                break;
            }
        }
    }
}