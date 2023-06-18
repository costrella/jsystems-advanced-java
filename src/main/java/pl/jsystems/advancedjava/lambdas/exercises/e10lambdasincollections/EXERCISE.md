## Ćwiczenie 9.

1. Napisz własny interfejs funkcyjny `InputHandler` z abstrakcyjna metodą
   `handle` nie przyjmującą argumentów, a zwracającą typ `T`.
   Metoda powinna móc wyrzucać wyjątek IOException.
2. W klasie `LambdasExcercise10LambdasInCollections` stwórz mapę, gdzie klucz będzie typu `String`,
   a wartość będzie typu `InputHandler<Integer>` (lub dowolny inny parametr generyczny).
3. Uzupełnij mapę kilkoma wartościami z użyciem wyrażeń lambda. Mogą być bardzo proste.
   Jedna z opcji powinna wyrzucać wyjątek `IOException`, inna dowolny wyjątek `RuntimeException`.
4. Przekaż mapę do konstruktora klasy `ConsoleApp` i wykorzytaj tę mapę w obsłudze 'wejścia' z konsoli.
   Użyj metody `getOrDefault` dostępnej w mapie.
5. Wynik wywołania metody `handle` powinien określać czy aplikacja powinna być zakończona - np.
   zwrócenie 0 powinno zakończyć aplikację. Jedna z opcji w mapie /
   lub opcja domyślna powinna zatrzymywać aplikację.
