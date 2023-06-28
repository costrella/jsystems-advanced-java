package pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.contents;

import java.util.UUID;

public class GPSTrackingMessageContent implements VehicleMessageContent
{
    private final UUID vehicleId = UUID.randomUUID();

    @Override
    public UUID getVehicleId()
    {
        return vehicleId;
    }

    @Override
    public String toString()
    {
        return "GPSTrackingMessageContent{" +
                "vehicleId=" + vehicleId +
                '}';
    }
}
