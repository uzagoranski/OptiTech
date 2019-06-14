<p align="center">
  <img src="http://optitech.sydneyitsolutions.com/wp-content/uploads/2019/03/logo640.png">
</p>

<h1 align="center">
Spletna platforma za pregled in upravljanje podatkov vozil testne flote podjetja Kivi
</h1>

  [POVEZAVA DO PREDSTAVITVENEGA VIDEA](https://vimeo.com/341977414)

### Predpogoji

IntelliJ IDEA,
Java,
MSSQL Server,
SQL Server Management Studio (SSMS),
Maven,
Spring Boot
```
IntelliJ IDEA 2019.1.2,
Java 1.8,
MSSQL Server 2016+,
SQL Server Management Studio (SSMS) 18.0,
Maven 4.0.0,
Spring Boot 2.1.4
```

## O projektu

### Ekosistem podatkov in upravljalcev

OptiTech je "connected vehicle" platforma za obdelavo in upravljanje različnih podatkov iz različnih vozil v povezavi z različnimi internimi in eksternimi sistemi ter uporabniki. 

Ključna prednost platforme je, da upošteva in nadgrajuje zahteve različnih uporabnikov (vozniki, servisne hiše, zavarovalnice, proizvajalci avtomobilov, itd) ter zagotavlja vedno boljšo uporabniško izkušnjo in posledično tudi lažje upravljanje z vozilom za vse deležnike ekosistema.

### Platforma

Platformo sestavlajo: 

- OBD II "plug & play" naprava v vozilu (Onboard Diagnostic connector), ki je povezana z internetom in sproti zajema podatke o vožnji in vozilu ter jih posreduje v procesni center,
- komunikacijski center oz komunikacijska naprava s procesnim centrom, ki skrbi za povezavo med napravo v vozilu in bazo naprav, uporabnikov in podatkov, 
- analitični center, ki obdeluje in pripravlja podatke za potrebe različnih skupin uporabnikov ekosistema in 
- portal OptiTech, ki omogoča preglede podatkov in upravljanje vseh delov sistema.

### Uporabniški vmesnik

Rok za izdelavo projekta je izjemno kratek (5 tednov), zaradi česar si ne moremo privoščiti izgube časa z vidika samostojnega programiranja uporabniškega vmesnika. Iz tega razloga smo prebrskali med najboljšimi brezplačnimi predlogami, ki so ustrezale našemu konceptu in tipu projekta in izbrali temo [CoolAdmin](https://colorlib.com/polygon/cooladmin/index.html). Ta je seveda prilagojena in prirejena našim potrebam, zatorej je popolna uporabniška izkušnja zagotovljena.

<p align="center">
  <img src="https://i.imgur.com/RQM0Xhh.png">
</p>

### Prijava in registracija

Zaradi boljše uporabniške izkušnje je platforma kar se da izpopolnjena za čim lažjo uporabo in k temu spada tudi prijava v sistem. Celotna varnost spletne strani je implementirana preko kombinacije Spring Security-ja in OAuth 2.0 protokola. Neprijavljen uporabnik lahko tako dostopa samo do pozdravne strani z osnovnimi informacijami, prijavljen uporabnik pa lahko brska po podrobnejših podatkih. 

Celotna platforma je implementirana tako, da deluje podobno kot vsi večji SSO (single sign-on) sistemi. V primeru neregistriranega uporabnika se registracija izvede praktično ob prvi prijavi in se podatki shranijo v Google-ovo shrambo podatkov, za že prijavljene uporabnike pa se izvede samo klasična avtorizacija.

## Arhitektura

Tekom celotnega projekta je uporabljena klasična MVC arhitektura, zgrajena s pomočjo Spring Boot ogrodja. Natančnejši opis posameznih komponent: 
* MODEL:  javanski razredi za dostop do entitet v SQL Server-ju 
* VIEW: prejem informacij in gradnja uporabniškega vmesnika v obliki .jsp strani, souporaba anotacij JSTL
* CONTROLLER: klasični Spring kontrolerji (nadzorniki) z anotacijami in mappingi 

Prednosti MVC arhitekture:
* Modularnost
* Fleksibilnost
* Hitrejši razvoj
* Lažje vzdrževanje

<p align="center">
  <img src="https://cdn-images-1.medium.com/max/1080/0*Qf1s2lG86MjX-Zcv.jpg">
</p>

Podporne tehnologije, uporabljene v projektu: Bootstrap, jQuery, AJAX, JavaScript, SASS

Kot dodatek se pri statistiki in javljanju napak posameznega vozila uporabljajo metode umetne inteligence. Iz širokega nabora podatkov smo izbrskali vzorec in po vnosu podatkov uporabnika lahko do 99% natančno določimo čas in tip napake, ki se lahko pojavi.

## Metode dela

#### Kanban

Kanban je metoda za upravljanje in organizacijo dela, s poudarkom na vmesnih rezultatih in usklajenemu delu vseh vpletenih. Najsi to metodo uporabljal le en posameznik ali velika skupina ljudi, vsem so vidne vse naloge, celoten potek in zaključek. Iz tega razloga za sledenje dela in nalog uporabljamo "Projects" segment, ki je ponujen na GitHubu. Za pregled nad projektom OptiTech kliknite naslednjo [povezavo](https://github.com/uzagoranski/Praktikum_OptiTech/projects/1).

#### Iterativen pristop

Za implementacijo posameznih komponent platforme se poslužujemo iterativnega pristopa dela. S tem pridobimo pregled nad delom, omogočimo lažjo organizacijo dela in boljše sodelovanje med člani ekipe. Posamezna iteracija traja 1 teden, v času le-te pa se izvede implementacija dodeljenih funkcionalnosti.

#### Inkrementalen pristop

Na žalost v posamezni iteraciji vsem ne uspe vedno zaključiti in izpopolniti dodeljene naloge. Zaradi tega je v igri tudi inkrementalen pristop dela, v katerem izvedemo gradnjo na prvotni funkcionalnosti z dopolnjevanjem in dodajanjem vrednosti obstoječi platformi.

<p align="center">
  <img src="https://i.imgur.com/R81mGsx.png">
</p>

## Navodila za implementacijo

1.	Prenesite in namestite: IntelliJ IDEA 2019.1.2, Java 1.8 MSSQL Server (vsaj 2016), SQL Server Management Studio (SMMS) 18.0
2.  V environment variables v računalniku nastavite pot do bin direktorija JDK-ja (Tutorial: https://www.javatpoint.com/how-to-set-path-in-java)
3.	Odprite razvojno okolje IntelliJ IDEA, kliknite opcijo "Check out from Version Control, v dropdown menuju izberite opcijo Git
4.  V polje URL prekopirajte povezavo https://github.com/uzagoranski/Praktikum_OptiTech.git, izberite lokacijo kloniranja in pritisnite na gumb Clone
5.  Na mail nam pišite za .bak datoteko podatkovne baze OptiTech. Ko pridobite datoteko jo "restorajte" v programu SQL Server Management Studio
6.  Povežite projekt s podatkovno bazo tako, da spremenite niz "connectionUrl" v razredu SQLDatabaseConnection v vaše podatke
7.  Stisnite na boot-form-handling -> Plugins -> Spring-boot -> in dvakrat kliknite na Spring-boot:run. Počakajte, da se naloži & v brskalniku odprite naslov http://localhost:8080/

## Navodila za zagon

<i>*Predpogoj: Operacijski sistem Windows</i>

1.	Omogočite program Hyper-V v operacijskem sistemu Windows po korakih iz [vodiča](https://docs.microsoft.com/en-us/virtualization/hyper-v-on-windows/quick-start/enable-hyper-v) 
2.  Prenesite .zip datoteko slike virtualnega stroja s predpripravljenimi komponentami za zagon aplikacije iz [povezave](https://drive.google.com/open?id=1fGRkT9z-re7TiKLI1Ztd-cAPIz_dXKCn) 
3.  Razširite .zip datoteko na željeno mesto na napravi
3.  Odprite program Hyper-V Manager in preko ukaza "Import Virtual Machine" dodajte sliko virtualnega stroja, ki se nahaja v direktoriju "OptiTech/Virtual Machines" in ima naziv "C67704EC-1E34-4020-AC29-7E914DED161B.vmcx" ter sledite [vodiču](https://docs.microsoft.com/en-us/windows-server/virtualization/hyper-v/deploy/export-and-import-virtual-machines)
4.  Podatki za prijavo v OS:
  *   <i>Uporabniško ime:</i> <b>User</b>
  *   <i>Geslo:</i> <b>1234</b>
5.  Po uspešni prijavi odprite "Command Prompt" (cmd) kot administrator, vpišite komando <b>"cd C:\Program Files\Apache Software Foundation\Tomcat 8.5\bin"</b> ter takoj za tem komando <b>"catalina.bat run"</b>. Počakajte, da se izpiše <i>"INFO [main] org.apache.catalina.startup.Catalina.start Server startup in 29959 ms"</i>
6. Odprite brskalnik, prekopirajte naslov http://localhost:8080/OptiTech/ in brskajte ter raziskujte, kaj aplikacija nudi

<p align="center">
  <img src="https://i.imgur.com/Fl0BHne.png">
</p>

## Izdelano s pomočjo

* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - Razvojno okolje (IDE)
* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Boot](https://spring.io/projects/spring-boot) - Orodje za pomoč (Framework)
* [MSSQL Server 2016+](https://www.microsoft.com/en-us/sql-server/sql-server-2016) - Strežnik za podatkovno bazo
* [SQL Server Management Studio (SSMS)](https://docs.microsoft.com/en-us/sql/ssms/download-sql-server-management-studio-ssms?view=sql-server-2017) - Orodje za delo s podatkovno bazo

## Prispevki

V primeru želje po sodelovanju pri projektu nas lahko kontaktirate in nam pošljete morebitne prispevke na email naslov: mestniutripmaribor@gmail.com

## Različica

Različica: 7.5.2

To je zadnja uradno objavljena različica.

## Avtorji

[<img alt="PrimozStopar" src="https://avatars1.githubusercontent.com/u/22565865?s=460&v=4" width="117">](https://github.com/Evixiss) |[<img alt="AljosaSikosek" src="https://avatars2.githubusercontent.com/u/33753351?s=460&v=4" width="117">](https://github.com/aljosasikosek) |[<img alt="UrosZagoranski" src="https://avatars1.githubusercontent.com/u/33725933?s=460&v=4" width="117">](https://github.com/uzagoranski) |
:---: |:---: |:---: |
[Primož Stopar](https://github.com/Evixiss) |[Aljoša Sikošek](https://github.com/aljosasikosek) |[Uroš Zagoranski](https://github.com/uzagoranski) |
