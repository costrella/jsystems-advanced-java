package pl.jsystems.advancedjava.reflection.examples.e1class;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

class ReflectionExample1Class
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionExample1Class.class);

    public static void main(String[] args)
    {
        new ReflectionExample1Class().run();
    }

    private void run()
    {
        LOGGER.info("GuineaPig.class class name: {}", GuineaPig.class.getSimpleName());
        LOGGER.info("GuineaPig.class canonical name: {}", GuineaPig.class.getCanonicalName());
        LOGGER.info("GuineaPig.class package name: {}", GuineaPig.class.getPackageName());

        GuineaPig guineaPig = new GuineaPig("Piggy", 2, 42);
        LOGGER.info("GuineaPig.class class name: {}", guineaPig.getClass().getSimpleName());
        LOGGER.info("GuineaPig.class canonical name: {}", guineaPig.getClass().getCanonicalName());
        LOGGER.info("GuineaPig.class package name: {}", guineaPig.getClass().getPackageName());

        LOGGER.info("Is GuineaPig subtype of Object? {}", Object.class.isAssignableFrom(GuineaPig.class));
        LOGGER.info("Is Object subtype of GuineaPig? {}", GuineaPig.class.isAssignableFrom(Object.class));

        LOGGER.info("Is GuineaPig subtype of Object? {}", Object.class.isAssignableFrom(GuineaPig.class));
        LOGGER.info("Is Object subtype of GuineaPig? {}", Arrays.asList(GuineaPig.class.getDeclaredFields()));
    }
}
