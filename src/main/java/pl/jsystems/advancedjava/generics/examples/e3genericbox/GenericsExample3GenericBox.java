package pl.jsystems.advancedjava.generics.examples.e3genericbox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class GenericsExample3GenericBox
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExample3GenericBox.class);

    public static void main(String[] args)
    {
        GenericsExample3GenericBox experiments = new GenericsExample3GenericBox();
        experiments.run();
    }

    private void run()
    {
        LOGGER.info("Creating the box storing integers.");
        Box<Integer> box = new Box<>();
        LOGGER.info("Putting something into the box.");
        box.putInto(42);
        LOGGER.info("Box contains integer: {}", box.peekAtContent());
        Integer content = box.getOutContent();
        LOGGER.info("Got out content - integer: {}", content);
        LOGGER.info("Box has content: {}", box.hasContent());
        LOGGER.info("Content should be null, is: {}", box.peekAtContent());
    }
}
