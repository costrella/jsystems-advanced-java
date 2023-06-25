## Ćwiczenie 6.

1. Zmień obsługę 'konsumera' w metodzie `receiveAndStore`,
   tak aby korzystała z `BlockingQueue` (`ArrayBlockingQueue`) zamiast dotychcasowego rozwiązania.
2. Zmień maksymalną zawartość kolejki na 1 i sprawdź jak się będzie zachowywał system.
   Spróbuj usunąć/dodać nowe wątki do obsługi wiadomości. Zobacz jak zmieni się zachowanie systemu.
   Zwróc uwagę na logi typu "Sending new message:" oraz "receiver job is done."
3. *Spraw aby klasa `CargoLoadedMessageRepositoryNotifier` była generyczna,
   potrafiła przyjąć dowolny typ wiadomości.
4. *Przywróć pozostałe 'odbieracze' wiadomości - dla rozładunku i gps,
   spraw by działały z wątkami, kolejką (kolejkami), zmienioną w poprzednim punkcie klasą.
5. *Dodaj kilka wątków dla wiadomości typu `CargoLoaded`, albo skróć czas zapisu wiadomości w odpowiednim repozytorium.
