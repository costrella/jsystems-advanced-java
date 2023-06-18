package pl.jsystems.advancedjava.lambdas.exercises.e9genericsrevision.contents;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.random.RandomGenerator;

public class CargoUnloadedMessageContent implements VehicleMessageContent, CargoLoadingMessageContent
{
    private final UUID vehicleId = UUID.randomUUID();
    private final UUID cargoId = UUID.randomUUID();
    private final BigDecimal cargoLoadInKgs;
    private final Long loadingTimeTakenInSeconds;

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

    public CargoUnloadedMessageContent()
    {
        cargoLoadInKgs = BigDecimal.valueOf(RandomGenerator.getDefault().nextDouble(5000));
        loadingTimeTakenInSeconds = RandomGenerator.getDefault().nextLong(0, 3600);
    }

    public CargoUnloadedMessageContent(BigDecimal cargoLoadInKgs, Long loadingTimeTakenInSeconds)
    {
        this.cargoLoadInKgs = cargoLoadInKgs;
        this.loadingTimeTakenInSeconds = loadingTimeTakenInSeconds;
    }

    @Override
    public String toString()
    {
        return "CargoUnloadedMessageContent{" +
                "vehicleId=" + vehicleId +
                ", cargoId=" + cargoId +
                ", cargoLoadInKgs=" + cargoLoadInKgs +
                ", loadingTimeTakenInSeconds=" + loadingTimeTakenInSeconds +
                '}';
    }
}

