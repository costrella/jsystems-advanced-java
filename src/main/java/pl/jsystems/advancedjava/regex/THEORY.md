### Wyrażenia regularne

#### Zastosowanie

Znajdowanie łańcuchów znaków pasujących do pewnego wzorca, szablonu.
Np. kody pocztowe mają swój wzór (w każdym kraju inny) - w Polsce jest to 5 cyfr,
z łącznikiem rozdzielającym 2gą i 3cią cyfrę,
np:

`00-231`

`42-124`

`80-210`

itd... Ciężko byłoby za każdym razem gdy chcemy sprawdzić czy dany łańcuch tekstowy jest kodem pocztowym,
porównywać go z każdym jednym kodem. Ciężko to zrobić, zamiast tego możemy prównać łańuch znaków 
z wyrażeniem regularnym (regex) - "\d\d-\d\d\d".

Podobne problem możemy mieć z sygnaturami dokumentów - np. faktur VAT danej firmy.


#### Znaki w wyrażeniach regularnych

`.` - dowolny znak poza nową linią. Przykład: `.` pasuje do "a", `.` pasuje do "1", `.` pasuje do "_".

`+` - jedno lub więcej wystąpień poprzedniego znaku. Przykład `.+` pasuje do "1av", `b+` pasuje do "b" i "bbbb", nie
pasuje do aacd

`*` - 0 lub więcej wystąpień poprzedniego znaku. Przykład `.*` pasuje do "" oraz do "123sad34, `a*` pasuje do "aaaa",
ale nie do "bbb"

`{m,n}` - od m do n (włącznie z obu stron) wystąpień poprzedniego znaku. Przykład `a{1,3}` pasuje do "a", "aa", "aaa"

`^` - znak początku testowanego łańcucha (często linii).

`$` - znak końca testowanego łańcucha (często linii).

`[ ]` - klasa zawierająca znaki. Przykład `[abc]` pasuje do "a", "b" lub "c", `[ab]+` pasuje do "aaa", "aba"

`[^ ]` - klasa wykluczająca znaki. Przykład `[abc]` pasuje do "def", "7890", "7890def"

`|` - alternatywa albo znak po lewej stronie pasuje, albo znak po prawej pasuje. Przykład - `1|a` pasuje do "1" i do "a"

`( )` - podzapytanie, 'nazwana grupa'

`(?: )` - podzapytanie, 'nienazwana grupa'

`\` - poprzedza znak specjalny (określający klasę) lub jest znakiem 'ucieczki' dla innych znaków zdefiniowanych wyżej.
Tzn. jeżeli chcemy znaleźć ciąg znaków zawierający tylko "*", trzeba użyć składni `\*`, żeby znak `*` uznawany był za '
zwyczajny' znak, bez dodatkowych funkcji.

