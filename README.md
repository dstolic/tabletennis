# tabletennis
Table tennis app contains 3 microservices.

1. tabletennis-data - Admin service, maintain data.
2. tabletennis-report - Client service. Shows data for current competition only.
3. tabletennis-gateway - Gateway service.

Commit 13/6:
- Git: switched work on dev branch
- Service classes refactored to work as implementation. 
- Service classes refactored to use Repository, instead of calling another service.
- CompetitionPlayer and CompetitionPlayerPK classes refactored: 
	a) @EmbeddedId instead of @IdClass
	b) Reference to Competition and Player instead of just id's
	
	
TODO: 
- Entity check if they need refactoring: Group (group name), Competition(remove competitionPlayers?), Game (remove groupNum?)
- Unit testing: JUnit & Mockito
- Remove Jackson annotations from Entity objects

