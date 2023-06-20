package pl.jsystems.advancedjava.streams.solutions.s8concatandreduce.contents;

import java.math.BigDecimal;
import java.util.UUID;

public interface CargoMessageContent extends MessageContent
{
    UUID getCargoId();

    BigDecimal getCargoLoadInKgs();
}
