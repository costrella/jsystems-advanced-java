package pl.jsystems.advancedjava.threads.exercises.e11concurrenttypes.contents;

import java.util.UUID;

public interface VehicleMessageContent extends MessageContent
{
    UUID getVehicleId();
}
