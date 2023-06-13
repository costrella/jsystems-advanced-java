package pl.jsystems.advancedjava.generics.solutions.s13extendinggenericclasses;

import java.math.BigDecimal;
import java.util.UUID;

interface CargoMessageContent extends MessageContent
{
    UUID getCargoId();

    BigDecimal getCargoLoadInKgs();
}
