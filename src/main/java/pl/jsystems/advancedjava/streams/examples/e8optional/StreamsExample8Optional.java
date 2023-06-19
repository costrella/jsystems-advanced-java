package pl.jsystems.advancedjava.streams.examples.e8optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

class StreamsExample8Optional
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExample8Optional.class);

    public static void main(String[] args)
    {
        new StreamsExample8Optional().run();
    }

    private void run()
    {
        int userId = 3;
        Optional<Preferences> preferencesOptional = findUserPreferences(userId);
        Preferences userPreferences;
        if (preferencesOptional.isPresent())
        {
            userPreferences = preferencesOptional.get();
            LOGGER.info("Found preferences for user{}! {} ", userId, userPreferences);
            // do stuff...
        }
        else
        {
            LOGGER.info("Preferences for user with id {} not found!", userId);
        }

        Preferences defaultPreferences = new Preferences("red", "12", "en");
        userPreferences = findUserPreferences(userId).orElse(defaultPreferences);
        // do stuff

        // or
        // orElse(value)
        userPreferences = findUserPreferences(userId)
                .or(() -> findUserPreferences(userId + 1))
                .orElse(defaultPreferences);
        // do stuff

        // or(Supplier<Optional>)
        // orElseThrow
        userPreferences = findUserPreferences(userId)
                .or(() -> findUserPreferences(userId + 1))
                .orElseThrow(() -> new IllegalStateException("That cannot be!"));

        // if present
        findUserPreferences(userId)
                .ifPresent(preferences -> LOGGER.info("Found preferences for user{}! {} ", userId, preferences));

        // if presentOrElse
        findUserPreferences(userId)
                .ifPresentOrElse(preferences -> LOGGER.info("Found preferences for user{}! {} ", userId, preferences),
                        () -> LOGGER.info("Preferences for user with id {} not found!", userId));

        // map function
        findUserPreferences(userId)
                .map(Preferences::colour).ifPresentOrElse(colour -> LOGGER.info("Favourite colour: " + colour),
                        () -> LOGGER.info("Preferences for user with id {} not found!", userId));
    }

    Optional<Preferences> findUserPreferences(int id)
    {
        if (id % 2 == 0)
        {
            Preferences person = new Preferences("colour" + id, "font" + id, "language" + id);
            return Optional.of(person);
        }
        return Optional.empty();
    }

    record Preferences(String colour, String font, String language)
    {

    }
}
