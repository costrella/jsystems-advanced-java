## Ćwiczenie 2.

1. W klasie `GPSTrackingMessageReceiver` dodaj linię powodującą,
   że główny wątek będzie czekał na zakończenie wątku generującego wiadomości.
   Obsłuż wyjątek InterruptedException tak jak jest to zrobione w metodzie `waitABit`.
2. Zrób to samo dla klas `CargoUnloadedMessageReceiver` i `CargoLoadedMessageReceiver`.
3. Czy obecny kod - pod względem szybkości działania różni się znacząco od kodu,
   w którym nie używaliśmy nowych wątków?
4. Zmień metodę w interfejsie `MessageReceiver<T extends MessageContent>`
   tak by zwracała wątek generujący wiadomości. Zmień wszystkie generatory by zwracały wątek po uruchomieniu,
   ale przed wywołaniem metody `join`, którą dodałaś / dodałeś przed chwilą.
5. W metodzie `receiveAndStore` klasy `ThreadsExercise2JoiningThreads`
   zmień wywołania metod, tak by zapisać poszczególne wątki.
   Na końcu metody poczekaj na wszystkie wątki z użyciem metody `join`.
