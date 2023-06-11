package pl.jsystems.advancedjava.generics.exercises.e10boundedtypeparameters;

import java.util.UUID;

class GPSTrackingMessageContent implements VehicleMessageContent
{
    @Override
    public UUID getVehicleId()
    {
        return UUID.randomUUID();
    }
}
