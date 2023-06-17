package pl.jsystems.advancedjava.lambdas.solutions.s3consumerlambda.contents;

public interface CargoLoadingMessageContent extends CargoMessageContent
{
    Long getLoadingTimeTakenInSeconds();
}
