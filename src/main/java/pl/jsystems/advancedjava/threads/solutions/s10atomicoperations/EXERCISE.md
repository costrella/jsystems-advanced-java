## Ćwiczenie 10.

1. Zapoznaj się ze zmianami w kodzie. Został nieco zmieniony - żeby przyspieszyć działanie.
   Generowanych jest teraz 100 wiadomości każdego typu z mniejszymi opóźnieniami.
   Jest też więcej wątków.
2. Zmień obsługę licznika w klasie `MessageLogger` tak, by korzystała z klasy `AtomicInteger`.
   Sprwadź czy teraz działa poprawnie.
3. Zastanów się czy aplikacja jest już bezpieczna z punktu widzenia wielowątkowości.
   Czy są jeszcze jakieś słabe punkty?
4. Żeby sprawdzić swoje przypusczenia zakomentuj wywołanie metod `waitABit`
   w każdym z 'odbiorników' (m.in. `CargoLoadedMessageReceiver`). Uruchom aplikację kilka razy
   i sprawdź czy suma wiadomości  (wyświetlana na końcu działania aplikacji)
   pokrywa się z oczekiwaną sumą (300).
5. Jeżeli coś nie działa (a może jednak działa?!:)) jak można to poprawić? Spróbuj! :)
