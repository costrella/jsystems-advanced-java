package pl.jsystems.advancedjava.generics.solutions.s10boundedtypeparameters;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.random.RandomGenerator;

class CargoLoadedMessageContent implements VehicleMessageContent, CargoMessageContent
{
    @Override
    public UUID getVehicleId()
    {
        return UUID.randomUUID();
    }

    @Override
    public UUID getCargoUUID()
    {
        return null;
    }

    @Override
    public BigDecimal getCargoLoadInKgs()
    {
        return BigDecimal.valueOf(RandomGenerator.getDefault().nextDouble(5000));
    }
}
