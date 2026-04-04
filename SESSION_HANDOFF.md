# Session Handoff

## Resume Courant

Le contexte minimal du projet a ete formalise pour permettre une reprise rapide entre sessions.

Etat backend confirme a la date de cette note :
- Architecture de type clean/hexagonale simplifiee
- Dossier `adapter` documente mais sans classes Java concretes detectees
- Dossier `core` deja alimente avec entites et use cases

## Dernieres Observations

- `tasktooine-api` contient deja les use cases suivants :
  - projets : create, update, delete, add task, remove task
  - taches : create, update, update status, delete
- `IUseCase` expose actuellement `void execute()`
- Les entites utilisent des champs publics
- Le repo racine ne contenait pas encore de mecanisme explicite de reprise de contexte avant cette mise en place

## Prochain Point De Reprise

Quand une nouvelle session commence :
1. Lire `AGENTS.md`
2. Lire `PROJECT_CONTEXT.md`
3. Lire ce fichier
4. Mettre a jour cette note apres tout changement important

## A Mettre A Jour Apres Chaque Session Importante

- Ce qui a ete modifie
- Decisions d'architecture prises
- Hypotheses encore ouvertes
- Prochaine action recommande
