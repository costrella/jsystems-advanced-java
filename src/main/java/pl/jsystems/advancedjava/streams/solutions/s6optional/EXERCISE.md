## Ćwiczenie 6.

1. Zmień funkcję `findBy`Id w `MessageRepository` i wszystkich implementacjach, 
tak by zwracała wartość opcjonalną. Zauważ, że `CargoLoadedMessageRepository` jest tak spreparowana,
aby losowo zwracać poprawny rezultat.
2. Skorzystaj z `CargoLoadedMessageRepository` do odczytania pojdynczej wiadomości,
wykonaj na niej funkcję `map`, tak aby wyciągnąć z wiadomości czas załadunku. 
Dodaj domyślą wartość dla wartości opcjonalnej o wartości '120'. Wyświetl wynik metody `get()`.
3. Jeszcze raz odczytaj wiadomość z repozytorium, jeżeli istnieje, wyświetl ciężar - w innym wypadku wyrzuć wyjątek
4. *Spróbuj punkt 3 wykonać innym sposobem.
5. *Jeżeli wiadomość nie została zwrócona, wyświetl log "Message got lost..."
