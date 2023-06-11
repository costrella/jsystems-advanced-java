package pl.jsystems.advancedjava.generics.exercises.e9upperboundwildcards;

import java.util.UUID;

class CargoReadyForLoadingMessageContent implements CargoMessageContent
{
    @Override
    public UUID getCargoUUID()
    {
        return UUID.randomUUID();
    }
}
