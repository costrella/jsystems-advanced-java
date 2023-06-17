package pl.jsystems.advancedjava.lambdas.exercises.e1consumerinterface.contents;

import java.util.UUID;

public interface VehicleMessageContent extends MessageContent
{
    UUID getVehicleId();
}
