package pl.jsystems.advancedjava.generics.solutions.s8upperboundwildcards;

import java.util.UUID;

interface CargoMessageContent extends MessageContent
{
    UUID getCargoUUID();
}
