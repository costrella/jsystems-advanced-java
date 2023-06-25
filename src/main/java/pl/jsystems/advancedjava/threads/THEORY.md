## Wielowątkowość

### Model pamięci

Model pamięci w Javie jest skomplikowanym zagadnieniem. W skrócie,
wirtualna maszyna może - w celu optymalizacji - zmieniać kolejność wykonywania operacji.
Oczywiście zmiana ta nie może zmieniać logiki działania programu, ale następująca zmiana - z:

```
value = 0;
...
...
value = 42;
isSet = true;
```

na:

```
value = 0;
...
...
isSet = true;
value = 42;
```

jest możliwa. W przypadku jednego wątku nie ma to znaczenia, w przypadku wielu -
jakiś wątek może odczytać flagę `isSet` a potem wartość `value`
i otrzymać wynik `0` zamiast `42`.
Żeby temu zapobiec potrzebna jest synchronizacja między wątkami.

Innym problemem jest to, że każdy z procesorów / rdzeni ma swoją pamięć podręczną,
która nie jest na bieżąco synchronizowana z główną pamięcią aplikacji.

Przez to, dwa wątki pracujące na innych rdzeniach mogą widzieć różny stan tej samej zmiennej.
Ponownie - potrzebna jest synchronizacja wątków by zapobiec takim sytuacjom.

### Synchronizacja stanu

#### Volatile

Słowo kluczowe `volatile` można ustawić na polu klasy. Za każdym razem,
gdy pole będzie odczytywane wirtualna maszyna zapewni, że wartość pola jest zsynchronizowana -
tzn. taka sama dla wszystkich wątków. Zmienna ta spowoduje też,
że zmiany kolejności wykonywania operacji nie będą widocze przy odczycie tego pola - tzn. kolejność będzie zachowana.

#### Operacje atomowe

Słowo kluczowe jest pomocne przy odczytywaniu wartości, ale nie przy próbie jednoczesnej jej zmiany.
Operacja:

```
i++
```

To tak naprawdę odczyt wartości ze zmiennej, dodanie 1 do wartości, zapis wartości do zmiennej.
Choć odczyt będzie zsynchronizowany z innymi wątkami,
to zapis nie musi być - tzn. zanim nowa wartość zostanie zapisana do zmiennej,
inny wątek może tę wartość wcześniej zapisać - co spowoduje błąd.
Jeżeli dwa wątki w tym samym czasie wykonają tę operację, np. dla wartości początkowej `42`,
to każdy będzie chciał podnieść wartość do `43`. Jeżeli zdarzy się, że oba wątki odczytają wartość `42`
dokładnie w tym samym momencie, to obie przypiszą wartość `43` - a ostatecznie wartość powinna wynosić `44`.

'Klasy atomowe' - np. `AtomicInteger`, pozwalają na wykonywanie 'atomowych' operacji na zmiennych.
Np. `AtomicInteger` ma metodę `incrementAndGet` - która powoduje zmianę wartości i jej pobranie.
Klasa zapewnia, że dwa konkurujące wątki nie nadpiszą sobie wartości przy zmianie.
Implementacja tych klas jest nieblokująca, opierająca się na funkcji procesora - compare and swap.

[Paczka java.util.concurrent.atomic - API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/atomic/package-summary.html)

#### Bloki / metody synchronized

W takich blokach może się w jednym momencie znajdować tylko 1 wątek.
Dodatkowo, pewne jest, że jeżeli jeden wątek opuści taki blok, a kolejny do niego wejdzie,
to stan między tymi dwoma wątkami będzie zsynchronizowany, kolejność operacji (sprzed wejścia) zachowana.

Każdy blok synchronized strzeżony jest przez jakiś obiekt - tzw. monitor.
Ważne jest by zawsze używać tego samego obiektu, inaczej blok nie będzie poprawnie synchronizował wątków,
każdy wątek będzie monitorowany przez inny monitor i synchronizacja nie będzie zapewniona.

