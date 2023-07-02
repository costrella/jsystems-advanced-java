package pl.jsystems.advancedjava.answers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class ComparatorInMaxMethod
{
    public static void main(String[] args)
    {
        // Dlaczego możemy użyć metody compareTo w metodzie 'max' stream, która wymaga comaratora.
        List<Integer> integers = new ArrayList<>(List.of(1, 2, 3, 4));

        // metoda max wymaga comparator'a, compareTo zwraca int
        integers.stream().max(Integer::compareTo);

        // poniższe jest lambdą implementującą interfejs Comparator, i jest równoznaczne zapisowi Integer::compareTo
        integers.stream().max((a, b) -> a.compareTo(b));

        // metoda max wymaga dwóch parametrów wejściowych - wyrażenie a.compareTo(b) ma dwa parametry.
        // kompilator potrafi więc zmapować to wyrażenie oraz wyrażenie Integer::compareTo do comparatora.

        // lambda implementująca komparator
        Comparator<Integer> example1 = (a, b) -> a.compareTo(b);
        // metoda statyczna, której 'interfejs' / 'api' jest takie samo jak Comparatora, sygnatura pasuje.
        Comparator<Integer> example2 = Integer::compare;
        // metoda (!)instancji(!), w zwiąku z tym, jeden argument wejściowy jest traktowany jako obiekt, na którym
        // wywoływana jest metoda .compareTo, a drugi argument wejściowy traktowany jest jako parametr metody compareTo.
        // tak jak przedstawione to zostało wyżej.
        Comparator<Integer> example3 = Integer::compareTo;
        // działa także z własnymi implementacjami Comparatora (ten sam interfejs).
        MyComparator<Integer> example4 = Integer::compareTo;

    }

    interface MyComparator<T> {
        int compare(T a, T b) throws IOException;
    }
}
