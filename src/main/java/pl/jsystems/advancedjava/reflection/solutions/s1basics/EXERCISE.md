## Ćwiczenie 1.

1. Zapoznaj się ze zmianami w kodzie. Cała 'logika biznesowa' została przeniesiona
   do klasy `LogisticsMessagesApp` bez większych zmian. Metoda `main`
   znajduje się zaś w klasie `ReflectionExercise1Basics`.
2. W metodzie `main`, korzystając z refleksji, wyświetl pełną nazwę (wraz z paczką) klasy `Message`.
3. Wyświetl jej pola oraz metody (raz publiczne, raz wszystkie).
4. Zwróc uwagę, na pole 'content'! Jakiego jest typu?
5. Dodana została klasa `SampleConcreteMessage` dziedzicząca po `Message` 
(`Message` nie jest już rekordem, bo rekord jest klasą 'finalną'). Zobacz jak wygląda deklaracja nowej klasy.
6. Na tej nowej klasie wywołaj metodę `getGenericSuperclass()` i wyświetl jej wynik.
7. *Sprwadź `Message` czy dziedziczy po klasie `Object`.
