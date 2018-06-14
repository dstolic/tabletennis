# tabletennis
Table tennis app contains 3 microservices.

1. tabletennis-data - Admin service, maintain data.
2. tabletennis-report - Client service. Shows data for current competition only.
3. tabletennis-gateway - Gateway service.

Commit 14/6:
- 'model' package renamed to 'entity'
- Added temporary solution in CompetitionService to avoid Hibernate save() problem.
- Removed competition id from URL. 'report' service works with current competition. 'data' service use current competition as default. (in progress)


Commit 13/6:
- Git: switched work on dev branch
- Service classes refactored to work as implementation. 
- Service classes refactored to use Repository, instead of calling another service.
- CompetitionPlayer and CompetitionPlayerPK classes refactored: 
	a) @EmbeddedId instead of @IdClass
	b) Reference to Competition and Player instead of just id's
	
	
TODO: 
- Group as entity.
- Entity check if they need refactoring: Group (group name), Competition(remove competitionPlayers?), Game (remove groupNum?)
- Unit testing: JUnit & Mockito
- Remove Jackson annotations from Entity objects
- Solve Hibernate save() problem in CompetitionService.

