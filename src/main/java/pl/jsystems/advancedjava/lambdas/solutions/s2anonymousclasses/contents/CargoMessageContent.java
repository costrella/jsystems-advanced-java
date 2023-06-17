package pl.jsystems.advancedjava.lambdas.solutions.s2anonymousclasses.contents;

import java.math.BigDecimal;
import java.util.UUID;

public interface CargoMessageContent extends MessageContent
{
    UUID getCargoId();

    BigDecimal getCargoLoadInKgs();
}
