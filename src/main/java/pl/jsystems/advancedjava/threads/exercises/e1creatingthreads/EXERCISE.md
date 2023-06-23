## Ćwiczenie 1.

1. W klasach odbiorników (paczka 'receivers'), zmień metodę `startReceivingUsing`, tak aby używały nowych wątków,
tzn. na początku metody powinien być tworzony 1 nowy wątek, w którym realizowana będzie cała 'logika' funkcji.
Poza tym, zachowanie metod nie powinno się zmienic - wciąż chcemy generować tylko 10 wiadomości każdego typu.
2. *Spróbuj stworzyć wątki na różne sposoby.
3. W klasie `ThreadsExercise1CreatingThreads` są logi informujące o tym ile trwa 'odbieranie' wiadomości,
oraz o liczbie wiadomości. Porównaj wyniki przed i po wprowadzeniu zmian. Czy logi wyświetlają to co 'powinny'?