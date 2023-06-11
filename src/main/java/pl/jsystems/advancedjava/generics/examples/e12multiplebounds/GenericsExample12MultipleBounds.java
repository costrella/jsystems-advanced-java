package pl.jsystems.advancedjava.generics.examples.e12multiplebounds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class GenericsExample12MultipleBounds
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExample12MultipleBounds.class);

    public static void main(String[] args)
    {
        GenericsExample12MultipleBounds experiments = new GenericsExample12MultipleBounds();
        experiments.run();
    }

    private void run()
    {
        LOGGER.info("Multiple bounds on type parameters...");

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

    private <T extends Number & Comparable<T>> Box<T> getBoxWithBiggerContent(Box<T> box1, Box<T> box2)
    {
        T content1 = box1.peekAtContent();
        T content2 = box2.peekAtContent();
        return content1.compareTo(content2) >= 0 ? box1 : box2;
    }
}
