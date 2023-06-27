## Ćwiczenie 14.

1. Korzystając z klas `ProcessBuilder` i `Process` stwórz i uruchom dowolny proces.
   Może to być np. `ls`, `dir`, `powershell` czy `bash`.
2. Spróbuj wykonać jakiś proces ze złożoną komendą np. `cp FILE1 FILE2`.
3. Postaraj się wyświetlić dane płynące z procesu - możesz skorzystać z przygotowanej metody:
   `copyProcessInputToUserOutput` albo przekierować strumienie wejścia / wyjścia z wykorzystaniem metody
   `.inheritIO()` klasy `ProcessBuilder`.
4. *Sprawdź czy proces 'żyje'. Jeśli tak - zakończ go.


