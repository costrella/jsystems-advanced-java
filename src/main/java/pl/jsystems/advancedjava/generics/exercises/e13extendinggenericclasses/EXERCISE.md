## Ćwiczenie 13.
1. Zapoznaj się z klasami `CargoLoadedMessageContent` i `CargoUnloadedMessageContent` 
i ich strukturą dziedziczenia.
Czego lepiej użyć do porównywania WIADOMOŚCI względem wielkości załadunku - 
implementacji interfejsu `Comparator` czy `Comparable`?
Zaimplementuj wybrane rozwiązanie, porównaj dwie wiadomości z jego użyciem.
2. *Zrób to samo dla porównania WIADOMOŚCI względem czasu załadunku (rozładunku).
3. Spraw, aby klasa / rekord `Message` implementował interfejs `Comparable` w taki sposób,
aby można było porównywać wiadomości po polu z datą ('sentAt'). Sprawdź działanie porównując
ze sobą dwie wiadomości.
