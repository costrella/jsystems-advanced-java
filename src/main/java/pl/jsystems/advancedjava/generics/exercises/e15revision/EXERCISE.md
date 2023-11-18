## Ćwiczenie 15.

1. Zapoznaj się ze kodem w aktualnej postaci.
   Wszystkie klasy związane z treścią zostały przeniesione do paczki `contents`.
   Klasa `Message` i `MessageCreator` (z ćwiczenia 3) zostały przeniesione do paczki `message`.
   W klasie `GenericsExercise15LogisticsApp` jest zakomentowany kod, który się nie kompiluje bo brakuje kilku klas.
2. Dodaj brakujące klasy / interfejsy:
    * MessageReceiver - z metodą `getLatestReceivedMessage()` zwracającą obiekt generycznego typu `Message`;
    * MessageRepository - z metodami:
        * `findAll()` - zwracającą listę wiadomości,
        * `save(Message<T> message)` - typu `void` zapisującą wiadomość,
        * `findById(UUID id)` - zwracającą wiadomość o danym id.

Oba interfejsy powinny być generyczne i obsługiwać wiadomości o zawratości typu i podtypów (`MessageContent`).

3. Dodaj bardziej / *mniej naiwną implementację powyższych interfejsów dla treści typu:
   `GPSTrackingMessageContent` - bardziej naiwna powinna pozwalać na kompilację odkomentowanego kodu,
   mniej naiwna implementacja powinna zwracać dobre wyniki przyuruchomieniu.
   Do tworzenia wiadomości możesz skorzystać z klasy `MessageCreator` i poniższego kodu:
   `return messageCreator.createMessageUsing(new GPSTrackingMessageContent());`