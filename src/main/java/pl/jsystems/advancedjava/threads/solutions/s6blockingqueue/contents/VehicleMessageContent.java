package pl.jsystems.advancedjava.threads.solutions.s6blockingqueue.contents;

import java.util.UUID;

public interface VehicleMessageContent extends MessageContent
{
    UUID getVehicleId();
}
