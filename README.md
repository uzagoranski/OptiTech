<p align="center">
  <img src="http://optitech.sydneyitsolutions.com/wp-content/uploads/2019/03/logo640.png">
</p>
          Spletna platforma za pregled in upravljanje podatkov testne flote vozil podjetja Kivi.

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


## Navodila za implementacijo

1.	Prenesite in namestite: IntelliJ IDEA 2019.1.2, Java 1.8 MSSQL Server (vsaj 2016), SQL Server Management Studio (SMMS) 18.0
2.  V environment variables v računalniku nastavite pot do bin direktorija JDK-ja (Tutorial: https://www.javatpoint.com/how-to-set-path-in-java)
3.	Odprite razvojno okolje IntelliJ IDEA, kliknite opcijo "Check out from Version Control, v dropdown menuju izberite opcijo Git
4.  V polje URL prekopirajte povezavo https://github.com/uzagoranski/Praktikum_OptiTech.git, izberite lokacijo kloniranja in pritisnite na gumb Clone
5.  Na mail nam pišite za .bak datoteko podatkovne baze OptiTech. Ko pridobite datoteko jo "restorajte" v programu SQL Server Management Studio
6.  Povežite projekt s podatkovno bazo tako, da spremenite niz "connectionUrl" v razredu SQLDatabaseConnection v vaše podatke
7.  Stisnite na boot-form-handling -> Plugins -> Spring-boot -> in dvakrat kliknite na Spring-boot:run. Počakajte, da se naloži & v brskalniku odprite naslov http://localhost:8080/

<p align="center">
  <img src="https://imgur.com/RQM0Xhh">
</p>

## Navodila za zagon

*Predpogoj: Operacijski sistem Windows

1.	Omogočite Hyper-V po korakih vodiča na [povezavi](https://docs.microsoft.com/en-us/virtualization/hyper-v-on-windows/quick-start/enable-hyper-v) 
2.  Prenesite sliko virtualnega stroja s predpripravljenimi komponentami za zagon aplikacije iz [povezave](https://github.com/uzagoranski/Praktikum_OptiTech/tree/master/vm) 
3.  Odprite program Hyper-V Manager in preko ukaza "Import Virtual Machine" dodajte sliko prej omenjenega virtualnega stroja
4.  Podatki za prijavo v OS:
* Uporabniško ime: User
* Geslo: 1234
5.  Po uspešni prijavi odprite "Command Prompt" kot administrator, vpišite komando "cd C:\Program Files\Apache Software Foundation\Tomcat 8.5\bin" ter takoj za tem komando "catalina.bat run". Počakajte, da se izpiše *"INFO [main] org.apache.catalina.startup.Catalina.start Server startup in 29959 ms"
6. Odprite brskalnik, prekopirajte naslov http://localhost:8080/OptiTech/ in brskajte ter raziskujte, kaj aplikacija nudi

<p align="center">
  <img src="https://imgur.com/Fl0BHne">
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

<p align="center">
  <img src="https://imgur.com/R81mGsx">
</p>

## Avtorji

[<img alt="PrimozStopar" src="https://avatars1.githubusercontent.com/u/22565865?s=460&v=4" width="117">](https://github.com/Evixiss) |[<img alt="AljosaSikosek" src="https://avatars2.githubusercontent.com/u/33753351?s=460&v=4" width="117">](https://github.com/aljosasikosek) |[<img alt="UrosZagoranski" src="https://avatars1.githubusercontent.com/u/33725933?s=460&v=4" width="117">](https://github.com/uzagoranski) |
:---: |:---: |:---: |
[Primož Stopar](https://github.com/Evixiss) |[Aljoša Sikošek](https://github.com/aljosasikosek) |[Uroš Zagoranski](https://github.com/uzagoranski) |
