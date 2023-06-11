package pl.jsystems.advancedjava.generics.exercises.e10boundedtypeparameters;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.random.RandomGenerator;

class CargoReadyForLoadingMessageContent implements CargoMessageContent
{
    @Override
    public UUID getCargoUUID()
    {
        return UUID.randomUUID();
    }

    @Override
    public BigDecimal getCargoLoadInKgs()
    {
        return BigDecimal.valueOf(RandomGenerator.getDefault().nextDouble(5000));
    }
}
