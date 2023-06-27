## Ćwiczenie 5.

1. Klasa `MyThread` jest teraz w innym pliku - teraz przy odebraniu notyfikacji 'śpi' przez jakiś czas.
   Stworzone są 4 wątki oczekujące na notyfikacje.
2. Zastąp synchronizację z użyciem `synchronized` na synchronizację z użyciem 'blokady' `Lock`.
3. Dodaj dwa 'warunki' `Condition` wyjścia z 'blokady' - jeden powinien być uruchamiany
   w przypadku wylosowania liczby 0-5, drugi dla pozostałych liczb.
4. 2 wątki powinny być uruchamiane dla wartości jednego 'warunku', 2 pozostałe wątki dla drugiego.
5. Gdy główny wątek zakonczy generowanie liczb powinien poczekać 2 sekundy i 'przeszkodzić' (interrupt) innym wątkom.
6. *Zastanów się jak teraz przekazać wylosowaną liczbę do odbiornika.
7. *Sprawdź ile razy wywołał się odbiornik (sprawdź logi "PROCESSING NEW EVENT!"), a ile razy nadajnik.
