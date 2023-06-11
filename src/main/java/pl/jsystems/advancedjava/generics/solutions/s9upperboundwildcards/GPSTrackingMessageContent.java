package pl.jsystems.advancedjava.generics.solutions.s9upperboundwildcards;

import java.util.UUID;

class GPSTrackingMessageContent implements VehicleMessageContent
{
    @Override
    public UUID getVehicleId()
    {
        return UUID.randomUUID();
    }
}
