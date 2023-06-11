package pl.jsystems.advancedjava.generics.exercises.e9upperboundwildcards;

import java.util.UUID;

interface VehicleMessageContent extends MessageContent
{
    UUID getVehicleId();
}
