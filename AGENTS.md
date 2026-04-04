# TaskTooine Agent Notes

Ce depot contient deux applications :
- `tasktooine-api` : backend Spring Boot / Java
- `tasktooine-front` : frontend Angular

## Startup Workflow

Au debut de chaque nouvelle session sur ce repo :
1. Lire `README.MD`
2. Lire `PROJECT_CONTEXT.md`
3. Lire `SESSION_HANDOFF.md`
4. Si la tache concerne l'API, lire aussi les `.md` d'architecture sous `tasktooine-api/src/main/java/com/thorekt/tasktooine_api`
5. Si la tache concerne le front, lire `tasktooine-front/AGENTS.md`

## Context Policy

- `PROJECT_CONTEXT.md` contient le contexte stable du projet
- `SESSION_HANDOFF.md` contient l'etat courant, les decisions recentes et le prochain point de reprise
- En fin de modification importante, mettre a jour `SESSION_HANDOFF.md`
- Ne pas supposer que le contexte en memoire d'une session precedente existe encore

## API Notes

Architecture actuellement visee pour `tasktooine-api` :
- `core/entity` : entites metier sans logique de traitement
- `core/usecase` : logique metier et interfaces necessaires
- `adapter/controller` : orchestration des use cases sans logique metier
- `adapter/gateway` : acces aux ressources externes
- `adapter/presenter` : adaptation des sorties pour le client

## Working Rule

En cas de contradiction entre le code et les fichiers de contexte, traiter le code comme source de verite et corriger ensuite la documentation.
