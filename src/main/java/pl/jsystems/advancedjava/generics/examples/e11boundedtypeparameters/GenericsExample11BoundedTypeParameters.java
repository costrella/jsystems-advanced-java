package pl.jsystems.advancedjava.generics.examples.e11boundedtypeparameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.concurrent.atomic.LongAdder;

class GenericsExample11BoundedTypeParameters
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExample11BoundedTypeParameters.class);

    public static void main(String[] args)
    {
        GenericsExample11BoundedTypeParameters experiments = new GenericsExample11BoundedTypeParameters();
        experiments.run();
    }

    private void run()
    {
        LOGGER.info("Bounded parameters types part2!");

        Box<Integer> boxWithInteger = new Box<>();
        boxWithInteger.putInto(100);
        Box<Integer> otherBoxWithInteger = new Box<>();
        otherBoxWithInteger.putInto(42);
        LOGGER.info("We have to boxes with integers: {}, {}", boxWithInteger, otherBoxWithInteger);

        Box<Integer> boxWithBiggerInteger = getBoxWithBiggerContent(boxWithInteger, otherBoxWithInteger);
        LOGGER.info("Box with bigger content is: {}", boxWithBiggerInteger);

        Box<Double> boxWithDouble = new Box<>();
        boxWithDouble.putInto(42d);
        Box<Double> otherBoxWithDouble = new Box<>();
        otherBoxWithDouble.putInto(2d);
        LOGGER.info("We have to boxes with integers: {}, {}", boxWithDouble, otherBoxWithDouble);

        Box<Double> boxWithBiggerDouble = getBoxWithBiggerContent(boxWithDouble, otherBoxWithDouble);
        LOGGER.info("Box with bigger content is: {}", boxWithBiggerDouble);
    }

    private <T extends Number> Box<T> getBoxWithBiggerContent(Box<T> box1, Box<T> box2)
    {
        T content1 = box1.peekAtContent();
        T content2 = box2.peekAtContent();
        return content1.doubleValue() - content2.doubleValue() >= 0 ? box1 : box2;
    }
}
