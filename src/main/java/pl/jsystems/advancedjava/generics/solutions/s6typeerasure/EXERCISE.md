## Ćwiczenie 6.

1. W klasie `GenericsExercise6TypeErasure` dodana została metoda `breakTheBox`.
   Dodane zostało również jej wywołanie z głównej metody `run`.
   W metodzie `breakTheBox` utworzona jest zmienna (o nazwie `box`) typu `Box<T>`
   oraz tworzony jest obiekt typu `Box` przypisany do tej zmiennej.
   Do powyższej metody dodaj kod, który najpierw uzupełni 'pudełko' obiektem typu `String`,
   a następnie obiektem typu `Integer`. Wyświetl zawartość pudełka na konsoli z użyciem zmiennej `box`.
2. *Dodaj metodę `breakTheBoxWithListOfStrings`. Stwórz w niej nowy obiekt typu `Box<List<String>>`,
   przypisz go do zmiennej. Spróbuj dodać listę innych niż String obiektów,
   spraw by dało się to zrobić i wyświetl rezultat.
3. *W tej samej metodzie dodaj kolejny krok,
   tak aby to samo 'pudełko' przechowywało teraz liczby, dodaj liczbę i wyświetl zawartość.