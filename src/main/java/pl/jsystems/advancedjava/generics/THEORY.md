## Typy Generyczne

### Runtime exceptions vs compile-time exceptions
Przed 5-tą wersją Javy, język nie posiadał typów generycznych.
Główny powodem ich prowadzenia była chęć zmniejszenia liczby błędów w czasie wykonywania programów (Runtime Exceptions), poprzez wcześniejsze ich wykrywanie - tj. sprawdzanie oczekiwanych typów w czasie kompilacji.

### Java 5 - założenia
Java zawsze zakładała wsteczną kompatybilność. Przy wprowadzaniu typów generycznych założono kompatybilność kodu źródłowego oraz kodu binarnego.
Alternatywą było wprowadzenie nowego zestawu klas, m.in. kolekcji, wtedy mielibyśmy do czynienia z np.:
`java.util.List` (bez typów generycznych) oraz nową klasę `java.collections.List` (z typami generycznymi).
Takie rozwiązanie przyjął `C#` i być może była lepsza strategia (patrząc długofalowo).

### Type erasure
Ze względu na chęć zachowania kompatybilności wstecznej, JVM, w trakcie wykonywania programu, nie może wiedzieć o typach generycznych. W związku z tym, informacja o nich jest (częściowo) usuwana podczas kompilacji, a następnie uruchamiania aplikacji.
Proces ten nazywa się `type erasure`.

Bytecode, pliki `.class` (efekt pierwszego etapu kompilacji) posiadają informację o typach generycznych, tak aby kod kliencki mógł korzystać z wcześniej skompilowanego kodu bibliotek / zależności. Jest to jednak informacja "dodatkowa", właściwość klas / pól / zmiennych generycznych.
Obiekt nie wie nic o swoim typie generycznym, wie tylko jakiej klasy jest instancją - np. `List`, a nie `List<String>`.

Podczas kompilacji typy generyczne są usuwane z kodu, a kompilator w odpowiednich miejscach dodaje rzutowania (`casting`), tak jak przed Javą 5 robili to programiści.

#### Type erasure - przykłady
1. Kompilator usuwa typy i dodaje rzutowanie:
```
List<String> myStringList = new ArrayList<>();
myStringList.add("test1");

String element = myStringList.get(0);
```
Po kompilacji, kod wykonywany (zgodność wsteczna z wersjami Javy poniżej 5):
```
// Parametrized type erased by the compiler
List myStringList = new ArrayList();
myStringList.add("test1");

// Casting added by the compiler (it knew to expect String)
String element = (String) myStringList.get(0);
```
2. Kompilator usuwa parametry generyczne w klasie i zastępuje je `określonymi` typami.
```
class Pair<T, R extends Comparable<R>> {
    private final T param1;
    private final R param2;

    Pair(T param1, R param2) {
        this.param1 = param1;
        this.param2 = param2;
    }
}
```
Po kompilacji, kod wykonywany (zgodność wsteczna z wersjami Javy poniżej 5):
```
class Pair {
    private final Object param1;
    private final Comparable param2;

    Pair(Object param1, Comparable param2) {
        this.param1 = param1;
        this.param2 = param2;
    }
}
```

#### Type erasure - reifiable types
[Reifiable types](https://docs.oracle.com/javase/tutorial/java/generics/nonReifiableVarargsType.html) to typy, które są jasno określone podczas działania programu, tzn. bez parametrów generycznych.
Zmienna typu `List<String>` nie jest `reifiable`, bo podczas działania programu nie różni się niczym od `List<Number>`

#### Ograniczenia
[Tutaj](https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html) można znaleźć listę ograniczeń związanych z typami generycznymi. Zawiera ona następujące punkty:
1. Parametry generyczne nie mogą być typami prymitywnymi.
2. Nie możemy tworzyć nowych instancji, obiektów typów generycznych np. `new T()`, gdzie `T` jest etykietą reprezentującą typ generyczny.
3. Statyczne pola nie mogą być typu generycznego - dotyczą klasy, a nie instancji.
4. Nie można używać typów generycznych z operatorem `instanceof`.
5. Nie można tworzyć tablic typów generycznych np.: `new List<String>[2]`
6. Nie można używać typów generycznych w połączeniu z blokiem `catch`, np.: `catch(T exception)`
7. Nie można przeciążać metod, gdzie argumentem jest typ generyczny.

