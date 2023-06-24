## Ćwiczenie 3.

1. Zapoznaj się z kodem w obecnej postaci. Dla uproszczenia, metoda `receiveAndStore`
   obsługuje tylko wiadomości dla `CargoLoadedMessageContent`.
2. W repozytorium dla tych wiadomości, w metodzie `save`,
   tuż przed zapisem wiadomości do mapy dodaj kod, który spowoduje, że zapis będzie trwał sekundę.
   Obsłuż w zalecany sposób wyjątek `InterruptedException`.
3. Zaobserwuj jak zachowuje się aplikacja - czy wiadomości są szybko odbierane,
   czy każda kolejna czeka na zapis poprzedniej?
4. W metodzie `receiveAndStore`, w definicji 'konsumera' wiadomości (w wyrażeniu lambda),
   utwórz nowy wątek i w nim wykonaj wszystkie operacje które ten 'konsumer' ma wykonać -
   pamiętaj o uruchomieniu (`start`) wątku.
5. Jak teraz zachowuje się aplikacja - czy wiadomości są szybko odbierane? Kiedy są zapisywane?
   Co 'widzi' 'główny' wątek tuż przed zakonczeniem? Czy inne wątki mogą działać bez 'głównego' wątku?
