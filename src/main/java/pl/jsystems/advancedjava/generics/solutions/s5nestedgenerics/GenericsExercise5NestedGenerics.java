package pl.jsystems.advancedjava.generics.solutions.s5nestedgenerics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericsExercise5NestedGenerics
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExercise5NestedGenerics.class);

    public static void main(String[] args)
    {
        new GenericsExercise5NestedGenerics().run();
    }

    private void run()
    {
        List<Message<Long, Map<Integer, Box<String>>>> listOfMessages = new ArrayList<>();
        Box<String> message1box1 = new Box<>();
        message1box1.putInto("message1 box1 content");
        Box<String> message1box2 = new Box<>();
        message1box2.putInto("message1 box2 content");

        Map<Integer, Box<String>> message1Content = new HashMap<>();
        message1Content.put(1, message1box1);
        message1Content.put(2, message1box2);
        listOfMessages.add(new Message<>(1L, message1Content));

        Box<String> message2box1 = new Box<>();
        message2box1.putInto("message2 box1 content");
        Map<Integer, Box<String>> message2Content = new HashMap<>();
        message2Content.put(1, message2box1);
        listOfMessages.add(new Message<>(2L, message2Content));

        LOGGER.info("List of complex messages: {}", listOfMessages);
        Box<String> oneOfTheBoxes = listOfMessages.get(0).content().get(2);
        LOGGER.info("Got one of the deeply hidden boxes: {}", oneOfTheBoxes);
    }
}
