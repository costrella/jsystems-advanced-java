## Ćwiczenie 14.

1. W klasie `GenericsExercise14MultipleTypeParameters` przygotowanych jest kilka list.
   Dodny jest też zakomentowany kod z wywołaniem metody `mergeUnique`.
   Utwórz metodę `mergeUnique`, tak aby kod po odkomentowaniu działał.
   Metoda powinna przyjmować dwie listy i zwracać listę zawierającą elementy z pierwszej listy
   wraz z elementami drugiej (powtórzenia nie powinny być dodawane).
   Pierwsza lista może mieć elementy dowolnego typu,
   druga elementy takich samych typów co pierwsza, lub ich podtypów.
   Jeden z zakomentowanych przykładów sprawdza ten warunek - jego odkomentowanie powinno powodować błąd kompilacji.
2. *Spraw, by pierwsza lista przyjmowała tylko elementy typu / podtypów Number.
   Zakomentuj przykłady, które nie pasują do nowej sygnatury.