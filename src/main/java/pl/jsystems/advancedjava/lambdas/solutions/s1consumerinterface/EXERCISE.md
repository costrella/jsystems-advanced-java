## Ćwiczenie 1.
1. Zapoznaj się ze kodem w aktualnej postaci.
2. W interfejsie `MessageReceiver` (i jego implementacji), zmień sygnaturę metody `getLatestReceivedMessage`,
tak aby nic nie zwracała, ale za to przyjmowała argument typu `Consumer<Message<T>>`.
3. Zmień nazwę metody na `startReceivingUsing`.
4. Zmień zachowanie metody tak, by wykorzystywało konsumera wiadomości.
5. Dodaj nową klasę (może być wewnętrzna), implementującą interfejs `Consumer`, 
tak aby była w stanie akceptować wiadomości typu `GPSTrackingMessageContent`.
6. Przenieś kod obsługujący wiadomość z metody `run` w klasie `LambdasExercise1MessageConsumer`
do nowego konsumera.
7. Wykorzystaj nowo utworzoną klasę do obsługi wiadomości.
8. *Czy nowa klasa konsumera może być generyczna?
