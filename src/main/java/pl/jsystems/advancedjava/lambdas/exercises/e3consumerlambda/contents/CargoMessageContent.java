package pl.jsystems.advancedjava.lambdas.exercises.e3consumerlambda.contents;

import java.math.BigDecimal;
import java.util.UUID;

public interface CargoMessageContent extends MessageContent
{
    UUID getCargoId();

    BigDecimal getCargoLoadInKgs();
}
