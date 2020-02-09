# API menicka.cz
Verzia: v1

Toto api slúži k prístupu k základným údajom zo stránky menicka.cz

**API nie je dokončené a niektoré veci sú iba načrtnuté a je potrebné ich doladiť**

*Celý kód je písaný v jave a bol testovaný na Wildfly18 s Java11*

## Základná funkčnosť
API podporuje moťnosť cachovania výsledkov (mestá a restaurácie v nich)
Toto cachovanie prebieha každú pol hodinu a slúži ku getovanie meníčka ponocou názvu reštaurácie a mesta.
Prístup k údajom zabezpečuje rest.

**Pozor! Nie je doladená situácia kedy existujú 2 reštaurácie s rovnakým menom v rozdielnych mestách.**

## REST volania
- test
