## Ćwiczenie 11.

1. Bez użycia typu generycznego (type parameter - `T`) Napisz metodę
   `addIfDoesntExist`, przyjmującą dwa argumenty. Pierwszym jest liczba (Integer),
   drugim lista, do której ta liczba ma zostać dodana (jeżeli jeszcze jej tam nie ma).
   Lista musi mieć jakiś typ generyczny.
   Sprawdź czy metoda działa przy wywołaniu z przygotowanymi danymi testowymi.
2. Zmień powyższą metodę (lub napisz drugą `addIfDoesntExistWithTypeParameters`),
   tak aby korzystała z typu generycznego `T`. Czy słowo kluczowe 'super' jest potrzebne?
3. *Ogranicz typ generyczny `T` w utworzonej metodzie od góry typem `Number`.
   Czy teraz słowo kluczowe `super` jest potrzebne?