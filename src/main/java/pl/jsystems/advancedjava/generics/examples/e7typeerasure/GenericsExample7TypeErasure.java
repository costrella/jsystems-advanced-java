package pl.jsystems.advancedjava.generics.examples.e7typeerasure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericsExample7TypeErasure
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsExample7TypeErasure.class);

    public static void main(String[] args)
    {
        GenericsExample7TypeErasure experiments = new GenericsExample7TypeErasure();
        experiments.run();
    }

    private void run()
    {
        LOGGER.info("Lets see how we can break stuff...");

        proveThatObjectsOfGenericTypesDontKnowAnythingAboutGenerics();
        proveThatArraysKnowTheirTypeAtRuntime();
    }

    private void proveThatObjectsOfGenericTypesDontKnowAnythingAboutGenerics()
    {
        LOGGER.info("Let us have a list of strings - strings only!");
        List<String> variableStoringListOfStrings = new ArrayList<>();
        variableStoringListOfStrings.add("first");
        variableStoringListOfStrings.add("second");

        List secondVariableReferencingSameListOfStrings = variableStoringListOfStrings;
        secondVariableReferencingSameListOfStrings.add(3d);
        secondVariableReferencingSameListOfStrings.add("fourth");
        secondVariableReferencingSameListOfStrings.add(LocalDate.now());

        variableStoringListOfStrings.add("what is going on here?!");
        variableStoringListOfStrings.add("It seems list (!)object(!) doesn't know about the generic type it has.");

        // this doesn't work, obviously :)
        //variableStoringListOfStrings.add(3);

        LOGGER.info("My list of 'strings' using first variable {}", variableStoringListOfStrings);
        LOGGER.info("My list of 'strings' using second variable {}", secondVariableReferencingSameListOfStrings);
    }

    private static void proveThatArraysKnowTheirTypeAtRuntime()
    {
        LOGGER.info("We know we can cheat lists - lets check with arrays!");
        String[] variableStoringArrayOfStrings = new String[3];
        variableStoringArrayOfStrings[0] = "first";
        LOGGER.info("First element added: {}", Arrays.toString(variableStoringArrayOfStrings));

        LOGGER.info("Well now, that will fail - the ArrayStoreException being thrown here is by design...");
        Object[] secondVariableReferencingSameArrayOfStrings = variableStoringArrayOfStrings;
        secondVariableReferencingSameArrayOfStrings[1] = 2;

        variableStoringArrayOfStrings[2] = "You won 10 million dollars! (I wish we got to this place)";
        LOGGER.info("List with good news (will newer be shown): {}", Arrays.toString(variableStoringArrayOfStrings));
    }
}
