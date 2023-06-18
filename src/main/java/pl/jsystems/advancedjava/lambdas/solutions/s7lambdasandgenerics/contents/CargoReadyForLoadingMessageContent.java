package pl.jsystems.advancedjava.lambdas.solutions.s7lambdasandgenerics.contents;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.random.RandomGenerator;

public class CargoReadyForLoadingMessageContent implements CargoMessageContent
{
    private final UUID cargoId = UUID.randomUUID();
    private final BigDecimal cargoLoadInKgs;

    public CargoReadyForLoadingMessageContent()
    {
        cargoLoadInKgs = BigDecimal.valueOf(RandomGenerator.getDefault().nextDouble(5000));
        ;
    }

    CargoReadyForLoadingMessageContent(BigDecimal cargoLoadInKgs)
    {
        this.cargoLoadInKgs = cargoLoadInKgs;
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
    public String toString()
    {
        return "CargoReadyForLoadingMessageContent{" +
                "cargoId=" + cargoId +
                ", cargoLoadInKgs=" + cargoLoadInKgs +
                '}';
    }
}
