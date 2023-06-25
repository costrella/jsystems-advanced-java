## Ćwiczenie 13.

1. Zamień metodę `waitForAllToFinish` w klasie `ThreadsExercise13Future`
   na 'scheduled task' i pobierz z niego obiekt typu future.
   Niech ten 'task' zwraca czas jaki oczekiwał.
2. Zmień sygnaturę metody `receiveAndStore` tak, aby przyjmowała obiekt
   typu `ExecutorService` i aby zwracała `Future<Integer>`.
3. Zamiast tworzyć `ExecutorService` w metodzie `receiveAndRestore` utwórz go 'wyżej'
   tam też obsłuż zwrócony z metody obiekt `Future`.
4. *Zmień sygnaturę metod `startReceivingUsing` 'odbiorników' i przekaż im 'executor',
   zastąp istniejące, tym przekazanym.
5. *Zaobserwuj jak działa metoda `shutdown`. Czy wszystkie wątki się zatrzymują.
   Spróbuj z metodą `shutdownNow`.
