## Ćwiczenie 3.

1. Utwórz strumień ze zmiennej `cargoLoadedMessages` 
i w dwóch krokach (z użyciem dwóch metod `map`) zmień go 
w strumień wartości `BigDecimal` zawierających wagę załadunku. 
W pierwszym kroku zamień wiadomość na treść, w drugim treść na kilogramy.
2. Przefiltruj powstały strumień tak by pozostały wartości większe od 1000.
3. Zmień wartości z BigDecimal na `String` z użyciem `map` i `toString()`.
4. Zamknij strumień z użyciem 'kolektora' `joining`, wyświetl uzyskany wynik.
5. *Spośród wszystkich wiadomości sprawdź ile jest takich, które mają załadunek 
więszy niż 2500kg i czas załadunku mniejszy od 10 minut. Wyświetl wynik (liczbę).
