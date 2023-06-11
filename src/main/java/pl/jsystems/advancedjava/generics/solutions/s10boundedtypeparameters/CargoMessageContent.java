package pl.jsystems.advancedjava.generics.solutions.s10boundedtypeparameters;

import java.math.BigDecimal;
import java.util.UUID;

interface CargoMessageContent extends MessageContent
{
    UUID getCargoUUID();
    BigDecimal getCargoLoadInKgs();
}
