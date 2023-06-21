## Ćwiczenie 9.

1. Zamień pętle w klasie `CargoLoadedMessageReceiver` na `IntStream` 
i z jego użyciem wygeneruj 10 wiadomości - powinny trafić do konsumera, 
tak jak odbywa się to teraz, zachowaj również odstęp czasowy między wiadomościami.
2. *Zrób to samo dla pozostałych 'odbiorników'.
3. Zapoznaj się ze zmianami w klasie `CargoLoadedMessageContent` (i bliźniaczych klasach) - 
ma ona teraz informację o liście paczek w załadunku.
4. W klasie `StreamsExercise9FlatMap`, dla wiadomości dot. załadunku ('cargoLoaded'),
korzystając z nowego pola dotyczącego listy paczek w załadunku oraz funkcji `flatMap`,
oblicz całkowitą wagę wszystkich ładunków z listy.
5. *Zrób podobną operację sumowania (bez flat map, niepotrzebne), korzysając z metody
`getCargoLoadInKgs` klasy `CargoLoadedMessageContent`. Napraw kod (w klasie `CargoLoadedMessageContent`)
aby wynik się zgadzał.
6. *Wprowadź zmiany w bliźniaczych klasach rodziny `CargoMessageContent`.
