package pl.jsystems.advancedjava.generics.solutions.s9upperboundwildcards;

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
