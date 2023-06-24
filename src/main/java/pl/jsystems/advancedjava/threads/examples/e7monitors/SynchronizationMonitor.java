package pl.jsystems.advancedjava.threads.examples.e7monitors;

class SynchronizationMonitor
{
    private int availableThreadsCounter;
    private String threadInput;

    SynchronizationMonitor(int availableThreadsCounter, String threadInput)
    {
        this.availableThreadsCounter = availableThreadsCounter;
        this.threadInput = threadInput;
    }

    int getAvailableThreadsCounter()
    {
        return availableThreadsCounter;
    }

    void incrementAvailableThreadsCounter()
    {
        this.availableThreadsCounter++;
    }

    void decrementAvailableThreadsCounter()
    {
        this.availableThreadsCounter--;
    }

    String getThreadInput()
    {
        return threadInput;
    }

    void setThreadInput(String threadInput)
    {
        this.threadInput = threadInput;
    }
}
