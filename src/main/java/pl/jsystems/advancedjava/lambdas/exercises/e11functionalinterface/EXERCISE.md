## Ćwiczenie 11.

1. Napisz (generyczny) interfejs funkcyjny `TriPredicate<T>`,
   z metodą `test` przyjmującą 3 argumenty tego samego typu i zwracającą `boolean`.
2. Napisz (generyczny) interfejs funkcyjny o nazwie `TriConsumer<T>`,
   z metodą `consume` przyjmującą 3 argumenty tego samego typu.
3. Napisz klasę generyczną `TriPredicateBufferedFilter<T>` przyjmującą
   w konstruktorze obiekt typu `TriPredicate<T>` oraz obiekt typu `TrySupplier<T>`,
   (parametry generyczne powinny być takie same - `T`).
4. Klasa powinna mieć metodę `bufferIfMatches` przyjmującą 3 wartości typu `T`
   Metoda powinna używać predykatu i jeżeli warunek jest spełniony buforować
   (zapisywać do późniejszego użycia) te 3 liczby.
   Klient powinien być w stanie wywołać tę metodę kilka razy
   i wszystkie pasujące 'trójki' parametrów powinny być zapisane do późniejszego użycia:
   np:

```
filter.bufferIfMatches(1, 2, 3); // pasuje, zapisuje trójkę liczb 1,2,3
filter.bufferIfMatches(4, 5, 6); // nie pasuje, nie zapisuje
filter.bufferIfMatches(7, 8, 9); pasuje, zapisuje tójkę liczb 7,8,9
stringFilter.bufferIfMatches("a", "b", "c"); // pasuje, zapisuje trójkę Stringów.
```

5. Klasa `TriPredicateBufferedFilter<T>` powinna mieć też metodą `consumeAllMatched`, powodującą
   przekazanie wszystkich zapisanych 'trójek' do konsumera i wyczyszczenie bufora.
   Ta metoda nie powinna przyjmować, ani zwracać argumentów.
6. W klasie `LambdasExercise11FunctionalInterface` jest zakomentowany przykład użycia
   powyższych klas / interfejsów. Dokończ przykład tak, aby kod się kompilował i działał.
7. *Spróbuj stworzyć rekord o nazwie `Triplet` do przechowywania 3-ch wartości i zastosuj go
   w klasie `TriPredicateBufferedFilter`.
