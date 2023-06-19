package pl.jsystems.advancedjava.streams.exercises.e5distinct.contents;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.random.RandomGenerator;

public class CargoLoadedMessageContent implements VehicleMessageContent, CargoLoadingMessageContent
{
    private static final List<UUID> ids = List.of(UUID.randomUUID(), UUID.randomUUID());

    private final UUID vehicleId = ids.get(RandomGenerator.getDefault().nextInt(0, 2));
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

    public CargoLoadedMessageContent()
    {
        cargoLoadInKgs = BigDecimal.valueOf(RandomGenerator.getDefault().nextDouble(5000));
        loadingTimeTakenInSeconds = RandomGenerator.getDefault().nextLong(0, 3600);
    }

    public CargoLoadedMessageContent(BigDecimal cargoLoadInKgs, Long loadingTimeTakenInSeconds)
    {
        this.cargoLoadInKgs = cargoLoadInKgs;
        this.loadingTimeTakenInSeconds = loadingTimeTakenInSeconds;
    }

    @Override
    public String toString()
    {
        return "CargoLoadedMessageContent{" +
                "vehicleId=" + vehicleId +
                ", cargoId=" + cargoId +
                ", cargoLoadInKgs=" + cargoLoadInKgs +
                ", loadingTimeTakenInSeconds=" + loadingTimeTakenInSeconds +
                '}';
    }
}
