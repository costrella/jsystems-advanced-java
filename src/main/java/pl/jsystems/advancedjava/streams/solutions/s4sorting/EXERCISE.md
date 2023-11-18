## Ćwiczenie 4.

1. Spróbuj posortować wiadomości z listy `cargoLoadedMessages` z użyciem strumienia i metody `sorted()`.
   Wyświetl wyniki.
2. Dlaczego ta metoda nie działa?
3. Zmień klasę `Message` tak, by implementowała interfejs `Comparable`
4. Spróbuj ponownie posortować wiadomości.
5. Do strumienia, po sortowaniu, dodaj kroki tak, aby na końcu otrzymać String
   z datami wysłania wiadomości połączonymi przecinkami. Wyświetl wynik i zweryfikuj czy faktycznie są w odpowiedniej
   koleności.
6. Użyj klas `CargoLoadingTimeTakenComparator` oraz `CargoLoadWeightComparator` do posortoania listy wiadomości.
   Ponownie - przemapuj wiadomości na odpowiednie dane (czas / waga) i wyświetl je.
7. *Czy możesz posortować wiadomości po czasie załadunku lub wadze załadunku bez użycia powyższych 'komparatorów'?
   Jeśli masz pomyśł - spróbuj to zaimplementować. 