## Ćwiczenie 3.

1. Stwórz klasę o nazwie `MessageCreator`.
2. Klasa powinna mieć generyczną metodę `createUsing` przyjmującą dwa argumenty -
   dla identyfikatora oraz treści wiadomości. Ta metoda powinna tworzyć wiadomość
   z wykorzystaniem przekazanych argumentów, a następie logować stworzony obiekt.
3. Dodaj kolejną metodę `createUsing`, przyjmującą tylko 1 argument generyczny -
   dla treści wiadomości. Wybierz typ dla `id` - `Long`, `UUID`, inny.
   Metoda powinna zwracać utworzoną wiadomość.
4. Użyj obu metod w klasie `GenericsExercise3GenericMethod` (zakomentowany kod),
   możesz dodać kilka własnych przykładów.
5. *Zapewnij, aby id było unikalne (przyajmniej w kontekście działającej aplikacji).
6. *Czy można utworzyć kolejną metodę `createUsing` z 1 argumentem generycznym,
   ale tworzącą, zwracającą wiadomości z innym typem `id`?
7. *Dodaj test do klasy `MessageCreator`.