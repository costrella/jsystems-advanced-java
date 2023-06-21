package pl.jsystems.advancedjava.streams.exercises.e9flatmap.contents;

import pl.jsystems.advancedjava.streams.exercises.e8concatandreduce.contents.MessageContent;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface CargoMessageContent extends MessageContent
{
    UUID getCargoId();

    BigDecimal getCargoLoadInKgs();

    List<CargoElement> getCargoElements();

    record CargoElement(UUID id, BigDecimal weight) {}
}
