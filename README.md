# Mastermind 

## Enoncé de l'exercice:
L'exercice consiste à implementer un jeu de mastermind en ligne de commande (pas besoin d'UI). 
Règles: 
Quand le jeu commence un code secret de 4 digits est généré, le but du jeu est de trouver le code après plusieurs tentatives successives. Le joueur doit être capable de proposer un code à 4 chiffres (input) et le jeu lui affiche des signes + ou - (Output) pour l'aider. Le jeu termine  quand le joueur a trouvé la combinaison exacte du code secret.  
1. Le jeu retournera un signe + pour un match exact :  
    - Un match exact est un digit à la bonne valeur et la bonne position par rapport au code secret  
2. Le jeu retournera un signe - pour un match partiel  
    - Un match partiel est un digit à la bonne valeur mais à la mauvaise position par rapport au code secret  
3. Les signes + sont à afficher avant les signes -  
4. Un digit de la tentative ne peut match qu'avec un seul digit du secret  
5. Un digit du secret ne peut match qu'avec un seul digit de la tentative  
6. Les matchs exacts sont prioritaires par rapport aux matchs partiels  

Remarque: le jeu fonctionne actuellement avec des digits, il ne faudrait que quelques petites
modifications pour qu'il fonctionne avec des couleurs comme le jeu original( création d'une
ENUM couleur, adaptation de l'userSide et du générator, modification du codeElement en abstract
avec une factory), le coeur du jeu ne serait pas à revoir.

## Requirement
Java 11 +  
Maven 3.5 +  

## Packaging and installation
Use maven for packaging executable jar :  
``` bash
mvn clean install
```
## Running
running the jar file  
``` bash
java -jar ./target/mastermind-1.0-SNAPSHOT.jar
```
