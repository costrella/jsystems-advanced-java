W przykładzie Pawła, mamy serię 'ekstraktorów' logów (obiektów), 
z których każdy potrafi obsłużyć inny typ treści wiadomości - 
inny, ale konkretny jeden.
Nie możemy więc do 'ekstraktora', który potrafi obsługiwać np. 
'CargoLoadedMessageContent' przekazać dowolnej wiadomości (treści).

`private void extractInto(List<String> messageDetails, R content)`
przyjmuje konkretny typ (np. CargoLoadedMessageContent), 
a my chcemy przekazać potencjalnie dowolny typ, np. CargoUnloadedMessageContent.
Kompilator nie zgadza się na to (i słusznie). Tak naprawdę, mamy tylko 1 możliwość
- zmianę sygnatury metody z:

`private void extractInto(List<String> messageDetails, R content)`

na 

`private void extractInto(List<String> messageDetails, MessageContent content)`.

Powoduje to jednak problemy z predykatem, w którym możemy jedank zmienić parametr generyczny na `MessageContent`.
Wtedy mamy tylko 1 rzutowanie i to w miarę bezpiecznym miejscu.

Proszę sprawdź klasę `Solution1` w celu uzyskania szczegółów.

Problemem w klasie `Solution1` jest to, że kod zakłada, iż ten kto go będzie wywoływał
nie popełni błędu / nie będzie chciał nic zepsuć przekazując do konstruktora parametry 
predicate i 'ekstraktora'. Można bowiem przekazać predykat niepasujący do ekstraktora.

Rozwiązanie w kodzie - spójrzmy na klasę:
`pl.jsystems.advancedjava.lambdas.solutions.s9genericsrevision.MessageLogger` (oraz w późniejszych jej wersjach)


Rozwiązania `Solution2`, `Solution3` oraz `Solution4` bazują na wzorcu:
"typesafe hetereogeneous container" opisanym w `Effective Java`.
`Solution2` wydaje się względnie skomplikowane, więc sugerowałbym od przejścia do `Solution3` 
a potem powrotu do `Solution2`. 
Zamysł jest jeden - przechowywanie predykatów (czy ogólnie warunków) w kluczu mapy, a oczekiwanego 'zachowania' w wartości mapy.
`Solution4` jest kontynuacją, dalszym uproszczeniem kodu z `Solution3`. 

`Solution2` jest nieco bardziej 'ogólnym' rozwiązaniem, bo możemy mieć dowolne predykaty.
W `Solution3` i `Solution4` naszym predykatem jest głównie sprawdzenie klasy treści wiadomości.

W zależności od potrzeb - możemy użyć jednego z nich. Każde z tych rozwiązań daje nam jednak wielki benefit.
Klasa `MessageLogger` staje się rozszerzalna. Możemy rejestrować dowolne nowe 'ekstraktory' (przykład w `solution4`)
