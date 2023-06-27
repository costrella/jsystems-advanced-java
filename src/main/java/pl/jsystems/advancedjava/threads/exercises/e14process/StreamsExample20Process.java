package pl.jsystems.advancedjava.threads.exercises.e14process;

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
        // todo stuff
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

