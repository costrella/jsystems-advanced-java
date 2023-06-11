package pl.jsystems.advancedjava.generics.solutions.s9upperboundwildcards;

import java.util.UUID;

interface CargoMessageContent extends MessageContent
{
    UUID getCargoUUID();
}
