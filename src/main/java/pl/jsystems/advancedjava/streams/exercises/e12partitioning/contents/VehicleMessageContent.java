package pl.jsystems.advancedjava.streams.exercises.e12partitioning.contents;

import java.util.UUID;

public interface VehicleMessageContent extends MessageContent
{
    UUID getVehicleId();
}
