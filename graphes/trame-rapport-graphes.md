[TOC]
>
Vous pouvez télécharger le présent fichier au format .md depuis la page Moodle de la SAÉ à l'aide d'un clic droit sur le lien, "Enregistrer la cible du lien" ou équivalent.

Le rapport à rendre doit être au format pdf ou html (éventuellement format md lisible sur gitlab) et doit suivre le plan donné ici. 
Il doit se trouver dans un répertoire `graphes` à la racine de votre dépôt git.
Le premier rendu concerne uniquement la Version 1, le second rendu concerne la Version 2.

Pour chaque partie du plan nous indiquons *en italique* ce que cette partie doit contenir au minimum.

Le rapport n'est pas un DS où on se contente de répondre aux questions. 
Dans le rapport **vous devez écrire des phrases et ajouter toutes les explications** qui vous semblent nécessaires pour le rendre compréhensible et agréable à lire, comme tout rapport se doit d'être.


SAE S2.02 -- Rapport pour la ressource Graphes
===

*Noms des auteurs, groupe*

## Version 1
---

Sera évaluée à partir du tag git `Graphes-v1`

### Étude d'un premier exemple

*Énumérer tous les appariements acceptables (c'est à dire qui asocient des adolescents compatibles) pour les données de l'Exemple 1, en supposant que les français rendent visite aux italiens.*

>**Les couples d'étudiants compatibles sont :**
>
>| Host/Guest    | Xolag    | Yak | Zander |
>|:-:            |:-:       | :-: | :-:    |
>| **Adonia**    | &#10004; | &#10004; | &#10004; |
>| **Bellatrix** | &#10004; | &#10060; | &#10004; |
>| **Callista**  | &#10004; | &#10004; | &#10004; |
>
>Seul le couple Bellatrix--Yak n'est pas compatible puisque Yak a un animal et Bellatrix est allergique aux animaux.


*Justifier pourquoi l'appariement optimal est Bellatrix--Xolag, Adonia--Zander, et Callista--Yak; cette explication ne doit pas parler de graphes, mais se baser uniquement sur les données du problème.*

>**Parmi les appariement possibles on va chercher celui pour lequel les éleves ont le plus d'affinitées entre eux donc ayant le plus d'hobbies en commun :**
>
>| Guest/Host    | Xolag | Yak      | Zander |
>|:-:            |:-:    | :-:      | :-:    |
>| **Adonia**    | 1     | 0        | 1      |
>| **Bellatrix** | 1     | &#10060; | 0      |
>| **Callista**  | 0     | 2        | 0      |
>
> D'après ce tableau on peut déterminer le meilleur appariement comme étant Callista--Yak ayant 2 hobbies en commun, Bellatrix--Xolag en ayant 1, et Adonia--Zander en ayant 1 aussi pur un total d'hobbies en commun de 4, cet appariement correspondant pour chaque guest au nombre d'hobbies en commun maximum possible avec un host et donc ayant le maximum possible d'hobbies en commun pour un appariement.

### Modélisation de l'exemple

*Donner un graphe qui modélise l'Exemple 1, plus précisément, la matrice d'adjacence de ce graphe. Expliquez comment vous avez choisi le poids pour chacune des arêtes.*

>Pour réaliser la matrice d'adjacence on défini un poids par défault pour chacune des arêtes à 100, auquel on rajoute 100 en cas de non compatibilité entre l'host et le guest correspondant à l'arrête, et, pour chaque hobbies en commun, on enleve 1 au poids total. 
>
>
>**Détail du calcul :**
>
>| Guest/Host    | Xolag        | Yak            | Zander      |
>|:-:            |:-:           | :-:            | :-:         |
>| **Adonia**    | 100 + 0 - 1  | 100 + 0 - 0    | 100 + 0 - 1 |
>| **Bellatrix** | 100 + 0 - 1  | 100 + 100 - 1  | 100 + 0 - 0 |
>| **Callista**  | 100 + 0 - 0  | 100 + 0 - 2    | 100 + 0 - 0 |
>
>**Matrice d'adjacence obtenue :**
>| Guest/Host    | Xolag | Yak      | Zander |
>|:-:            |:-:    | :-:      | :-:    |
>| **Adonia**    | 99    | 100      | 99     |
>| **Bellatrix** | 99    | 199      | 100    |
>| **Callista**  | 100   | 98       | 100    |
>


### Modélisation pour la Version 1

*Décrire une modélisation générale pour la Version 1. C'est à dire, donner une formule ou une description précise qui décrit comment, étant donné un adolescent hôte et un adolescent visiteur, on détermine le poids de l'arête entre ces deux adolescents en fonction des critères considérés dans la Version 1.*

>Comme expliqué plus haut, le poids de l'arête reliant un adolescent hôte et un adolescent visiteur par défault est à 100, si l'hôte possède un animal et que le visiteur est allergiques aux animaux, alors on rajoute 100 au poids, et, pour chaque hobbies en commun qu'ont l'hôte et le visiteur, on enleve 1 au poids total pour un maximum de 3 hobbies en commun compté (on enlèvera jusqu'à 3 points au maximum si il y a plus de trois hobbies en commun).
>
>Calcul : 100 + (100 si non compatible sinon 0) - 1*n (n = nombre d'hobbies en commun <=3) 

### Implémentation de la Version 1

*Cette partie du travail sera évaluée à partir du code. Implémenter la fonction weight de la classe AffectationUtil en suivant la modélisation proposée. Puis, implémenter une classe TestAffectationVersion1 qui montre que votre implémentation est correcte. La classe de test doit contenir au moins une méthode de test comme ceci:*
- *créer les adolescents de l'Exemple 1*
- *construire le graphe modèle pour ces adolescents; le graphe sera de type fr.ulille.but.GrahpeNonOrienteValue*
- *calculer l'affectation optimale en utilisant la classe fr.ulille.but.CalculAffectation*
- *écrire des assertions (assertEquals ...) qui vérifient que le résultat de l'affectation calculé à l'étape précédente est bien celui attendu*

*Si vous n'êtes pas à l'aise avec les tests unitaires, votre classe TestAffectationVersion1 peut contenir une méthode main à la palce de la méthode de test, dans ce cas vous afficherez dans le terminal l'appariement résultat.*

### Exemple de vérification de l'incompatibilité 

*Cet exemple va mettre au défi votre modèle vis à vis de la prise en compte de l'incompatibilité entre adolescents 

Récupérez sur Moodle le fichier de données `compatibilityVsHobbies.csv`. Expliquez quelle est sa particularité de cet exemple. Écrire la méthode de test qui test qui change cet exemple, construit le graphe modèle, calcule l'affectation, et finalement vérifie qu'aucune paire d'adolescents non compatibles n'a été construite par l'algorithme.*

>La particularité de ce fichier est qu'il y a énormément de hobbies en commun entre l'hôte D et le visiteur A, or ils ne sont pas compatible car l'ôte D a un animal et le visiteur A est allergique aux animaux, donc même si ils ont plein de hobbies en commun il ne peuvent pas être mis ensemble, il ne faut pas que le grand nombre d'hobbies en commun prend le dessus sur la non compatibilité.
>
>Dans l'exemple du csv `compatibilityVsHobbies.csv`, le visiteur A devrait faire la paire avec l'hôte C et le visiteur B avec l'hôte D.

## Version 2
---

Sera évaluée à partir du tag git `Graphes-v2`

### Exemple minimal pour la gestion de l'historique

*Présenter un exemple minimal qui est pertinent pour tester l'historique. L'exemple contiendra:*
- *huit adolescents de deux pays différents tels que* 
  - *certains des adolescents expriment des préférences d'historique (critère HISTORY). Toutes les valeurs possibles pour ce critère doivent être présentes* 
  - *aucun des adolescents n'est allergique aux animaux en aucun n'a exprimé de passe-temps, ainsi pour l'instant on peut se concentrer uniquement sur la gestion de l'historique*
- *un historique, c'est à dire une collection de paires d'adolescents qui étaient correspondants l'année passée. Ces paires doivent permettre d'illustrer les différents cas de figure qui peuvent se présenter par rapport aux contraintes d'historique et les huit adolescents*

*Puis, donner un appariement optimal qui tient compte des données d'historique, et expliquer pourquoi il est optimal. L'explication ne doit pas parler des graphes, mais uniquement des adolescents et les critères exprimés.*



>**Exemple étudiants :**
>|FORENAME  |NAME        |COUNTRY   |BIRTH_DATE |HOBBIES                 |GUEST_ANIMAL_ALLERGY|HOST_HAS_ANIMAL|GUEST_FOOD |HOST_FOOD         |GENDER  |PAIR_GENDER|HISTORY|
>|:-:       |:-:         | :-:      | :-:       | :-:                    | :-:                | :-:           | :-:       | :-:              | :-:    | :-:       | :-:   |
>|Dalthu    |Tanjin      |ITALY     |           |                        |no                  |               |           |                  |        |           |same   |
>|Lilly     |Damon       |ITALY     |           |                        |no                  |               |           |                  |        |           |same   |
>|Jensmebur |Ekey        |ITALY     |           |                        |no                  |               |           |                  |        |           |same   |
>|Himimtoss |Ozith       |ITALY     |           |                        |no                  |               |           |                  |        |           |       |
>|Laris     |Rex         |GERMANY   |           |                        |                    |no             |           |                  |        |           |same   |
>|Bellatrix |Interfector |GERMANY   |           |                        |                    |yes            |           |                  |        |           |other  |
>|Mave      |Crane       |GERMANY   |           |                        |                    |no             |           |                  |        |           |same   |
>|Hinkkost  |Enaxx       |GERMANY   |           |                        |                    |yes            |           |                  |        |           |       |
>
>
>**Historique :**
>
>- Dalthu--Mave
>- Lilly--Hinkkost
>- Jensmebur--Bellatrix
>- Himimtoss--Laris
>
>Parmi les appariement possibles on va chercher celui pour lequel aucun éleve ayant précisé "other" se retrouve avec le même étudiant que l'année dernière, tous les anciens couples d'étudiants ayant tous les deux précisé "same" se retrouve absolument ensemble, et les anciens couples dont un seul des deux a précisé "same" sont priorisé.
>
>Voici les représentation utilisée pour y voir clair :
>
>- Doit être ensemble : &#10004;
>- Est priorisé : &#128578;
>- Pas de particularitée :  &#128528;
>- Ne doit pas être ensemble : &#10060;
>
>| Guest/Host    | Laris    | Bellatrix | Mave     | Hinkkost |
>|:-:            |:-:       | :-:       | :-:      | :-:      |
>| **Dalthu**    |&#128528; |&#128528;  |&#10004;  |&#128528; |
>| **Lilly**     |&#128528; |&#128528;  |&#128528; |&#128578; |
>| **Jensmebur** |&#128528; |&#10060;   |&#128528; |&#128528; |
>| **Himimtoss** |&#128578; |&#128528;  |&#128528; |&#128528; |
>
>
> D'après ce tableau on peut déterminer un appariement optimale comme étant :
>- Dalthu--Mave 
>- Jensmebur--Laris
>- Lilly--Hinkkost
>- Himimtoss--Bellatrix
>
>**Dalthu--Mave** étant une ancienne paire d'étudiant ayant mis "same" tous les deux ils sont donc obligatoirement ensemble.
Jensmebur--Bellatrix étant une ancienne paire d'étudiant dont l'un à mis other, le couple ne doit surtout pas être ensemble, il faut donc associer Jensmebur avec quelqu'un d'autre, on l'associe donc avec Laris car dans les deux cas il n'y a pas d'affinité **Jensmebur--Laris**.
Ensuite on peut voir que **Lilly--Hinkkost** on une affinité entre eux puisque l'un a mis same mais l'autre n'a rien mis, on peut donc les mettre ensemble.
Pour finir, la paire **Himimtoss--Bellatrix** est par défault la dernière n'ayant pas de particularité.
Cet appariement correspond à l'un des deux appariement optimal possible pour cet exemple.





### Deuxième exemple pour la gestion d'historique

*Modifiez l'exemple précédent en ajoutant des préférences liées aux passe-temps. Donnez l'appariement que vous considérez optimal dans ce cas. En particulier, expliquez comment vous comptez combiner une éventuelle affinité liée à l'historique avec l'affinité liée aux passe-temps. Rappelons que l'historique peut compter comme une contrainte rédhibitoire ou comme une préférence, voir le sujet pour plus de précisions.*

*Donner l'appariement que vous considérez optimal dans ce deuxième exemple, toujours sans parler de graphes.*

>
>**Exemple étudiants modifié :**
>|FORENAME  |NAME        |COUNTRY   |BIRTH_DATE |HOBBIES                 |GUEST_ANIMAL_ALLERGY|HOST_HAS_ANIMAL|GUEST_FOOD |HOST_FOOD         |GENDER  |PAIR_GENDER|HISTORY|
>|:-:       |:-:         | :-:      | :-:       | :-:                    | :-:                | :-:           | :-:       | :-:              | :-:    | :-:       | :-:   |
>|Dalthu    |Tanjin      |ITALY     |           |                        |no                  |               |           |                  |        |           |same   |
>|Lilly     |Damon       |ITALY     |           |reading                 |no                  |               |           |                  |        |           |same   |
>|Jensmebur |Ekey        |ITALY     |           |culture                 |no                  |               |           |                  |        |           |same   |
>|Himimtoss |Ozith       |ITALY     |           |culture,science         |no                  |               |           |                  |        |           |       |
>|Laris     |Rex         |GERMANY   |           |science                 |                    |no             |           |                  |        |           |same   |
>|Bellatrix |Interfector |GERMANY   |           |reading,culture         |                    |yes            |           |                  |        |           |other  |
>|Mave      |Crane       |GERMANY   |           |sports                  |                    |no             |           |                  |        |           |same   |
>|Hinkkost  |Enaxx       |GERMANY   |           |sports,culture,reading  |                    |yes            |           |                  |        |           |       |
>
>
>**Historique :**
>
>- Dalthu--Mave
>- Lilly--Hinkkost
>- Jensmebur--Bellatrix
>- Himimtoss--Laris
>
>Pour ce nouvel exemple on va reprendre la notation précédente à laquelle on va rajouter le nombre de hobbies en communs comme pour l'exemple de la version 1.
>
>Voici les représentation utilisée pour y voir clair :
>
>- Doit être ensemble : &#10004;
>- Est priorisé : &#128578;
>- Pas de particularitée :  &#128528;
>- Ne doit pas être ensemble : &#10060;
>
>| Guest/Host    | Laris        | Bellatrix    | Mave         | Hinkkost     |
>|:-:            |:-:           | :-:          | :-:          | :-:          |
>| **Dalthu**    |&#128528; / 0 |&#128528; / 0 |&#10004; / 0 |&#128528; / 0  |
>| **Lilly**     |&#128528; / 0 |&#128528; / 1 |&#128528; / 0 |&#128578; / 0 |
>| **Jensmebur** |&#128528; / 0 |&#10060; / 1  |&#128528; / 0 |&#128528; / 1 |
>| **Himimtoss** |&#128578; / 1 |&#128528; / 1 |&#128528; / 0 |&#128528; / 1 |
>
>
> D'après ce tableau on peut déterminer un appariement optimale comme étant : 
>- Dalthu--Mave 
>- Jensmebur--Hinkkost
>- Himimtoss--Laris
>- Lilly--Bellatrix
>
>Comme précédemment cela ne change pas, **Dalthu--Mave** étant une ancienne paire d'étudiant ayant mis "same" tous les deux ils sont donc obligatoirement ensemble.
Comme précédemment Jensmebur--Bellatrix étant une ancienne paire d'étudiant dont l'un à mis other, le couple ne doit surtout pas être ensemble, il faut donc associer Jensmebur avec quelqu'un d'autre, cependant cette fois-ci on a ajouter les hobbies à prendre en compte, on l'associe donc avec Hinkkost **Jensmebur--Hinkkost** car ils ont un hobbie en commun alors que l'autre possibilité Jensmebur--Laris n'ont pas d'hobbies en commun.
Ensuite on peut voir que **Himimtoss--Laris** on une affinité entre eux puisque l'un a mis same mais l'autre n'a rien mis, on peut donc les mettre ensemble (à voir de plus qu'ils ont un hobbie en commun que la paire choisit pour l'exemple précédent n'en a actuellemnt pas donc ce choit de couple est plus optimal).
Pour finir, la paire **Lilly--Bellatrix** est par défault la dernière n'ayant pas de particularité par rapport aux autres choix possibles.
Cet appariement correspond au seul appariement optimal possible pour cet exemple puisque les hobbies permettent de décider d'un appariement entre les deux optimaux de l'exemple précédent.

### Modélisation pour les exemples

*Pour chacun des deux exemples précédents, donnez un graphe (donné par sa matrice d'adjacence) tel que l'affectation minimale dans ce graphe correspond à l'appariement optimal identifié plus haut. Expliquez comment vous avez choisi le poids pour chacune des arêtes.*


#### Modélisation 1
>
>Pour réaliser la matrice d'adjacence on défini un poids par défault pour chacune des arêtes à 100 comme pour la version 1, auquel on :
>
>- Enlève 100 lorsque les deux étudiants étant ensemble l'année précédente on écrit "same".
>- Enlève 4 lorsque l'un des deux étudiants étant ensemble l'année précédente a écrit "same" et l'autre n'a rien renseigné.
>- Rajoute 100 lorsque l'un des deux étudiants étant ensemble l'année précédente a écrit "other" peu importe ce qu'a écrit l'autre.
>
>
>**Détail du calcul :**
>
>| Guest/Host    | Laris    | Bellatrix | Mave      | Hinkkost |
>|:-:            |:-:       | :-:       | :-:       | :-:      |
>| **Dalthu**    | 100 +- 0 | 100 +- 0  | 100 - 100 | 100 +- 0 |
>| **Lilly**     | 100 +- 0 | 100 +- 0  | 100 +- 0  | 100 - 4  |
>| **Jensmebur** | 100 +- 0 | 100 + 100 | 100 +- 0  | 100 +- 0 |
>| **Himimtoss** | 100 - 4  | 100 +- 0  | 100 +- 0  | 100 +- 0 |
>
>**Matrice d'adjacence obtenue :**
>
>| Guest/Host    | Laris    | Bellatrix | Mave     | Hinkkost |
>|:-:            |:-:       | :-:       | :-:      | :-:      |
>| **Dalthu**    | 100      | 100       | 0        | 100      |
>| **Lilly**     | 100      | 100       | 100      | 96       |
>| **Jensmebur** | 100      | 200       | 100      | 100      |
>| **Himimtoss** | 96       | 100       | 100      | 100      |
>
>
>Selon la matrice plus haut on prend pour chaque visiteur le poids avec l'hôte le plus faible :
>
>- Dalthu a le poids optimal avec Mave pour un poids de 0 **Dalthu--Mave**.
>- Lilly a le poids optimal avec Hinkkost pour un poids de 96 **Lilly--Hinkkost**.
>- Jensmebur a l'un des poids optimal avec Laris pour un poids de 100 **Jensmebur--Laris**.
>- Himimtoss n'a pas le poids optimal avec Bellatrix pour un poids de 100 mais c'est le dernier choix possible **Himimtoss--Bellatrix**.
>
>Cette confinaison permet d'avoir l'un des deux appariement optimal pour un poid de 0 + 96 + 100 + 100 = 296.



#### Modélisation 2
>
>Pour réaliser la matrice d'adjacence on défini un poids par défault pour chacune des arêtes à 100 comme pour la version 1, auquel soit on :
>
>- Enlève 100 lorsque les deux étudiants étant ensemble l'année précédente on écrit "same".
>- Enlève 4 lorsque l'un des deux étudiants étant ensemble l'année précédente a écrit "same" et l'autre n'a rien renseigné.
>- Rajoute 100 lorsque l'un des deux étudiants étant ensemble l'année précédente a écrit "other" peu importe ce qu'a écrit l'autre.
>
>Puis comme pour la modélisation de l'exemple de la version1, pour chaque hobbies en commun, on enleve 1 au poids total pour un maximum de 3 hobbies en commun compté (on enlève jusqu'à 3 points au maximum si il y a plus de trois hobbies en commun).
>
>
>
>**Détail du calcul :**
>
>| Guest/Host    | Laris        | Bellatrix     | Mave          | Hinkkost     |
>|:-:            |:-:           | :-:           | :-:           | :-:          |
>| **Dalthu**    | 100 +- 0 - 0 | 100 +- 0 - 0  | 100 - 100 - 0 | 100 +- 0 - 0 |
>| **Lilly**     | 100 +- 0 - 0 | 100 +- 0 - 1  | 100 +- 0 - 0  | 100 - 4 - 0  |
>| **Jensmebur** | 100 +- 0 - 0 | 100 + 100 - 1 | 100 +- 0 - 0  | 100 +- 0 - 1 |
>| **Himimtoss** | 100 - 4 - 1  | 100 +- 0 - 1  | 100 +- 0 - 0  | 100 +- 0 - 1 |
>
>**Matrice d'adjacence obtenue :**
>
>| Guest/Host    | Laris    | Bellatrix | Mave     | Hinkkost |
>|:-:            |:-:       | :-:       | :-:      | :-:      |
>| **Dalthu**    | 100      | 100       | 0        | 100      |
>| **Lilly**     | 100      | 99        | 100      | 96       |
>| **Jensmebur** | 100      | 199       | 100      | 99       |
>| **Himimtoss** | 95       | 99        | 100      | 99       |
>
>Selon la matrice plus haut on prend pour chaque visiteur le poids avec l'hôte le plus faible :
>
>- Dalthu a le poids optimal avec Mave pour un poids de 0 **Dalthu--Mave**.
>- Himimtoss a le poids optimal avec Laris pour un poids de 95 **Himimtoss--Laris**.
>- Jensmebur a l'un des poids optimal avec Hinkkost pour un poids de 99 **Jensmebur--Hinkkost**.
>- Lilly n'a pas le poids optimal avec Bellatrix pour un poids de 99 mais c'est le dernier choix possible **Lilly--Bellatrix**.
>
>Cette confinaison permet d'avoir l'appariement optimal pour un poids total de 0 + 95 + 99 + 99 = 293.
>
>Pour comparaison si on avait choisit l'appariement de la modélisation 1, on aurait un poids total de 0 + 96 + 100 + 99 = 295


### Modélisation pour l'historique de la Version 2

*Décrire une modélisation générale pour la Version 2. C'est à dire, donner une formule ou une description précise qui décrit comment, étant donné un adolescent hôte et un adolescent visiteur, on détermine le poids de l'arête entre ces deux adolescents en fonction des critères considérés dans la Version 2. Décrire également comment vous construisez le graphe modèle à partir des données en entrée.*

>Comme expliquer dans la modélisation précédente le poids de l'arête reliant un adolescent hôte et un adolescent visiteur par défault est à 100 comme pour la version 1, auquel soit on :
>
>- Enlève 100 lorsque les deux étudiants étant ensemble l'année précédente on écrit "same".
>- Enlève 4 lorsque l'un des deux étudiants étant ensemble l'année précédente a écrit "same" et l'autre n'a rien renseigné.
>- Rajoute 100 lorsque l'un des deux étudiants étant ensemble l'année précédente a écrit "other" peu importe ce qu'a écrit l'autre.
>
>Puis comme pour la modélisation de l'exemple de la version1, pour chaque hobbies en commun, on enleve 1 au poids total pour un maximum de 3 hobbies en commun compté (on enlève jusqu'à 3 points au maximum si il y a plus de trois hobbies en commun).
>
>Calcul : 100 ( - 100 si compatibilité forcée historique ou - 4 si affinité historique ou +100 si incompatibilité historique sinon 0) - 1*n (n = nombre d'hobbies en commun <=3)

### Implémentation de l'historique de la Version 2

*Quelles fonctions de votre code avez-vous modifié pour prendre en compte le critère historique ? Donnez ici les noms des méthodes (et leur classe), à quoi elles servent, et quelles modifications vous avez apportées. Essayez d'être synthétique.*

### Test pour l'historique de la Version 2

*Créer la classe de TestAffectationVersion2 qui contiendra deux méthodes de test, une pour chacun des exemples. Chacune de ces méthodes doit avoir la même structure que pour TestAffectationVersion1, c'est à dire créer les données d'entrée (adolescents, historique), créer le graphe, calculer l'affectation, et tester que le résultat est comme attendu.*

### Prendre en compte les autres préférences

*Pour chacun des autres critères d'affinité que vous décidez de prendre en compte, décrire comment vous changez la fonction weight de la classe AffectationUtil.*

### L'incompatibilité en tant que malus

*Proposer une formule ou une description précise qui explique comment calculer le poids d'une arête en considérant les incompatibilités comme des malus et les critères satisfaits comme des bonus. Implémenter cette formule dans une seconde méthode appelée `weightAdvanced`, ceci pour éviter de casser votre code. Puis, écrire une méthode de test qui permet d'illustrer le calcul d'affectation basé sur `weightAdvanced`. Vous pouvez égalmente tester l'affectation en utilisant le fichier de données `incompatibilityVsBonus.csv`.*

