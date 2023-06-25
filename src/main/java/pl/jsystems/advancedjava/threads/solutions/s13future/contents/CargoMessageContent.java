package pl.jsystems.advancedjava.threads.solutions.s13future.contents;

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
