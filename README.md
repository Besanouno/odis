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

Aplikacja umożliwia także wysyłanie maili(alerty), jeśli ilość danych pobranych przez użytkownika przekroczy zadany próg.
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

# Instalacja i konfiguracja
Systemem operacyjnym na którym zostało zainstalowane oprogramowanie jest Ubuntu 18.10. 

Na początku należy zaktualizować nasz system przed instalacją jakichkolwiek paczek:
```bash
sudo apt update
sudo apt upgrade
sudo reboot
```

Instalacja oprogramowania Docker, potrzebnego do uruchomienia Redasha:
```bash
sudo apt-get update 
sudo apt-get -yy install apt-transport-https ca-certificates curl software-properties-common wget pwgen
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
sudo apt-get update && sudo apt-get -y install docker-ce
```

Instalacja Docker Compose:
```bash
export VER="1.22.0"
sudo curl -L https://github.com/docker/compose/releases/download/${VER}/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose
```

Nadanie praw pliku wykonywalnego:
```bash
sudo chmod +x /usr/local/bin/docker-compose
```

Pozwolenie obecnemu użytkownikowi uruchamiania komend Dockera:
```bash
sudo usermod -aG docker $USER
newgrp docker
```

### IPAC-NG
```bash
./ipac-ng/configure --enable-default-storage=postgre
make
make install
mkdir /etc/ipac-ng
direct=$(pwd)
cp conf/ipac.conf /etc/ipac-ng
cp conf/rules.conf /etc/rules.conf
./ipac-ng/fetchipac -Sv
./ipac-ng/ipacsum -t today
```

Następnie należy wykonać polecenie:
```bash
echo "*/4 * * * * root ${direct}/ipac-ng/fetchipac" >> /etc/crontab
```
które to doda wpis do pliku **/etc/crontab** i będzie ono odpowiadało za odpalanie fetchipac co 4 minuty w celu zapisania danych z ipac-ng do bazy PostgreSQL.

### Redash
Instalację Redasha można przeprowadzić w sposób automatyczny poprzez pobranie gotowego skryptu, którego uruchomienie wszystko za nas zrobi lub manulanie(instalacja krok po kroku).
Poniżej przedstawiona została opcja automatyczna:
```bash
https://raw.githubusercontent.com/getredash/redash/master/setup/setup.sh
chmod +x setup.sh
./setup.sh
```

### PostgreSQL
Dla zainstalowanej wcześniej lokalnie bazie PostgreSQL należy dodać wpis:
```bash
host    all             all             0.0.0.0/0               md5
```
do pliku konfiguracyjnego **pg_hba.conf**, w celu umożliwenia połączenia się uruchomionego Redasha w Dockerze z bazą danych która jest lokalnie.

Na sam koniec należy wykonać polecenie:
```bash
service postgresql restart
```


