# Network Analyzer

Aplikacja Network Analyzer służy do monitorowania ruchu sieciowego i przedstawiania w wygodnej formie wykresów 
informacje o aktywności użytkownika w sieci

# Funkcjonalność

Dzięki aplikacji możemy uzyskać następujące informacje
- stosunek procentowy ilości pobieranych danych do wysyłanych z ostatniej doby
- ilość danych pobranych i odebranych dla każdej godziny z ostatniej doby
- dla wybranych portali internetowych ilość danych pobranych, a także wysłanych przez użytkownika w ciągu ostatniej godziny
- aktywność użytkownika na portalu Wykop w ciągu ostatniej godziny
- maksymalny nieprzerwany czas spędzony na portalu YouTube w ciągu ostatniej doby
- ilość minut spędzonych na portalu YouTube w poszczególnych godzinach

Aplikacja umożliwia także wysyłanie maili, jeśli ilość danych pobranych przez użytkownika przekroczy zadany próg.
# Technologie i użyte narzędzia

Aplikacja korzysta z takich narzędzi jak:
* IPAC-NG
* Redash
* PostgreSQL


Ruch sieciowy jest symulowany przy pomocy gotowego narzędzia tzw. WebCrawler.

### IPAC-NG
IPAC-NG narzędzie linuksowe pozwala na zdefiniowanie tablicy adresów, na których ma być wykrywany ruch użytkownika.
Umożliwia tworzenie reguł dotyczących zarówno ruchu wysyłanego jak i odbieranego. Narzędzie może zapisywać dane 
w pliku lub bazie danych PostgrSQL 


### Redash
Redash to rozwiązanie open-source służące do analizowania i wizualizacji danych. Jest bardzo przydatnym narzedziem i wspiera
wiele baz danych takich jak m.in: PostgreSQL, MySQL, Redshift, BigQuery, MongoDB. Narzędzie posiada wiele ciekawych możliwości
wizualizacji danych w zależności od potrzeb użytkownika. Ponadto redash pozwala zdefiniować alerty, które są bardzo 
użyteczną funkcją w przypadku aplikacji monitorujących.

### PostgreSQL
PostgreSQL jeden z trzech najpopularniejszych otwartych relacyjnych systemów bazodanowych.


