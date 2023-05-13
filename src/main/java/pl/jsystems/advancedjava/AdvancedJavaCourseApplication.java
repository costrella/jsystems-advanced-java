package pl.jsystems.advancedjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class AdvancedJavaCourseApplication
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AdvancedJavaCourseApplication.class);

    public static void main(String[] args)
    {
        LOGGER.info("==== Starting JSystems Advanced Java Course Application ====");
        new AdvancedJavaCourseApplication().run();
    }

    void run()
    {
        LOGGER.debug("Nothing much here, yet...");
    }
}
