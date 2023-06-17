package pl.jsystems.advancedjava.lambdas.solutions.s3consumerlambda.contents;

import java.util.UUID;

public interface VehicleMessageContent extends MessageContent
{
    UUID getVehicleId();
}
