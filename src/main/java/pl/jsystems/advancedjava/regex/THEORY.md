### Wyrażenia regularne

#### Zastosowanie

Znajdowanie łańcuchów znaków pasujących do pewnego wzorca, szablonu.
Np. kody pocztowe mają swój wzór (w każdym kraju inny) - w Polsce jest to 5 cyfr,
z łącznikiem rozdzielającym 2gą i 3cią cyfrę,
np:

`00-231`

`42-124`

`80-210`

itd... możemy porównać łańuch znaków z wyrażeniem regularnym (regex) - `\d\d-\d\d\d` lub `\d{2}-\d{3}`
Trzeba jednak zwrócić uwagę, że nie wszystkie kombinacje istnieją w Polsce - więc tu tylko sprawdzamy format,
a nie poprawność samego kodu!

Innym przykładem mogą być sygnatury dokumentów - np. faktur VAT danej firmy, itd, itp.

Oprócz tego, możemy używać wyrażeń regularnych do wyciągania danych (części testowanego wyrażenia)
z określonego łańcucha znaków.

#### Kiedy nie stosować?

Obsługa wyrażeń regularnych jest dosyć pracochłonna dla procesora. Trzeba zbudować drzewo opisujące wszystkie możliwe
konfiguracje opisane przez dany wzorzec, a kombinacji tych może być bardzo dużo. Np. jaki regex dla daty
w formacie: "dd-mmm-YYYY", "dd/mmm/YYYY" lub "dd.mmm.YYYY"

