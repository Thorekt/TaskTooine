# Project Context

## But

TaskTooine est un outil de gestion de taches et de projets.

Le repo est compose de :
- `tasktooine-api` pour le backend
- `tasktooine-front` pour le frontend

## Etat Actuel

### Backend

Le backend est un projet Spring Boot avec une architecture metier decouplee.

Contexte actuellement observe dans `tasktooine-api` :
- Point d'entree minimal Spring Boot
- Entites metier principales : `Project`, `Task`, `TaskStatus`
- Use cases presents pour la creation, mise a jour, suppression et association projet/tache
- Repositories definis comme interfaces metier
- Pas encore d'implementations concretes detectees dans `adapter/controller`, `adapter/gateway`, `adapter/presenter`

Modele metier actuel :
- Un `Project` contient une liste de `Task`
- Une `Task` possede `id`, `title`, `description`, `status`
- Le statut par defaut d'une tache est `BACKLOG`
- Les use cases recuperent l'entite via repository, modifient ses champs, puis la sauvegardent
- Si l'entite n'existe pas, plusieurs use cases quittent silencieusement

### Frontend

Le frontend est un projet Angular.

Contraintes deja documentees :
- Standalone components
- Signals pour l'etat local
- Bonnes pratiques Angular et accessibilite documentees dans `tasktooine-front/AGENTS.md`

## Intentions D'Architecture

Le projet suit une separation nette entre :
- metier
- orchestration
- acces technique
- presentation

Le code metier doit rester simple et peu couple aux details framework.

## Usage Recommande En Debut De Session

Pour reconstruire rapidement le contexte :
1. Lire `AGENTS.md`
2. Lire ce fichier
3. Lire `SESSION_HANDOFF.md`
4. Lire seulement les fichiers lies a la zone touchee par la demande
