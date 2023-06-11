package pl.jsystems.advancedjava.generics.solutions.s8upperboundwildcards;

import java.util.UUID;

class GPSTrackingMessageContent implements VehicleMessageContent
{
    @Override
    public UUID getVehicleId()
    {
        return UUID.randomUUID();
    }
}
