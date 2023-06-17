package pl.jsystems.advancedjava.lambdas.solutions.s1consumerinterface.contents;

import java.util.UUID;

public interface VehicleMessageContent extends MessageContent
{
    UUID getVehicleId();
}
