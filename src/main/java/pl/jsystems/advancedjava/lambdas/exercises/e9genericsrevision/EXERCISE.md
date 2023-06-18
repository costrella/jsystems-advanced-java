## Ćwiczenie 9.

1. W obecnym stanie kodu, typ predykatu i typ funkcji wyciągającej treść wiadomości są ze sobą niezwiązane.
   Powoduje to, wraz z rzutowaniem, kompilator nie jest w stanie wychwycić dla nas wszystkich błędów. 
2. Spróbujmy to poprawić poprzez zmianę klasy `MessageDetailsExtractor` tak,
   aby była parametryzowalna typem ograniczonym od góry `MessageContent`.
3. Zastąp pole z 'predykatem' polem z klasą `Class` (`private final Class<T> contentType`).
4. Zmień parametr funckcji z `MessageContent` na `T`.
   Popraw konstruktor i jego wywołania.
5. Zastąp warunek do tej pory realizowany przez predykat sprawdzeniem czy
   przekazana treść jest tego samego typu co oczekiwana - `contentType.isAssignableFrom(content.getClass())`.
6. Gdzie teraz powinno znaleźć się rzutowanie? Czy teraz kompilator pilnuje poprawności typów?
