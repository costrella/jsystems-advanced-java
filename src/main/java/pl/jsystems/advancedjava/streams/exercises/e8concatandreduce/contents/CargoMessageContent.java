package pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.contents;

import java.math.BigDecimal;
import java.util.UUID;

public interface CargoMessageContent extends MessageContent
{
    UUID getCargoId();

    BigDecimal getCargoLoadInKgs();
}