`^(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]|(?:Jan|Mar|May|Jul|Aug|Oct|Dec)))\1|(?:(?:29|30)(\/|-|\.)(?:0?[1,3-9]|1[0-2]|(?:Jan|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec))\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)(?:0?2|(?:Feb))\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9]|(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep))|(?:1[0-2]|(?:Oct|Nov|Dec)))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$`
[Przykad drzewa ](https://i.stack.imgur.com/D4YDF.png)

Dla formatu "dd-mmm-YYYY" nie wygląda to dużo lepiej:
`^(?:(?:31-(?:0?[13578]|1[02]))\1|(?:(?:29|30)-(?:0?[1,3-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29-(?:0?2|(?:Feb))\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])-(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$`

Dodatkowo przeglądanie dużych łańcuchów, jeżeli nasz wzorzec jest dosyć 'ogólny', może spowodować,
że będziemy znajdować bardzo dużo wyników i 'przeskanowanie' tego łańcucha,
a także zebranie wyników może być kosztowne.

#### Znaki w wyrażeniach regularnych

`.` - dowolny znak poza nową linią. Przykład: `.` pasuje do "a", `.` pasuje do "1", `.` pasuje do "_".

`+` - jedno lub więcej wystąpień poprzedniego znaku. Przykład `.+` pasuje do "1av", `b+` pasuje do "b" i "bbbb", nie
pasuje do aacd

`*` - 0 lub więcej wystąpień poprzedniego znaku. Przykład `.*` pasuje do "" oraz do "123sad34, `a*` pasuje do "aaaa",
ale nie do "bbb"

`{ }` - określona liczba powtórzeń poprzedniej części wyrażenia - patrz niżej

`^` - znak początku testowanego łańcucha (często linii).

`$` - znak końca testowanego łańcucha (często linii).

`[ ]` - klasa zawierająca znaki. Patrz niżej. Przykład `[abc]` pasuje do "a", "b" lub "c", `[ab]+` pasuje do "aaa", "
aba"

`[^ ]` - klasa wykluczająca znaki. Przykład `[abc]` pasuje do "def", "7890", "7890def"

`|` - alternatywa albo znak po lewej stronie pasuje, albo znak po prawej pasuje. Przykład - `1|a` pasuje do "1" i do "a"

`( )` - podzapytanie, 'nazwana grupa'

`(?: )` - podzapytanie, 'nienazwana grupa'

`\` - poprzedza znak specjalny (określający klasę) lub jest znakiem 'ucieczki' dla innych znaków zdefiniowanych wyżej.
Tzn. jeżeli chcemy znaleźć ciąg znaków zawierający tylko "*", trzeba użyć składni `\*`, żeby znak `*` uznawany był za '
zwyczajny' znak, bez dodatkowych funkcji.

*Dodatkowo

`\b` - początek, koniec słowa

`\B` - wszystko co nie jest początkiem, końcem słowa

`\n` - referencja do grupy, "n" to cyfra

#### Predefiniowane klasy (zwróć uwagę na znak '\')

`\d` - jakakolwiek cyfra ('digit', stąd 'd') - inaczej `[0-9]`

`\D` - jakikolwiek znak poza cyfrą - inaczej `[^0-9]`

`\w` - litery, znak używany w 'słowach' ('word', stąd 'w') - inaczej `[a-zA-Z_]`

`\W` - znak nieużywany w 'słowach', zaprzeczenie `\w` - inaczej `[^a-zA-Z_]`

` ` - spacja

`\t` - tab

`\s`- 'białe' znaki ('s' od 'space'), czyli spacje tabulatory itp. - odpowiednik `[ \t\n\r]` lub `\p{Space}`

`\S` - wszystko co nie jest 'białym' znakiem - inaczej `[ \t\n\r\f\x0B]`

`\p{Alnum}` - znaki 'alfanumeryczne', to co `\w`, ale bez "_" - inaczej `[a-zA-Z]`

`\p{Blank}` - spacja lub tab - inaczej `[ \t]`

`\p{Lower}` - małe litery - inaczej `[a-z]`

`\p{Upper}` - duże litery - inaczej `[A-Z]`

#### Zakresy

W ramach grup możemy określać zakresy - tak jak pokazane to było na przykładach wyżej.
Zakresy definiowane są przez pierwszy znak, znak '-', końcowy znak, np.

`[a-c]` oznacza zakres znaków od 'a', 'b' lub 'c'.

#### Grupy

Oznaczone poprzez `[ ]`. Mogą składać się z dowolnej liczby znaków i zakresów. Np.:

`[ABCa-z]` - oznacza, że poszukujemy znaku "A", "B" lub "C", lub dowolnego znaku małej litery.

Możemy łączyć grupy zakresy, np.:

`[a-dA-D]` - to wszystkie litery od "A" do "D" włącznie - małe lub wielkie (alternatywny zapis `[a-d[A-D]]`)

Możemy robić części wspólne zakresów:

`[a-f&&[c-g]]` - małe litery od 'c' do 'f' (alternatywny zapis `[a-f&&[c-g]]`)

Możemy też odejmować zakresy:

`[a-g&&[^c-f]]` - małe litery 'a', 'b', 'g'.

#### Powtórzenia (w przykładach X jest przykładowym wyrażeniem, znakiem, grupą)

`X?` - raz albo wcale

`X*` - 0 lub wiele razy

`X+` - jeden lub wiele razy

`X{n}` - 'n' razy, liczba

`X{n,}` - przynajmniej 'n' razy, liczba

`X{n, m}` - od 'n' do 'm' razy, liczba

### Klasy w Javie

#### Pattern

Do deklarowania wyrażenia regularnego używamy klasy `Pattern`.

`Pattern.compile` - pozwala nam na utworzenie wzorca (drzewa).

`Pattern.matches(String regex, CharSequence input)` - pozwala na szybkie sprawdzenie czy ciąg pasuje do wzorca.
Odpowiednik w `String.matches(String regex)`. (!) - jeżeli wielokrotnie sprawdzamy różne teksty z tym samym wzorcem,
lepiej najpierw go skompilować!

`pattern.split(String regex)` - pozwala na rozbicie ciągu znaków na części przy wykorzystaniu wyrażenia - np.
`split("-")` - przy ciągu wejściowym "a-b-c" da nam tablicę z wynikami "a", "b" i "c".
Odpowiednik w `String.split(String regex)`.

`pattern.matcher()` - zwraca nam matcher, czyli obiekt który ma informację o pasujących / znalezionych wynikach.

#### Matcher (najczęsciej używane)

`find()` - znajdź kolejne wystąpienie wzorca w wejściowym ciągu danych

`matches()` - czy cały(!) ciąg pasuje do wzorca.

`replaceAll(String replacement)` - zamień wszystkie znalezione wystąpienia podanym stringiem ('replacement'),
odpowiednik w `String.replaceAll(String replacement)`

`replaceFirst(String replacement` - zamień pierwsze znalezione wystąpienie podanym stringiem ('replacement')