## Ćwiczenie 6.

1. W klasie `MessageLogger` na początku metody `logReceived` dodaj zmienną `List<String> details = new ArrayList<>();`.
2. W tej samej metodzie usuń warunek `else`, ale zostaw kod,
   który się w nim z nim znajduje - tak aby ten kod / log był zawsze wykonywany.
3. Dodaj nową zmienną `details` do wyświetlanych parametrów w tym logu.
4. Zmień 'konsumentów' w ten sposób, aby oprócz wiadomości, przyjmowali argument typu `List<String>`.
   Wywołując 'konsumentów' przekazuj dodatkowy parametr z listą szczegółów - `details`.
   Konsumenci nie powinni teraz nic logować,
   a jedynie dodawać do listy szczegóły specificzne dla danej wiadomości, np. tylko taki String:
   `Vehicle id: JAKIES_ID`.
5. Możesz teraz usunąć słowo kluczowe `else`, oba warunki są teraz od siebie niezależne.
6. *W deklaracji 'konsumentów' zastąp wywołanie statycznych metod wyrażeniami lambda.
7. *Zastanów się jak dalej można usprawnić ten kod.