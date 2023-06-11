package pl.jsystems.advancedjava.generics.exercises.e9upperboundwildcards;

import java.util.UUID;

interface CargoMessageContent extends MessageContent
{
    UUID getCargoUUID();
}
