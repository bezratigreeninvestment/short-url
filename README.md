# Présentation du travail
La solution implémentée s'agit d'une application Spring Boot 2 prête à démarrer sans aucune configuration additionnelle (download and run).

Les technologies principales utilisées sont:
* Java 17
* Spring Boot 2.7.15
* Hibernate 5.6.15
* Base de données H2 2.1 et MySQL 8
* Maven
* Docker

Le modèle conceptuel s'agit d'un microservice exposant un API REST.

Des tests d'intégration roulés par Spring Boot servent aussi bien à tester les contrôlleurs que les services.

La persistence est implémentée par Hibernate et Spring Data.

## Comment rouler l'application en local
Simplement exécuter la classe ShortUrlApplication. L'application Spring Boot exposera le port 8000.

Une collection Postman est disponible sous doc/Short URL.postman_collection.json

Pour raccourcir une URL, envoyer la requête HTTP suivante:

POST http://localhost:8000/shorten avec comme body votre url, i.e.: google.com

Ceci retournera l'URL raccourcie à même le body de la réponse: http://ibezrati.io/b0579bed91

Pour obtenir l'URL complète, envoyer la requête HTTP suivante:

GET http://localhost:8000/expand?url=http://ibezrati.io/b0579bed91 

Ceci retournera l'URL originale à même le body de la réponse, i.e.: google.com

## Persistence
La base de donnée H2 a été choisie pour rouler l'application par défaut car elle ne requiert aucune configuration du poste.
Cependant, il est possible de basculer vers une base de données MySQL en commentant la configuration H2 et en activant la configuration MySQL.

Un indexe a été créé pour accélérer la recherche des URL raccourcies.
Une clé unique empêche la duplication des URL originales.

## Conteneurisation
La build maven bâtie une image docker nommée ibezrati/short-url:0.0.1-SNAPSHOT

Pour lancer le build: 

`mvn clean install`

Le fichier docker-compose.yaml permet de lancer l'application et un serveur MySQL 8 sur Docker.
Pour cela, simplement exécuter la commande suivante à partir du répertoire racine du projet:

`docker-compose up -d`

Pour terminer l'exécution des conteneurs docker:

`docker-compose down`

## Améliorations possibles

* Une synchronization lors de la création des URLs qui serait compatible avec la parallèlisation des instances dans un environnement cloud. Penser Redis.
* Augmenter la couverture de tests.
* Introduire SonarQube pour valider la qualité du code, la couverture des tests et certaines pratiques sous-optimales.
* Plus de validation au niveau du contrôlleur
* Cache. Penser Redis.
* Un modèle de données plus sophistiqué en entrée et en sortie de l'API REST. Le but de ce travail était seulement d'implémenter les requis.
* Gérer les collisions possibles entre les URLs raccourcies car le nombre de caractères est limité à 10

## Contraintes
* Changer l'algorithme de raccouricement ou le domaine dans la config dupliquerait les URLs existantes si elles sont raccourcies de nouveau.


