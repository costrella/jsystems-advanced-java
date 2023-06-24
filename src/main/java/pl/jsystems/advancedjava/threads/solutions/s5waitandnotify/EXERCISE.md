## Ćwiczenie 5*.

1. Zmień obsługę 'konsumera' w metodzie `receiveAndStore`,
   tak by korzystała z długożyjącego wątku, powiadamianego o nowej wiadomości.
   W tym celu dodaj klasę dziedziczącą po `Thread` oraz klasę,
   której obiekt będzie służył za 'monitor'/'powiadamiacz' posiadający stan oczekujących powiadomień.
2. Nowa klasa wątku powinna w konstruktorze przyjmować
   obiekt typu `Consumer<Message<CargoLoadedMessageContent>>`.
   Konsumer ten powinien mieć dokładnie takie zachowanie jak obecnie istniejący.
3. Teraz, 'konsumer' w metodzie `receiveAndStore`, ten przekazywany do 'odbiornika',
   powinien tylko i wyłącznie wywoływać metodę na 'monitorze'.
4. *Czekanie na zakończenie wątku 'odbiornika' nie ma teraz dużo sensu, bo jego wiadomości
   będą przetwarzane jeszcze po tym jak on zakończy działanie - usuń tę część kodu.
5. *Zamiast powyższego, w metodzie `receiveAndStore` dodaj 10s oczekiwania i potem 
    wzbudź (`interrupt`) utworzone wątki - aplikacja powinna się zatrzymać.