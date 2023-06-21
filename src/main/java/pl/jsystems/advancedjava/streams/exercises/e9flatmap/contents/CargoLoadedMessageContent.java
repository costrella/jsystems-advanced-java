package pl.jsystems.advancedjava.streams.exercises.e9flatmap.contents;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;

public class CargoLoadedMessageContent implements VehicleMessageContent, CargoLoadingMessageContent
{
    private static final List<UUID> ids = List.of(UUID.randomUUID(), UUID.randomUUID());

    private final UUID vehicleId = ids.get(RandomGenerator.getDefault().nextInt(0, 2));
    private final UUID cargoId = UUID.randomUUID();
    private final BigDecimal cargoLoadInKgs;
    private final List<CargoElement> cargoElements;
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
        cargoElements = IntStream.of(0, 11)
                .mapToObj(ignored -> randomCargoElement())
                .toList();
        cargoLoadInKgs = BigDecimal.ZERO; // todo stuff - cargoElements.stream()...
        loadingTimeTakenInSeconds = RandomGenerator.getDefault().nextLong(0, 3600);
    }

    @Override
    public List<CargoElement> getCargoElements()
    {
        return cargoElements;
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

    private static CargoElement randomCargoElement()
    {
        return new CargoElement(UUID.randomUUID(),
                BigDecimal.valueOf(RandomGenerator.getDefault().nextDouble(1d, 1000d)));
    }
}
