package pl.jsystems.advancedjava.generics.exercises.e13extendinggenericclasses;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.random.RandomGenerator;

class CargoLoadedMessageContent implements VehicleMessageContent, CargoLoadingMessageContent
{
    private final UUID vehicleId = UUID.randomUUID();
    private final UUID cargoId = UUID.randomUUID();
    private final BigDecimal cargoLoadInKgs = BigDecimal.valueOf(RandomGenerator.getDefault().nextDouble(5000));
    private final Long loadingTimeTakenInSeconds = RandomGenerator.getDefault().nextLong(0, 3600);

    @Override
    public UUID getVehicleId()
    {
        return vehicleId;
    }

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

    @Override
    public Long getLoadingTimeTakenInSeconds()
    {
        return loadingTimeTakenInSeconds;
    }
}
