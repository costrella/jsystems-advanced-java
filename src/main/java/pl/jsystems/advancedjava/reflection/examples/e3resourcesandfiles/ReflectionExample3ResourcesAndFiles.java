package pl.jsystems.advancedjava.reflection.examples.e3resourcesandfiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

class ReflectionExample3ResourcesAndFiles
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionExample3ResourcesAndFiles.class);

    public static void main(String[] args) throws IOException
    {
        new ReflectionExample3ResourcesAndFiles().run();
    }

    private void run() throws IOException
    {
        LOGGER.info("CLASSPATH vs. WORKDIR (USER DIR)");

        LOGGER.info("CLASSPATH:" + System.getProperty("java.class.path"));
        LOGGER.info("WORKDIR (USER DIR):" + System.getProperty("user.dir"));


        LOGGER.info("====================");
        LOGGER.info("WHAT USES CLASSPATH?");
        LOGGER.info("Class and ClassLoader, see below:");
        LOGGER.info("Consider Class first:");
        LOGGER.info("We have 2 methods:");
        LOGGER.info("this.getClass().getResourceAsStream() and this.getClass().getResourceAsStream()");
        LOGGER.info("Those use the paths in the same way");
        LOGGER.info("this.getClass().getResource(\"\") is a class path closes to the 'this' (ReflectionExample3ResourcesAndFiles) class. See:");
        var thisClassesPath = this.getClass().getResource("");
        LOGGER.info("{}", thisClassesPath);
        LOGGER.info("Please notice, that depending on what 'this' is (in 'this.getClass().getResource()') results may vary!");
        var loggerClassPath = LOGGER.getClass().getResource("");
        LOGGER.info("LOGGER.getClass().getResource(\"\") is a class path closes to the 'Logger' class. See:");
        LOGGER.info("{}", loggerClassPath);
        LOGGER.info("But we can also get an absolute path!");
        LOGGER.info("this.getClass().getResource(\"/\") returns absolute path for classpath. See:");
        var thisClassesAbsolutePath = this.getClass().getResource("/");
        LOGGER.info("For example, this.getClass().getResource(\"/log4j2.xml\") returns:");
        var log4jConfigFile = this.getClass().getResource("/");
        LOGGER.info("{}", log4jConfigFile);
        LOGGER.info("For base Java classes all this doesnt seem to work at all!");

        LOGGER.info("ClassLoader - use only without / at the beginning!");
        LOGGER.info("ClassLoader - do not use on base classes!");
        LOGGER.info("Like Object.class.getResource(\"\") or Thread.getClass().getClassLoader(\"\")");
        LOGGER.info("Use the below - it is 'always' the classpath.");
        LOGGER.info("Like this.class.getResource(\"\") or this.getClass().getClassLoader(\"\").getClass().getResourceAsStream()");
        var classLoaderFromClass = LOGGER.getClass().getClassLoader().getResource("log4j2.xml");
        LOGGER.info("{}", classLoaderFromClass);
        LOGGER.info("TESTING - with classloader");
        LOGGER.info("{}", new String(this.getClass().getClassLoader().getResourceAsStream("log4j2.xml").readAllBytes()));
        LOGGER.info("TESTING - with classloader, absolute path:");
        LOGGER.info("{}", new String(this.getClass().getResourceAsStream("/log4j2.xml").readAllBytes()));
        LOGGER.info("TO SUM UP - TYPICALLY USE:");
        LOGGER.info("this.getClass().getResource() methods with / at the beginning");
        LOGGER.info("this.getClass().getClassLoader().getResourceAsStream() methods without / at the beginning");

        LOGGER.info("====================");
        LOGGER.info("WHAT USER WORKDIR");
        LOGGER.info("String 'nio' package uses workdir as default path - you can also use '/' to have an absolute path.");
        LOGGER.info("{}", Paths.get("README.md").toFile().getCanonicalPath());
        LOGGER.info("{}", new String(new FileInputStream(Paths.get("README.md").toFile()).readAllBytes()));
    }
}