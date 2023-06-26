## Ćwiczenie 1.
1. Zapoznaj się z kodem projektu.
2. Stwórz parametryzowalną klasę `Message<T>`.
Klasa ta powinna posiadać pole finalne typu `T` przechowujące treść wiadomości,
powinna również mieć 'getter' do pobierania treści 
oraz przesłoniętą metodę `toString()` (IntelliJ potrafi ją wygenerować).
3. W klasie `GenericsExercise1GenericMessage` utwórz obiekt typu `Message<String>`.
Wyświetl w logach ten obiekt, oraz niezależnie treść wiadomości (pobraną przez 'getter').
4. *Zapewnij, by treść wiadomości nie była pusta.
