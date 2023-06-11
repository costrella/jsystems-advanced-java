package pl.jsystems.advancedjava.generics.solutions.s8upperboundwildcards;

import java.util.UUID;

class CargoReadyForLoadingMessageContent implements CargoMessageContent
{
    @Override
    public UUID getCargoUUID()
    {
        return UUID.randomUUID();
    }
}
