## Ćwiczenie 7.

1. W klasie `MessageLogger` zmień 'konsumentów' tak, aby przyjmowali argumenty typu `MessageContent`,
a nie `Message`.
2. Zauważ, że bloki warunkowe w metodzie `logReceived` dla obu typów wiadomości są takie same - 
różnią się tylko 'predytkatem' i 'konsumerem'. 
Można tę część kodu sparametryzować, poprawiając reużywalność.
3. Stwórz prywatną statyczną zagnieżdżoną klasę `MessageDetailsExtractor`.
4. Klasa powinna mieć dwa 'finalne' pola - jedno z 'predykatem', jedno z 'bi-konsumerem'.
Powinna mieć też konstruktor przyjmujący dwa argumenty i inicjalizujący nimi te dwa pola. 
5. Stwórz metodę `extractInto`, przyjmującą argumenty typu `List<String>` oraz `MessageContent`.
Zachowanie metody powinno być takie jak bloków warunkowych w metodzie `logReceived`.
6. Stwórz dwa obiekty klasy `MessageDetailsExtractor` z użyciem odpowiednich 'predykatów' i 'konsumentów'.
Zapewne pojawią się problemy z typami generycznymi - w jakimś miejscu trzeba będzie zastosować rzutowanie.
7. Zastąp instrukcje warunkowe wywołaniem metody `extractInto` na odpowiednich obiektach.
8. *Dodaj oba obiekty klasy `MessageDetailsExtractor` do listy 
i spraw aby lista była 'finalnym' polem w klasie `MessageLogger`.
W metodzie `logReceived` dla każdego elementu tej listy wywołaj metodę `extractInto`.
9. *Zastanów się nad rzutowaniem, które pojawiło się w kodzie. Jak w łatwy sposób można sprawić 
by podczas uruchomienia aplikacji pojawił się błąd `ClassCastException`?
10. *Jak jeszcze możemy usprawnić ten kod?
