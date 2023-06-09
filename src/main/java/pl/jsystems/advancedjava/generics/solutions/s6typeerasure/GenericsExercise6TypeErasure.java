package pl.jsystems.advancedjava.generics.solutions.s6typeerasure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

class GenericsExercise6TypeErasure
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExercise6TypeErasure.class);

    public static void main(String[] args)
    {
        new GenericsExercise6TypeErasure().run();
    }

    private void run()
    {
        breakTheBox();
        breakTheBoxWithListOfStrings();
    }

    private void breakTheBox()
    {
        LOGGER.info("Let's break that box!");
        Box<String> box = new Box<>();

        box.putInto("First content");
        LOGGER.info("My box can store only strings! {}", box);
        Box sameBox = box;
        sameBox.putInto(123);
        LOGGER.info("At least I thought so! {}", box);
    }

    private void breakTheBoxWithListOfStrings()
    {
        LOGGER.info("Let's break that box!");
        Box<List<String>> box = new Box<>();
        box.putInto(List.of("a", "b", "c"));
        LOGGER.info("My box can store only lists of strings! {}", box);

        Box sameBox = box;
        Box<List<Number>> stillSameBox = sameBox;
        stillSameBox.putInto(List.of(3, 4, 5));
        LOGGER.info("At least I thought so! Well, it can store other lists too... {}", box);

        sameBox.putInto(BigDecimal.TEN);
        LOGGER.info("Actually, anything can fit in here... {}", box);
    }
}
