package pl.jsystems.advancedjava.generics.exercises.e9upperboundwildcards;

import java.util.UUID;

class GPSTrackingMessageContent implements VehicleMessageContent
{
    @Override
    public UUID getVehicleId()
    {
        return UUID.randomUUID();
    }
}
