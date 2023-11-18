## Typy Generyczne

### Runtime exceptions vs compile-time exceptions

Przed 5-tą wersją Javy, język nie posiadał typów generycznych.
Głównym powodem ich wprowadzenia była chęć zmniejszenia liczby błędów w czasie wykonywania programów (Runtime
Exceptions), poprzez wcześniejsze ich wykrywanie - tj. sprawdzanie oczekiwanych typów w czasie kompilacji.

### Java 5 - założenia

Java zawsze zakładała wsteczną kompatybilność. Przy wprowadzaniu typów generycznych założono kompatybilność kodu
źródłowego oraz kodu binarnego.
Alternatywą było wprowadzenie nowego zestawu klas, m.in. kolekcji, wtedy mielibyśmy do czynienia z np.:
`java.util.List` (bez typów generycznych) oraz nową klasę `java.collections.List` (z typami generycznymi).
Takie rozwiązanie przyjął `C#` i być może była lepsza strategia (patrząc długofalowo).

### Type erasure

Ze względu na chęć zachowania kompatybilności wstecznej, JVM, w trakcie wykonywania programu, nie może wiedzieć o typach
generycznych. W związku z tym, informacja o nich jest (częściowo) usuwana podczas kompilacji, a następnie uruchamiania
aplikacji.
Proces ten nazywa się `type erasure`.

Bytecode, pliki `.class` (efekt pierwszego etapu kompilacji) posiadają informację o typach generycznych, tak aby kod
kliencki mógł korzystać z wcześniej skompilowanego kodu bibliotek / zależności. Jest to jednak informacja "dodatkowa",
właściwość klas / pól / zmiennych generycznych.
Obiekt nie wie nic o swoim typie generycznym, wie tylko jakiej klasy jest instancją - np. `List`, a nie `List<String>`.

Podczas kompilacji typy generyczne są usuwane z kodu, a kompilator w odpowiednich miejscach dodaje
rzutowania (`casting`), tak jak przed Javą 5 robili to programiści.

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

