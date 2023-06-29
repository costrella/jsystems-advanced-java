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

    void run()
    {
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            LOGGER.info("Enter regex:");
            String regex = scanner.nextLine();

            LOGGER.info("Enter text to search:");
            String input = scanner.nextLine();

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            LOGGER.info("Matches? {}", matcher.matches());
            LOGGER.info("Group count: {}", matcher.groupCount());
            for (int groupIndex = 1; groupIndex < matcher.groupCount(); groupIndex++)
            {
                LOGGER.info("Group({}): {}", groupIndex, matcher.group(groupIndex));
            }

            matcher.reset();
            int counter = 0;
            while (matcher.find())
            {
                LOGGER.info("Found {} starting at index {} and ending at index {}.",
                        matcher.group(),
                        matcher.start(),
                        matcher.end());
                counter++;
            }

            LOGGER.info("In total found {} results: ", counter);
        }
    }
}