Mówi się, że wątek, który wszedł do bloku `synchronized` posiada prawo do danego monitora.
Opuszczając blok w sposób naturalny, bądź wywołanie metody `wait()` wątek oddaje prawo do monitora.

Można wielokrotnie wchodzić do bloków strzeżonych przez ten sam monitor (zagnieżdżenie).

#### Klasy Concurrent... CopyOnWrite... (np. ConcurrentHashMap)

Istnieją klasy - np. `ConcurrentHashMap` czy `CopyOnWriteArrayList`, które zapewniają,
że ich zawartość będzie zsynchronizowana między wątkami `thread safe`.
Warto korzystać z takich klas, gdy mają do nich dostęp różne wątki.
Należy zapoznać się z dokumentacją każdej z nich, gdyż ich użycie może mieć wpływ na wydajność,
np. `CopyOnWriteArrayList` przy każdym zapisie kopie listę.

[Paczka java.util.concurrent - API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/package-summary.html)

### Synchronizacja wątków

Operacje współbieżne często muszą się ze sobą komunikować. Aby w tym pomóc Java udostępnia szereg narzędzi.

#### join()

Metoda join wywołana w wątku A na obiekcie wątku B spowoduje, 
że wątek A będzie czekał na to aż wątek B zakończy pracę.
Jest to najbardziej podstawowa metoda synchronizacji wątków.

#### wait(), notify(), notifyAll()

Innymi podstawowymi narzędziami są metody `wait()`, `notify()` i `notifyAll()`.
Mogą one być wywoływane tylko i wyłącznie ze synchronizowanego bloku `synchronized` (patrz wyżej).
Metody te wywołuje się na obiekcie monitora - nigdy innym.
Metoda `wait` spowoduje, że wątek chwilowo 'opuści' blok synchronizowany i odda prawa do monitora innym wątkom.
Jeżeli inny wątek wejdzie do bloku `synchronized` i wywoła metodę `notify()` lub `notifyAll()`
po opuszczeniu bloku synchronized wzbudzony zostanie DOWOLNY wątek oczekujący
na dostęp (albo z await, albo z zewnątrz bloku).

#### Blokady, kolejki, semafory i inne

Java udostępnia wiele klas służących synchronizacji wątków - w zależności od potrzeb można wybrać odpowiednie narzędzie:

[Paczka java.util.concurrent - API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/package-summary.html)
[Paczka java.util.concurrent.locks - API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/locks/package-summary.html)

Klasy z tych paczek obudowują podstawowe narzędzie - takie jak bloki synchronizowalne, zmienne `volatile`, 
udostępniając bardziej wysokopoziomowe / łatwiejsze w obsłudze narzędzia.

#### Interrupted exception

Wyjątek rodzaju `checked`, wyrzucany przez wątek wtedy, gdy ktoś chce mu przeszkodzić wątkowi, zapewne zakończyć jego prace.
Aby przedzkodzić wątkowi można na obiekcie go reprezentującym wywołać metodę `interrupt`.
Taki wątek będzie miał wtedy flagę `interrupted` ustawioną na `true`.

Gdy wirtualna maszyna będzie sprawdzać stan wątku i zobaczy taką flagę - 
wyczyści ją i wyrzuci wyjatek `InterruptedException`. Taki wyjątek nie jest wyrzucany 
w przypadku oczekiwania na wejście do bloku synchronized (kto miałby go wyrzucić, złapać?)

Wątek który przechwyci taki wyjątek powinien założyć (oczywiście to zależy), że ktoś przeszkodził innemu wątkowi. 
Powinien sam siebie oznaczyc jako `Interrupted` i wyrzucić wyjątek `Runtime`, aby przerwać działanie.

### Executors / ExecutorService / Executor / Future
Są to klasy ułatwiające tworzenie puli wątków. Dzięki nim można utworzyć, zamknąć wątki.
Można dodawać nowe wywołania do puli wątków nie martwiąc się, że ich zabraknie.
Można planować zadania na przyszłość (`ScheduledExecutorService`), można odczytywać wartości
które będą zwrócone w przyszłości - klasa `Future`.
