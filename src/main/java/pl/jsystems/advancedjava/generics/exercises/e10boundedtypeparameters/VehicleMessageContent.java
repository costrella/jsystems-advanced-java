package pl.jsystems.advancedjava.generics.exercises.e10boundedtypeparameters;

import java.util.UUID;

interface VehicleMessageContent extends MessageContent
{
    UUID getVehicleId();
}
