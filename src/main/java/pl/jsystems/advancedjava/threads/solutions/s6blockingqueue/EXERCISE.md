## Ćwiczenie 6.

1. Zmień obsługę 'konsumera' w metodzie `receiveAndStore`,
   tak by korzystała z kolejki `BlockingQueue`.
   Jedyne co konsumer powinien robić to dodawać wiadomości do kolejki (metoda `put`).
   Zainicjalizuj kolejkę z rozmiarem 1.
2. Kod który był wcześniej używany w 'konsumerze',
   powinien być przeniesiony do oddzielnego wątku, działać nieprzerwanie
   i reagować na zdarzenia na kolejce (metoda `take`).
3. *Sprawdź jak zachowuje się aplikacja - czemu obsługuje wiadomości tak wolno? Zmień ustawienia kolejki
   sprawdź czy to pomoże.
4. *Co możemy zrobić, aby usprawnić działanie / przyspieszyć obsługę wiadomości w tym konkretnym przypadku?
