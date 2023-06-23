## Strumienie w Javie (streams, java.util.stream)

### Definicja, strumienie a kolekcje danych
Strumienie definiują sekwencję elementów, które mogą być przetwarzane kolejno lub równolegle 
z wykorzystaniem funkcji agregujących.
Kolekcje pozwalają na zarządzanie wszystkimi na raz lub poszczególnymi elementami - 
strumienie określają zaś zestaw operacji (pipeline), które wykonywane będą na wszystkich elementach.

### Ważne cechy strumieni
* Strumień składa się ze źródła (source), operacji pośrednich oraz operacji końcowej.
* Pull-based - tzn. jeżeli nie zdefiniujemy operacji końcowej, strumień nie rozpocznie przetwarzania danych - 
inne biblioteki mogą oferować podejście push-based, tzn. każdy nowy element w strumieniu jest od razu przetwarzany.
* Strumień może być użyty tylko raz - próba ponownego użycia zakończy się wyjątkiem. 
Można jednak zbudować nowy strumień na podstawie tych samych danych - np. kolekcji.
* Elementy w strumieniach mogą być przetwarzane kolejno lub równologle (parallel stream).
* Strumienie mają dane uporządkowane (ordered) lub nieuporządkowane. 
Operacje równoległe lepiej wykonywać na nieuporządkowanych danych, gdyż wtedy nie ma ograniczeń
co do kolejności przetwarzania elementów i, co za tym idzie, łatwiej jest zrównoleglić operacje.


[Informacje dodatkowe - API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html)
