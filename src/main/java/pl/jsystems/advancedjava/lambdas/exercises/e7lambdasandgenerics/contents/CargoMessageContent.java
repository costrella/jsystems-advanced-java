package pl.jsystems.advancedjava.lambdas.exercises.e7lambdasandgenerics.contents;

import java.math.BigDecimal;
import java.util.UUID;

public interface CargoMessageContent extends MessageContent
{
    UUID getCargoId();

    BigDecimal getCargoLoadInKgs();
}
