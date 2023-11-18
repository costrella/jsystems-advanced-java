package pl.jsystems.advancedjava.generics.examples.e6nestedgenerics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class GenericsExample6NestedGenerics
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExample6NestedGenerics.class);

    public static void main(String[] args)
    {
        GenericsExample6NestedGenerics experiments = new GenericsExample6NestedGenerics();
        experiments.run();
    }

    private void run()
    {
        LOGGER.info("Creating a list of strings");
        List<String> values = new ArrayList<>();
        values.add("first string");
        values.add("second string");

        Message<UUID, List<String>> message = new Message<>(UUID.randomUUID(), values);
        LOGGER.info("Message with a list of strings: {}", message);
        message.content().add("third string");
        LOGGER.info("Someone has been peeking! We now have more strings {}", message);

        // NOTE! Dygresja dot. niezmienności - Immutability. Zawartość wiadomości może być polem finalnym,
        // ale jeżeli to pole może zmieniać stan (np. ArrayList), to wtedy cała wiadomość nie jest niezmienna.
        // Warto zadbać o to, aby obiekty, które komuś przekazujemy były niezmienne,
        // inaczej ktoś może zmienić ich stan bez naszej wiedzy.
        List<String> immutableList = List.copyOf(values);
        Message<UUID, List<String>> trulyImmutableMessage = new Message<>(UUID.randomUUID(), immutableList);
        LOGGER.info("Message with new, immutable list of strings: {}", trulyImmutableMessage);
        LOGGER.info("Below exception is expected - peeking is ok, but changing the list isn't!");
        trulyImmutableMessage.content().add("third string");
    }
}