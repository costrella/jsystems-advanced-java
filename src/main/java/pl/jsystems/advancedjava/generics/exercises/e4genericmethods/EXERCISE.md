## Ćwiczenie 4.

1. Stwórz klasę o nazwie `BoxContentReplacer`.
2. Klasa powinna mieć generyczną metodę `replaceUsing` przyjmującą dwa argumenty -
   'pudełko', i nową zawartość. Metoda powinna logować informację, że ktoś wymienia zawartość.
   Metoda powinna zwracać starą zawartość.
3. Użyj nowej klasy / metody w klasie `GenericsExercise3GenericMethod` (zakomentowany kod),
   możesz dodać kilka własnych przykładów.
4. *Dodaj trzeci argument (`username`) do metody `replaceUsing`
   i wykorzystaj go przy logowaniu informacji o odczycie wiadomości.
5. *Metoda powinna wyrzucać wyjątki typu `IllegalStateException`
   w przypadku pustgeo pudełka.
6. *Metoda powinna wyrzucać wyjątki typu `IllegalArgumentException`
   w przypadku, gdy pudełko lub nowa zawartość są równe `null`.