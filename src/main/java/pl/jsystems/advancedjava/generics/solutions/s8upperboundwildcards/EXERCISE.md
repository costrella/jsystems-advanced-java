## Ćwiczenie 8.

1. Zapoznaj się z kodem w tej paczce.
   Do klasy `Message` używanej w poprzednich ćwiczeniach, dodane zostało pole typu `Instant`
   informujące o tym, kiedy wiadomość została wysłana.
   Id wiadomości jest teraz typu `UUID` (nie jest parametrem generycznym).
   Dodane zostały nowe klasy i interfejsy. Definiują one możliwe typy treści wiadomości.
2. W klasie `GenericsExercise8UpperBoundWildcards` dodaj metodę `logReceived` przyjmującą w argumencie
   wiadomość (parametr generyczny) i wyświetlajacą jej id oraz informację o tym kiedy została utworzona.
3. Zastanów się jak, spróbuj, wyświetlić informacje o typie treści otrzymanej wiadomości.
4. *Do wyświetlanej wiadomości dodaj informacje o obecnym czasie (chwili otrzymania wiadomości).