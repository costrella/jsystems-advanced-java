## Ćwiczenie 3.

1. W zadaniu zdefiniowana jest lista nazw klas.
2. Dla każdego z elementów listy pobierz obiekt klasy przy użyciu moetody `Class.forName(...)`
3. Z uzyskanych klas odfiltruj te, które mają adnotację `ForDependencyInjection`
   oraz czy konstruktor bezargumentowy. Możesz sprawdzić adnotację na klasie podobnie do pól, metod, konstruktorów.
4. Dla znalezionych klas utwórz obiekty.
5. Podobnie jak w punkcie 3. odfiltruj klasy z adnotacją `MainClass` - powinna być tylko jedna
   (*mozesz wyrzucic wyjątek jak będzie więcej).
6. Utwórz obiekt tej klasy używając w konstruktorze obiektów utworzonych w punkcie 4.
7. Na tym obiekcie wywołaj metodę `run`.
