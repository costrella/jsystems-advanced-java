package pl.jsystems.advancedjava.generics.exercises.e9upperboundwildcards;

import java.util.UUID;

class CargoUnloadedMessageContent implements VehicleMessageContent, CargoMessageContent
{
    @Override
    public UUID getVehicleId()
    {
        return UUID.randomUUID();
    }

    @Override
    public UUID getCargoUUID()
    {
        return UUID.randomUUID();
    }
}

