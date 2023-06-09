package pl.jsystems.advancedjava.generics.exercises.e6typeerasure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    }

    private void breakTheBox()
    {
        LOGGER.info("Let's break that box!");
        Box<String> box = new Box<>();
    }
}
