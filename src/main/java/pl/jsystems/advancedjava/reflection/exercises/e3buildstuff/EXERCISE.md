## Ćwiczenie 3.

1. W zadaniu zdefiniowana jest lista nazw klas.
2. Dla każdego z elementów listy pobierz obiekt klasy przy użyciu moetody `Class.forName(...)`
3. Z uzyskanych klas odfiltruj te, które mają adnotację `ForDependencyInjection` 
   oraz czy konstruktor bezargumentowy. Możesz sprawdzić adnotację na klasie podobnie do pól, metod, konstruktorów.
4. Dla znalezionych klas utwórz obiekty.
5. Podobnie jak w punkcie 3. odfiltruj klasy z adnotacją `ForDependencyInjection`, ale z parametrami w konstruktorze.
   Dla znalezionych klas utwórz obiekty, przekazując w konstruktorze utworzone w punkcie 4.
