# Project-Protectors
Members: Zászlós Dorottya Beáta, Árvai Balázs, Bíró Benjámin, Fodor Ádám

# Meetings
Minden héten kedd 18:00-tól, illetve szobmat 12:00-től egy pár perces beszélgetésre kerül sor discord-on, mely során mindenki beszámol
arról min dolgozott az elmúlt időszakban. Ha valaki nem tud részt venni valamelyik alkalmon Teams chat-en illik előre pár mondatban leírni amit mondana.

# Workflow
A GitHub-on a feladatok a beadandók alapján projektekre vannak taggolva. A projektekhez tartozó részfeladatok tovább vannak bontva ticket-ekre.  
Ha egy ticket-en valaki dolgozni kezd, az alábbi lépéseket kövesse:  
    1. A ticket-et assign-olja magához  
    2. A ticket állapotát állítsa át In progress-re  
    3. Hozzon létre egy új branch-en a legfrissebb main-ről leágazva, melynek a nevében legyen benne a ticket neve és száma.  
    4. Ha késznek ítéli a feladatot akkor nyisson egy pull request-et a main branch-re.  
    5. A pr-hez adja hozzá a ticket-et, hogy a rendszer automatikusan tudja mozgatni a jegyet.  
    6. Ha a review-t végző fejlesztők módosításokat kérnek nem szükséges új pr létrehozása, csupán a branch-re fel kell pusholni az új  commit-okat.  
    7. Ha legalább két másik fejlesztő approve-olta a review-t, akkor be kell mergelni és lezárni a branchet.  
    8. Ellenőrizni a jegy megfelelően lezárult (Done).  

# About Git and tips
-Könnyebb átláthatóság kedvéért érdemes lehet a TortoiseGit-et kipróbálni.  
-Minden git művelet előtt érdemes fetch-elni, hogy biztos a repo legfrissebb állapotát lássa az ember.  
-Ne legyenek commitolva build-ek, idea-k projekt fáljai, test logok.  
-A commit message-ek értelmes, rövid leírása legyenek mit tartalmaz az adott commit.  
-A commit-ok tartalma tényleges előrelépés legyen, nem kell minden sor változást egyessével commit-olni.  
-Az egyes commit-ok lehetőleg ne törtjék a build-et és a teszteket, ha valaki mégis félkész munkát commit-ol jelezze a message végén  (--WIP, vagyis work in progress) és később squash-olja ezeket a commit-okat az átláthatóság kedvéért.  
-Ha két fejlesztő párhuzamosan dolgozik ugyanazon a kódrészen, a később befejezett feladat branch-ét rebase-elni kell a frissebb   verzióra, a konfliktusok feloldásához pedig érdemes a másik fejlesztő segítségét kérni. A rebase-elt branch-et mindenképpen tesztelni  kell!  
-A commit-olt munkát érdemes mindig rögtön fel is pusholni átláthatóság és biztonság okán.  

# Software Versions
-Maven:     3.6.3  
-Java:      1.8  

# Maven telepítése, projekt futtatása cmd-ből (Windows)
Feltételezem, hogy a java már telepítve van a gépedre.  

1. Töltsd le a maven-t zip-ként becsomagolva innen: https://maven.apache.org/download.cgi  
2. A zip-et csomagold ki, és a tartalmát másold át egy fix helyre, pl: `C:\Program Files\apache-maven-3.6.3\ ` 
3. Adj hozzá egy környzeti változót `M3_HOME` néven, a tartalma a kicsomagolt fájlok helye legyen, pl: `C:\Program Files\apache-maven-3.6.3\`  
4. a `PATH` környezeti változóhoz add hozzá az ezen belüli bin foldert: `C:\Program Files\apache-maven-3.6.3\bin`  
5. cmd-ben győződj meg róla, hogy a maven helyesen telepítve lett:  
```
mvn -v  
```
Ha mindent jól van beállítva, akkor egy ehhez hasonló outputot kell kapnod:  
```
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: C:\Program Files\apache-maven-3.6.3\bin\..
Java version: 1.8.0_281, vendor: Oracle Corporation, runtime: C:\Program Files (x86)\Java\jre1.8.0_281
Default locale: hu_HU, platform encoding: Cp1250
OS name: "windows 10", version: "10.0", arch: "x86", family: "windows"
```

Ezen a linken találsz bővebb infót a telepítéshez:
https://maven.apache.org/install.html

6. Ellenőrizd, hogy a `JAVA_HOME` környezeti változód megfelelően van beállítva.  
Ha nincs, akkor hozd létre, a jdk-ra kell, hogy mutasson. Ha java telepítés közben nem változtattad meg a helyét, akkor valami ilyesmi kell, hogy legyen: `C:\Program Files\Java\jdk1.8.0_191`  

## Projekt futtatása cmd-ből
1. Klónozd le a repót egy erre a célra létrehozott folderbe  
```
cd app-folder  
git clone https://github.com/13alazs/Project-Protectors.git
```

2. fordítsd le a projectet maven-el:  
```
mvn package
```

3. ha a build sikeres volt, futtasd a projectet a következő paranccsal:  
```
java -cp target/app-1.0-SNAPSHOT.jar protectors.RPG
```

4. A teszteket így tudod futtatni:  
```
mvn test
```

#  Maven telepítése, projekt futtatása cmd-ből (Ubuntu)
```
apt-cache search maven
sudo apt-get install maven
```
ellenőrizheted a telepítés sikerességét:  
```
mvn -v
```

Klónozd a projectet:  
```
git clone https://github.com/13alazs/Project-Protectors.git
```

Build, run:  
```
mvn package
java -cp target/app-1.0-SNAPSHOT.jar protectors.RPG
```

#  Javadoc plugin használata
```
mvn javadoc:javadoc

```
You can find the generated documentation in ("traget/site/apidocs")
