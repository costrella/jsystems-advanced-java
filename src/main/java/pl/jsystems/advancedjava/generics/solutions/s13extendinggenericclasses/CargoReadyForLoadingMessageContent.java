package pl.jsystems.advancedjava.generics.solutions.s13extendinggenericclasses;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.random.RandomGenerator;

class CargoReadyForLoadingMessageContent implements CargoMessageContent
{
    private final UUID cargoId = UUID.randomUUID();
    private final BigDecimal cargoLoadInKgs;

    CargoReadyForLoadingMessageContent()
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
