package pl.jsystems.advancedjava.lambdas.solutions.s9genericsrevision.comparators;

import pl.jsystems.advancedjava.lambdas.solutions.s9genericsrevision.contents.CargoLoadingMessageContent;

import java.util.Comparator;
import java.util.Objects;

class CargoLoadingTimeTakenComparator implements Comparator<CargoLoadingMessageContent>
{

    @Override
    public int compare(CargoLoadingMessageContent cargo1, CargoLoadingMessageContent cargo2)
    {
        Objects.requireNonNull(cargo1, "First cargo cannot be null.");
        Objects.requireNonNull(cargo2, "Second cargo cannot be null.");
        return cargo1.getLoadingTimeTakenInSeconds().compareTo(cargo2.getLoadingTimeTakenInSeconds());
    }
}
