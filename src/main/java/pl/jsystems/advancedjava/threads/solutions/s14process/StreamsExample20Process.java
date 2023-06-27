package pl.jsystems.advancedjava.threads.solutions.s14process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

class StreamsExample20Process
{
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamsExample20Process.class);

    public static void main(String[] args) throws IOException
    {
        new StreamsExample20Process().run();
    }

    private void run() throws IOException
    {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cp", "README.md", "README.md.bak")
                // for full experience, you can comment it out, but then comment last two lines of this method
                //.inheritIO()
                .start();

        //Process process = processBuilder.command("powershell").start();
        Process process = processBuilder.command("bash")
                // for full experience, you can comment it out, but then comment last two lines of this method
                //.inheritIO()
                .start();

        new Thread(() -> copyProcessInputToUserOutput(process)).start();
        copyUserInputToProcessOutput(process);
        process.destroy();

        sleepForABit();
        if (process.isAlive())
        {
            LOGGER.info("Process still alive!");
            process.destroyForcibly();
            sleepForABit();
            LOGGER.info("Process ended ? with status {}", process.exitValue());
        }
        else
        {
            LOGGER.info("Process ended with status {}", process.exitValue());
        }

    }

    private static void copyProcessInputToUserOutput(final Process process)
    {
        BufferedInputStream input = new BufferedInputStream(process.getInputStream());
        // Reads first byte from file
        int i = readByte(input);

        while (i != -1)
        {
            System.out.print((char) i);
            i = readByte(input);
        }
        closeInputStream(input);
    }

    private static void copyUserInputToProcessOutput(Process process) throws IOException
    {
        Scanner keyboard = new Scanner(System.in);
        String input = "";
        try (BufferedWriter writer = process.outputWriter())
        {
            while (!input.equals("exit"))
            {
                input = keyboard.nextLine();
                writer.write(keyboard.nextLine());
                writer.newLine();
                writer.flush();
            }
        }
    }

    private static void closeInputStream(final BufferedInputStream input)
    {
        try
        {
            input.close();
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static int readByte(final BufferedInputStream input)
    {
        try
        {
            return input.read();
        } catch (IOException e)
        {
            LOGGER.error("Error while reading input from the process.", e);
            throw new RuntimeException("Error while reading input from the process.", e);
        }
    }

    private static void sleepForABit()
    {
        try
        {
            Thread.sleep(500);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
}

