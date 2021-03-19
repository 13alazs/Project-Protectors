# Project-Protectors
Members: Zászlós Dorottya Beáta, Árvai Balázs, Bíró Benjámin, Fodor Ádám

# Meetings
Minden héten kedd 18:00-tól, illetve szobmat 12:00-től egy pár perces beszélgetésre kerül sor discord-on, mely során mindenki beszámol
arról min dolgozott az elmúlt időszakban. Ha valaki nem tud részt venni valamelyik alkalmon chat-en illik előre pár mondatban leírni amit mondana.

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
-Netbeans:  12.3  
-Maven:     3.6.3  
-Java:      1.8  
