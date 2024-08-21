# Jeu de Quilles Ancien - Simulation Informatique

## Description du Projet

Ce projet est une simulation informatique basée sur un jeu ancien récemment découvert en Afrique, dont les règles ressemblent étrangement à celles du bowling moderne. Le but du jeu est de lancer un morceau de bois en direction de 15 quilles pour essayer d'en abattre le maximum possible. Le jeu se déroule sur 5 frames, chaque frame comportant 3 lancers. Les quilles sont redressées après chaque frame.

### Règles du Jeu

- **Frames et Lancers :** Le jeu est composé de 5 frames. Chaque frame permet jusqu'à 3 lancers pour tenter d'abattre les 15 quilles.
- **Strike :** Si toutes les quilles sont abattues lors du premier lancer d'une frame, cela est compté comme un "strike". Un strike vaut 15 points, plus les quilles abattues lors des 3 lancers suivants, avec un score maximum possible de 60 points par strike.
- **Spare :** Si toutes les quilles sont abattues après le 2e ou 3e lancer d'une frame, cela est compté comme un "spare". Un spare vaut 15 points, plus les quilles abattues lors des 2 lancers suivants, avec un score maximum possible de 45 points par spare.
- **Lancer Supplémentaire :** Lorsqu'un strike ou un spare est réalisé dans la dernière frame, des lancers supplémentaires sont accordés pour calculer le score final de la dernière frame.
- **Score Parfait :** Le score parfait maximum réalisable dans ce jeu est de 300 points.

### Symboles Utilisés

- **Spare :** Symbolisé par une barre ("/") dans la dernière case de la frame.
- **Strike :** Symbolisé par une croix ("X") dans la première case de la frame.

Ce projet simule ce jeu en appliquant les règles décrites, permettant aux utilisateurs de jouer et de calculer leur score comme s'ils jouaient au jeu original.


## Installation et Utilisation

Clonez le dépôt, compilez le projet avec Maven, et exécutez l'application pour commencer à jouer.

```bash
git clone https://github.com/KhairulIslam9/telemis_2024.git
cd telemis_2024
mvn clean install
mvn spring-boot:run