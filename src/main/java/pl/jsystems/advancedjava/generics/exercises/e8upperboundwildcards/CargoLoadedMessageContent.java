package pl.jsystems.advancedjava.generics.exercises.e8upperboundwildcards;

import java.util.UUID;

class CargoLoadedMessageContent implements VehicleMessageContent, CargoMessageContent
{
    @Override
    public UUID getVehicleId()
    {
        return UUID.randomUUID();
    }

    @Override
    public UUID getCargoUUID()
    {
        return null;
    }
}
