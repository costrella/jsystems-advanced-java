package pl.jsystems.advancedjava.generics.exercises.e8upperboundwildcards;

import java.util.UUID;

interface CargoMessageContent extends MessageContent
{
    UUID getCargoUUID();
}