[Reifiable types](https://docs.oracle.com/javase/tutorial/java/generics/nonReifiableVarargsType.html) to typy, które są
jasno określone podczas działania programu, tzn. bez parametrów generycznych.
Zmienna typu `List<String>` nie jest `reifiable`, bo podczas działania programu nie różni się niczym od `List<Number>`

#### Ograniczenia

[Tutaj](https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html) można znaleźć listę ograniczeń
związanych z typami generycznymi. Zawiera ona następujące punkty:

1. Parametry generyczne nie mogą być typami prymitywnymi.
2. Nie możemy tworzyć nowych instancji, obiektów typów generycznych np. `new T()`, gdzie `T` jest etykietą
   reprezentującą typ generyczny.
3. Statyczne pola nie mogą być typu generycznego - dotyczą klasy, a nie instancji.
4. Nie można używać typów generycznych z operatorem `instanceof`.
5. Nie można tworzyć tablic typów generycznych np.: `new List<String>[2]`
6. Nie można używać typów generycznych w połączeniu z blokiem `catch`, np.: `catch(T exception)`
7. Nie można przeciążać metod, gdzie argumentem jest typ generyczny.

### Dziedziczenie (inheritance) i typy wieloznaczne (wildcard)

#### Dziedziczenie (inheritance)

Dziedziczenie w przypadku typów generycznych jest mało intuicyjne.

`List<Integer>` **nie dziedziczy** po `List<Number>`. Wynika to z tego, że nie zachodzi relacja 'jest' ('is a').

`List<Integer>` jest listą **(!)tylko całkowitych(!)** liczb (nie może zawierać liczb zmiennoprzecinkowych).

`List<Number>` jest listą **(!)dowolnych(!)** liczb.

Lista **(!)tylko całkowitych(!)** liczb nie jest więc listą **(!)dowolnych(!)** liczb. Nie zachodzi relacja 'jest',
więc nie jest to dziedziczenie. Jest to mało intuicyjne, bo `Integer` dziedziczy po `Number`.

Zauważ, że do `List<Integer>` możemy dodawać liczby całkowite - tak samo jak w przypadku `List<Number>`.
To co 'blokuje' dziedziczenie, to 'pobieranie' danych. Pobierając dane z `List<Integer>`
spodziewamy się liczby całkowitej, a `List<Number>` może zwrócić dowolną liczbę.

**Gdybyśmy tylko nie musieli odczytywać liczb z tej listy...** (ogranicznie 1, patrz niżej)

Możemy się też zastanowić czy `List<Number>` dziedziczy po `List<Integer>`.
Poniekąd, `List<Number>` rozszerza ('extends') przecież funkcjonalność `List<Integer>` -
może zapisywać różnego typu liczby i je zwracać. Ale ponownie:

Lista **(!)dowolnych(!)** liczb nie jest listą **(!)tylko całkowitych(!)** liczb. Dodając liczbę zmiennoprzecinkową,
dodawalibyśmy ją do listy **(!)tylko całkowitych(!)** liczb.

**Gdybyśmy tylko nie musieli dodawać liczb do tej listy...** (ograniczenie 2, patrz niżej)

#### Typy wieloznaczne (wildcard)

Żeby zapewnić jakiś poziom dziedziczenia - a co za tym idzie, reużywalności kodu - wprowadzono typy wieloznaczne.
Istnieją dwa rodzaje typów wieloznacznych:

1. ograniczone od góry `<? extends TYPE>` (upper bound) i
2. ograniczone od dołu `<? super TYPE>` (lower bound)

gdzie TYPE to dowolny typ w Javie.

##### Typ ograniczony od góry `<? extends TYPE>` (upper bound)

Na przykładzie listy - typ ograniczony od góry `<? extends TYPE>` oznacza,
że mamy do czynienia z listą przechowującą obiekty, które dziedziczą po typie `TYPE`, tzn.
`List<? extends Number>` przechowuje **(!)dowolne(!)** liczby. Nie wiemy jakie to liczby,
ale możemy je **(!)pobierać(!)** i traktować jak **(!)dowolne(!)** liczby - obiekty typu `Number`.
Do takiej listy **nie możemy jednak nic dodawać**.

**Zapamiętaj!**

**Typ `List<? extends Number>` pozwala tylko na odczyt liczb.**

**Typ `List<? extends Number>` oznacza, że możesz bezpiecznie odczytywać obiekty typu Number.**

Zdejmuje to z nas ograniczenie(1) wspomniane wyżej - w związku z tym lista **(!)tylko(!)** liczb
całkowitych `List<Integer>`
jest listą **(!)tylko do odczytu, jakichś (dowolnych)(!)** liczb `List<? extends Number>`,
czyli `List<Integer> extends List<? extends Number>`.

Przykład:

```
    // accepting a list of Numbers and things extending Number - Integers, Doubles, Longs etc..
    private double sumNumbers(List<? extends Number> listOfNumbers)
    {
        double result = 0d;
        for (Number number : listOfNumbers)
        {
            result += number.doubleValue();
        }
        return result;
    }
    
    void someMethod()
    {   
        ...
        List<Number> numbers = ...; // here we're using Number
        double sum = sumNumbers(numbers);
        ...
        List<Integer> integers = ...; // here we're using Integer
        double otherSum = sumNumbers(integers);
        ...
    }
```

##### Typ graniczony od dołu `<? super TYPE>` (lower bound)

Na przykładzie listy - typ ograniczony od dołu `<? super TYPE>` oznacza,
że mamy do czynienia z listą przechowującą obiekty typu `TYPE` lub takie, po których `TYPE` dziedziczy, np.
`List<? super Integer>` oznacza, że lista przechowuje liczby całkowite,
ale może też przechowywać inne liczby (typ `Number`), a także obiekty typ `Object`.
W związku z tym nie możemy odczytać liczb z tej listy (bo nie wiemy czy są tam tylko liczby czy też inne obiekty),
ale możemy bezpiecznie zapisać liczbę całkowitą do tej listy.

**Zapamiętaj!**

**Typ `List<? super Integer>` pozwala tylko na zapis / dostarczenie liczb całkowitych.**

**Typ `List<? super Number>` pozwala tylko na zapis / dostarczenie dowolnych liczb.**

Zdejmuje to z nas ograniczenie(2) wspomniane wyżej - w związku z tym lista **(!)jakichś, dowolnych(!)**
liczb `List<Number>`
jest listą **(!)tylko do zapisu, tylko całkowitych(!)** liczb `List<? super Integer>`,
czyli `List<Number> extends List<? super Integer>`.

Przykład:

```
    // accepting a list of Doubles, Numebrs, Objects.
    void populateWithTemperatureReadingsFromStation(List<? super Double> resultList, Long stationId) {
        List<Double> temperatureReadings = ...;
        resultList.add(temperatureReadings.get(0));
        resultList.add(temperatureReadings.get(1));
        resultList.add(temperatureReadings.get(2));
        ...
    }
    
    void someMethod()
    {   
        ...
        Long someStationId = ...;
        List<Number> temperatureReadingsHolder = ...; // here we're using Number
        populateWithTemperatureReadingsFromStation(temperatureReadingsHolder, someStationId);
        ...
    }
    
    void someOtherMethod()
    {   
        ...
        Long someStationId = ...;
        List<Double> temperatureReadingsHolder = ...; // here we're using Double
        populateWithTemperatureReadingsFromStation(temperatureReadingsHolder, someStationId);
        ...
    }
```

##### Typ wieloznaczny (nieograniczony) (wildcard)

Typ `<?>` jest równożnaczny typowi `<? extends Object>` jest równożnaczny typowi
`<? extends Object>`, czyli jest tak naprawdę typem 'ograniczonym od góry'.
Oznacza to, że nie wiemy co w sobie przechowuje - przechowuje jakiś obiekt.
Z listy takiego typu możemy więc czytać obiekty, nie możemy ich jednak zapisywać.

##### Użycie typów wieloznacznych

Zaleca się używanie typów wieloznacznych przy definiowaniu metod, ew. parametrów klas.
Nie zaleca się zwracania typów wieloznacznych jako wynik wykonywania metody.
Nie zaleca się stosowania ich jako zmiennych czy pól.

##### Ograniczone parametry metod i klas generycznych

Parametry generyczne metod i klas możemy ograniczać od góry - upper bound.
Działanie jest takie samo jak w przypadku typów wieloznacznych, a zapis jest następujący:

`<T extends TYPE>`, np. w sygnaturze metody:

`<T extends Number> T doSomethingWith(T argument) {...}`

[Diagram dziedziczenia](https://docs.oracle.com/javase/tutorial/figures/java/generics-wildcardSubtyping.gif)

[Informacje dodatkowe](https://docs.oracle.com/javase/tutorial/java/generics/inheritance.html)