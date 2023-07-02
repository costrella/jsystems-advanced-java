package pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.ForDependencyInjection;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.MessageRepository;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.contents.CargoLoadedMessageContent;
import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.message.Message;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@ForDependencyInjection
public class CargoLoadedMessageRepository implements MessageRepository<CargoLoadedMessageContent>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CargoLoadedMessageRepository.class);

    private static final Map<UUID, Message<CargoLoadedMessageContent>> MESSAGES = new ConcurrentHashMap<>();
    private static final Path MESSAGE_STORAGE_PATH_REF_USER_DIR = Paths.get("output/saved-messages.json");
    private static final String MESSAGE_STORAGE_PATH_REF_CLASSPATH = "messages-to-start-with.json";

    private final ObjectMapper objectMapper;

    CargoLoadedMessageRepository()
    {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        readOldMessages();
    }

    private void readOldMessages()
    {
        try
        {
            doReadOldMessages();
        } catch (FileNotFoundException e)
        {
            LOGGER.warn("I have not found old messages... well, I can always create new ones for you!");
        } catch (IOException e)
        {
            throw new RuntimeException("Messages cannot be read!" + e.getMessage());
        }
    }

    private void doReadOldMessages() throws IOException
    {
        Files.createDirectories(MESSAGE_STORAGE_PATH_REF_USER_DIR.getParent());

        InputStream stream = new FileInputStream(MESSAGE_STORAGE_PATH_REF_USER_DIR.toFile());

        if (stream == null)
        {
            throw new FileNotFoundException("File not found! " + MESSAGE_STORAGE_PATH_REF_CLASSPATH);
        }
        var results = objectMapper
                .readValue(stream, new TypeReference<List<Message<CargoLoadedMessageContent>>>() { });
        MESSAGES.putAll(results.stream().collect(Collectors.toMap(Message::id, Function.identity())));
        LOGGER.info("Messages loaded! count: {}", MESSAGES.size());
    }

    @Override
    public List<Message<CargoLoadedMessageContent>> findAll()
    {
        return List.copyOf(MESSAGES.values());
    }

    @Override
    public Optional<Message<CargoLoadedMessageContent>> findById(UUID id)
    {
        return Optional.ofNullable(MESSAGES.get(id));
    }

    @Override
    public void save(Message<CargoLoadedMessageContent> message)
    {
        LOGGER.info("Saving cargo loading message: {}", message.id());
        waitForStorageInExternalSystem();
        MESSAGES.put(message.id(), message);
        writeToFile();
    }

    private void waitForStorageInExternalSystem()
    {
        try
        {
            Thread.sleep(50);
        } catch (InterruptedException e)
        {
            LOGGER.info("Saving thread got interrupted!");
            Thread.currentThread().interrupt();
            writeToFile(); //risky! ;)

            throw new RuntimeException(e);
        }
    }

    private void writeToFile()
    {
        try
        {
            //objectMapper.writer().writeValue(MESSAGE_STORAGE_PATH_REF_USER_DIR.toFile(), findAll());
            String result = objectMapper.writer().writeValueAsString(findAll());
            try (FileOutputStream fos = new FileOutputStream(MESSAGE_STORAGE_PATH_REF_USER_DIR.toFile()))
            {
                fos.write(result.getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException ex)
        {
            LOGGER.error("Error while trying to save our data! {}", ex.getMessage());
            throw new RuntimeException("Error while trying to save our data!", ex);
        }
    }
}
