package pl.jsystems.advancedjava.generics.exercises.e13extendinggenericclasses;

import java.util.UUID;

interface VehicleMessageContent extends MessageContent
{
    UUID getVehicleId();
}
