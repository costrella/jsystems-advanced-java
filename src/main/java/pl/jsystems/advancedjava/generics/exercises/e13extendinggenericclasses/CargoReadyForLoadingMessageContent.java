package pl.jsystems.advancedjava.generics.exercises.e13extendinggenericclasses;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.random.RandomGenerator;

class CargoReadyForLoadingMessageContent implements CargoMessageContent
{
    private final UUID cargoId = UUID.randomUUID();
    private final BigDecimal cargoLoadInKgs = BigDecimal.valueOf(RandomGenerator.getDefault().nextDouble(5000));

    @Override
    public UUID getCargoId()
    {
        return cargoId;
    }

    @Override
    public BigDecimal getCargoLoadInKgs()
    {
        return cargoLoadInKgs;
    }
}
