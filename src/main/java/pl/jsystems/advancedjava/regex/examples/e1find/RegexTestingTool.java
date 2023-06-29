package pl.jsystems.advancedjava.regex.examples.e1find;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTestingTool
{

    private static final Logger LOGGER = LoggerFactory.getLogger(RegexTestingTool.class);

    public static void main(String[] args)
    {
        new RegexTestingTool().run();
    }

    void run() {
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            LOGGER.info("Enter regex:");
            Pattern pattern = Pattern.compile(scanner.nextLine());

            LOGGER.info("Enter text to search:");
            Matcher matcher = pattern.matcher(scanner.nextLine());

            boolean found = false;
            while (matcher.find())
            {
                LOGGER.info("Found {} starting at index {} and ending at index {}.",
                        matcher.group(),
                        matcher.start(),
                        matcher.end());
                found = true;
            }
            if (!found)
            {
                LOGGER.info("No match found.%n");
            }
        }
    }
}
