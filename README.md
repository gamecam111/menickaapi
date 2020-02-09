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
- listcities - Zoznam všetkých miest
- listrestaurants - Zoznam všetkých reštaurácií v konkrétnom meste podľa url. 
- listrestaurantsbyname - Zoznam všetkých reštaurácií v konkrétnom meste podľa mena mesta. 
- getRestaurant - Informácie o reštaurácii podľa url reštaurácie (menu, info atd..)
- getrestaurantbyname -  Informácie o reštaurácii podľa mena reštaurácie (menu, info atd..)


##Dodatok
Celé api je iba rýchlo nastrelené a kopa vecí v ňom potrebuje doladiť. Napríklad info o reštaurácii nie je kompletné a je nutné ho doplňiť. 


### © Erik Juríček 2020
