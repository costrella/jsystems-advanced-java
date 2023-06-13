package pl.jsystems.advancedjava.generics.examples.e15extendinggenericclasses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;

class GenericsExample15ExtendingGenericClasses
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExample15ExtendingGenericClasses.class);

    public static void main(String[] args)
    {
        GenericsExample15ExtendingGenericClasses experiments = new GenericsExample15ExtendingGenericClasses();
        experiments.run();
    }

    private void run()
    {
        LOGGER.info("We can extend generic classes and implement generic interfaces.");

        NumberBox<Number> numberBox = new NumberBox<>();
        numberBox.putInto(42d);
        LOGGER.info("Numeric box has positive value - {}.", numberBox.isContentPositive());

        IntegerBox integerBox = new IntegerBox();
        integerBox.putInto(-42);
        LOGGER.info("Integer box has positive value - {}, has even value - {}.",
                integerBox.isContentPositive(), integerBox.isContentEven());

        LOGGER.info("Box with bigger content: {}", compareBoxes(numberBox, integerBox));
        LOGGER.info("Box with bigger content: {}", new BoxComparator().compare(numberBox, integerBox));
    }

    private NumberBox<? extends Number> compareBoxes(NumberBox<? extends Number> box1, NumberBox<? extends Number> box2)
    {
        return (box1.compareTo(box2)) >= 0 ? box1 : box2;
    }

    static class BoxComparator implements Comparator<NumberBox<? extends Number>>
    {

        @Override
        public int compare(NumberBox<? extends Number> o1, NumberBox<? extends Number> o2)
        {
            return o1.compareTo(o2);
        }
    }
}
