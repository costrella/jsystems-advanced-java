package pl.jsystems.advancedjava.generics.exercises.e13extendinggenericclasses;

import java.util.UUID;

class GPSTrackingMessageContent implements VehicleMessageContent
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
