package pl.jsystems.advancedjava.lambdas.exercises.e9genericsrevision.contents;

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
