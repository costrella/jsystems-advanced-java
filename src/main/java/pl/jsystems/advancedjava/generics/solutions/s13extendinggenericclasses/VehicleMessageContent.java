package pl.jsystems.advancedjava.generics.solutions.s13extendinggenericclasses;

import java.util.UUID;

interface VehicleMessageContent extends MessageContent
{
    UUID getVehicleId();
}
