package pl.jsystems.advancedjava.threads.exercises.e6blockingqueue.contents;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;

public class CargoReadyForLoadingMessageContent implements CargoMessageContent
{
    private final UUID cargoId = UUID.randomUUID();
    private final BigDecimal cargoLoadInKgs;
    private final List<CargoElement> cargoElements;

    public CargoReadyForLoadingMessageContent()
    {
        cargoElements = IntStream.of(0, 11)
                .mapToObj(ignored -> randomCargoElement())
                .toList();
        cargoLoadInKgs = cargoElements.stream()
                .reduce(BigDecimal.ZERO, (sum, element) -> element.weight().add(sum), BigDecimal::add);
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
    public List<CargoElement> getCargoElements()
    {
        return cargoElements;
    }

    @Override
    public String toString()
    {
        return "CargoReadyForLoadingMessageContent{" +
                "cargoId=" + cargoId +
                ", cargoLoadInKgs=" + cargoLoadInKgs +
                '}';
    }

    private static CargoElement randomCargoElement()
    {
        return new CargoElement(UUID.randomUUID(),
                BigDecimal.valueOf(RandomGenerator.getDefault().nextDouble(1d, 1000d)));
    }
}
