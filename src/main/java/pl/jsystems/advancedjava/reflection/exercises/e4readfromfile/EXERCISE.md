## Ćwiczenie 4.

1. W klasie `CargoLoadedMessageRepository` w linii 65 jest miejsce na zdefiniowanie strumienia
   danych do odczytu starych wiadomości. Zdefiniuj ten strumień z użyciem klasy `ClassLoader`.
   W zasobach znajduje się plik z wiadomościami.
   W logu przy starcie aplikacji powinna się pojawić informacja czy strumień został odczytany.
2. Zwróć uwagę, że wiadomości się zapisują do folderu ./output/
3. Odczytaj inny plik (z folderu ./output/) 'WORKING DIRECTORY' / 'USER DIR'.
4. *Spójrz na linię 72 - zastanów się czym może być `TypeReference` i jak jest używany w tym przypadku.
5. *Zobacz jak zapisywane są wiadomości - zakomentowany jest inny sposób zapisu - sprawdź czy działa.
