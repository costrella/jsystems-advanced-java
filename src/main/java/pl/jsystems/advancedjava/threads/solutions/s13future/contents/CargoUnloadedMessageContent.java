package pl.jsystems.advancedjava.threads.solutions.s13future.contents;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;

public class CargoUnloadedMessageContent implements VehicleMessageContent, CargoLoadingMessageContent
{
    private final UUID vehicleId = UUID.randomUUID();
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

    @Override
    public List<CargoElement> getCargoElements()
    {
        return cargoElements;
    }

    public CargoUnloadedMessageContent()
    {
        cargoElements = IntStream.of(0, 11)
                .mapToObj(ignored -> randomCargoElement())
                .toList();
        cargoLoadInKgs = cargoElements.stream()
                .reduce(BigDecimal.ZERO, (sum, element) -> element.weight().add(sum), BigDecimal::add);
        loadingTimeTakenInSeconds = RandomGenerator.getDefault().nextLong(0, 3600);
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

    private static CargoElement randomCargoElement()
    {
        return new CargoElement(UUID.randomUUID(),
                BigDecimal.valueOf(RandomGenerator.getDefault().nextDouble(1d, 1000d)));
    }
}

