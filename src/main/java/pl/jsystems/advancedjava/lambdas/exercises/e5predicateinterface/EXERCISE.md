## Ćwiczenie 5.

1. W metodzie `logReceived` klasy `MessageLogger` zamień wyrażenia warunkowe tak, aby korzystały z predykatów.
   Zdefiniuj predykaty jako stałe (pola `static final`) i zainicjalizuj je poprzez wyrażenia lambda.
2. *W metodzie `logReceived` klasy `MessageLogger` zastąp wywołania metod logujących
   (`logReceivedVehicleMessage` i `logReceivedCargoMessage`), tak aby realizowane były z użyciem interfejsu konsumer.
   Zdefiniuj 'konsumentów' jako stałe, użyj wyrażeń lambda.
   Będziesz musiała / musiał zmienić powyższe metody na metody statyczne.