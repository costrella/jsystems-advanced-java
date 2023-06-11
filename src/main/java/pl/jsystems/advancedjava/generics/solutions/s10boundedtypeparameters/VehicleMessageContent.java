package pl.jsystems.advancedjava.generics.solutions.s10boundedtypeparameters;

import java.util.UUID;

interface VehicleMessageContent extends MessageContent
{
    UUID getVehicleId();
}
