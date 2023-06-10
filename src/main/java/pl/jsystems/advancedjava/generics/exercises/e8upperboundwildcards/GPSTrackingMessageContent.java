package pl.jsystems.advancedjava.generics.exercises.e8upperboundwildcards;

import java.util.UUID;

class GPSTrackingMessageContent implements VehicleMessageContent
{
    @Override
    public UUID getVehicleId()
    {
        return UUID.randomUUID();
    }
}
