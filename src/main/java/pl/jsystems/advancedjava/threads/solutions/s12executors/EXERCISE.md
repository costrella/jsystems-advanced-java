## Ćwiczenie 12.

1. W klasach odbiorników zastąp tworzenie wątku poprzez `new Thread()`
   różnymi typami 'Executorów' - `SingleThreadExecutor` czy rodzajami `ScheduledThreadExecutor`.
   W przypadku 'scheduled', możesz usunąć wywołanie 'waitABit'.
   Usuń także `IntStream` - niech odbiorniki działają nieprzerwanie.
2. W głównej klasie dodaj nowy 'Executor', który co 5 sekund będzie
   sprawdzał i wyświetlał liczbę wiadomości danego typu.
3. *W metodzie `receiveAndStore` stwórz `CachedThreadPool`. Przekaż go do metody
   `receiveAndStoreFor` (zmień sygnaturę).
   Nie twórz kilku wątków `MessageRepositoryWorkerThread` zamiast tego, przekaż 'executora'
   do konstruktora `MessageRepositoryWorkerThread` i użyj go po pobraniu wiadomości z kolejki.
4. *Zmień nazwę klasy `MessageRepositoryWorkerThread` na `MessageRepositoryWorker`,
   spraw by nie rozszerzała już `Thread`, ale żeby implementowała `Runnable`.
   Zamiast startować wątek, dodaj obiekt tej klasy do 'executora'.
