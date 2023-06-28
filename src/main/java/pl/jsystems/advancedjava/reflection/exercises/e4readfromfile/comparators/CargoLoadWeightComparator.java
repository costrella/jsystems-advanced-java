package pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.comparators;

import pl.jsystems.advancedjava.reflection.exercises.e4readfromfile.contents.CargoMessageContent;

import java.util.Comparator;
import java.util.Objects;

public class CargoLoadWeightComparator implements Comparator<CargoMessageContent>
{

    @Override
    public int compare(CargoMessageContent cargo1, CargoMessageContent cargo2)
    {
        Objects.requireNonNull(cargo1, "First cargo cannot be null.");
        Objects.requireNonNull(cargo2, "Second cargo cannot be null.");
        return cargo1.getCargoLoadInKgs().compareTo(cargo2.getCargoLoadInKgs());
    }
}
