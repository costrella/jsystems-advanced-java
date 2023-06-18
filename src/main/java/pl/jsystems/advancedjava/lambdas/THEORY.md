## Wyrażenia Lambda

### Definicja, lambda a klasy anonimowe
Lambda to wyrażenie skrótowo definiujące anonimową klasę, 
implementującą interfejs o (!)tylko(!) jednej abstrakcyjnej metodzie.
(Niewliczane są metody takie same jak zdefiniowane w klasie Object, 
bo one i tak będą zaimplementowane).

Np. interfejs `Consumer<T>`, ma jedną abstrakcyjną metodę `accept(T t)`. 
Może on być zaimplementowany przez anonimową klasę w następujący sposób:
```
Consumer<String> consumer = new Consumer<String>()
{
    @Override
    public void accept(String input)
    {
        System.out.println("New input: " + input);
    }
};
```
Taka implementacja zajmuje dużo miejsca, większość kodu definiuje strukturę, a nie funkcjonalność.
Skrótowa forma wyrażenia lambda, pozwala na zdefiniowanie jedynie części realizującej daną operację ('funkcję').
Np.:
```
Consumer<String> consumer = input -> System.out.println("New input: " + input);
```

### Składnia
1. Argumenty wejściowe, wylistowane po przecinku, otoczone nawiasami `()`. 
W przypadku jednego argumentu nawiasy można pominą. Argumenty (wszystkie albo żaden) mogą być poprzedzone ich typami.
Jest to jednak niepotrzebne, bo typ można wywnioskować z kontekstu.
2. Strzałka w postaci `->`,
3. Wyrażenie lub blok kodu ograniczony nawiasami `{}`. Zarówno wyrażenie jak i blok mogą coś zwracać, 
z tym że w przypadku bloku kodu wymagane jest słowo kluczowe `return`, 
a w przypadku wyrażenia nie powinno się go używać - wynik wyrażenia będzie wynikiem wyrażenia lambda.

### Wyjątki
Z wyrażeń lambda można wyrzucać wyjątki tak jak z klas abstrakcyjnych i nazwanych.
Jeżeli lambda ma wyrzucić wyjątek 'checked' - informacja o tym musi się znajdować w interfejsie, 
który to wyrażenie lambda implementuje.

### Adnotacja @FunctionalInterface
Adnotacją `@FunctionalInterface` może być oznaczony dowolny interfejs z tylko jedną metodą abstrakcyjną.
Ta adnotacja służy jako informacja, że dany interfejs został stworzony z zamiarem używania go z wyrażeniami lambda.
Widząc tę adnotację, programista nie powinien więc dodawać kolejnych abstrakcyjnych metod do interfejsu,
w takim wypadku kompilator zgłosi bowiem błąd.

### Zakres zmiennych
Wyrażenia lambda mają własny zakres zmiennych, 
ale mogą też korzystać ze zmiennych z wyższego zakresu, kodu z którego są wywoływane.
Zmienne używane w wyrażeniach lambda muszą być jednak stałe (effectively final),
tzn. nie można ich nadpisywać (ale nie muszą być poprzedzone słowem `final`).

### Typy
Dwa identycznie zdefiniowane wyrażenia lambda mogą być przypisane do zmiennych o różnych typach, 
jeżeli typy te (interfejsy) definiują metody o sygnaturach pasujących do danego wyrażenia.

Np.:
```
Consumer<String> consumer1 = input -> {};
MyConsumer<String> consumer2 = input -> {};
```
Typy parametrów wejściowych i zwracanych wyrażenia lambda są 'odgadywane' przez kompilator na podstawie definicji metody, 
którą implementują oraz zmiennej do której wyrażenie jest przypisywane.

Np.:
```
MyConsumer<List<String>> myConsumer = input -> LOGGER.info("Got something: {}", input);
```
Kompilator wie, że `input` jest zmienną typu `List<String>` na podstawie typu zmiennej `myConsumer`;

### Method reference
Jeżeli jedyne co wyrażenie lambda robi to wywołanie metody (w tym konstruktora), 
to możliwe jest zastąpienie go poprzez referencję do metody.

Przykład 1:
```
Consumer<String> printer = input -> System.out.println(input);
```
czyli inaczej:
```
Consumer<String> printer = System.out::println;
```
oznacza, że parametr wejściowy typu `String` zostanie przekazany do statycznej metody `System.out.println`.

Przykład 2:
```
Function<String, Integer> lengthCalculator = input -> input.length;
```
czyli inaczej:
```
Function<String, Integer> lengthCalculator = String::length;
```
oznacza, że na parametrze wejściowym typu `String`, zostanie wywołana metoda `length` i zwrócony zostanie jej wynik.

Przykład 3:
```
Function<String, List<String>> singleElementList = input -> List.of(input);
```
czyli inaczej:
```
Function<String, List<String>> singleElementList = List::of;
```
oznacza, że na parametrze wejściowym zostanie wywołana statyczna metoda `List.of` i zwrócony będzie jej wynik.

Przykład 4:
```
Function<Integer, List<String>> emptyList = input -> new ArrayList(input);
```
czyli inaczej:
```
Function<Integer, List<String>> emptyList = ArrayList::new;
```
oznacza, że utworzona zostanie nowa lista o rozmiarze podanym jako parametr wejściowy wyrażenia lambda, 
a lista zostanie zwrócona jako wynik wyrażenia lambda.

[Informacje dodatkowe](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
