package pl.jsystems.advancedjava.generics.examples.e10boundedtypeparameters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

class GenericsExample10BoundedTypeParameters
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExample10BoundedTypeParameters.class);

    public static void main(String[] args)
    {
        GenericsExample10BoundedTypeParameters experiments = new GenericsExample10BoundedTypeParameters();
        experiments.run();
    }

    private void run()
    {
        Box<Integer> boxWithInteger = new Box<>();
        boxWithInteger.putInto(3);

        // the below does not compile - String does not extend Number
        // Box<String> boxWithStrings = new Box<>();
        // boxWithStrings.putInto("test");

        Box boxOfStrings = new Box();
        // so the below, does not compile.
        // you'd think with type erasure you'll be able to cheat.
        // not this time though! T is replaced with Number now, not with Object
        // boxOfStrings.putInto("abc");
        LOGGER.info("Box {} is designed to store Numbers only, no String will ever fit.", boxOfStrings);;

        // compiler warns us, that we may be putting a wrong type here,
        // because boxOfStrings is of raw type - but it's practically impossible
        // to be wrong here. We can suppress this warning as below
        boxOfStrings.putInto(2);
        //noinspection unchecked
        boxOfStrings.putInto(3);
    }
}

