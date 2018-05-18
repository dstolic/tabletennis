# tabletennis
Table tennis app
To start app, you'll have complete few steps.

1. Create database.
Create script is at /tabletennis-data/src/main/resources/db-script/Dump20180516.sql. Script is made as MySQL db dump.
2. Start Eureka server (microservice, project tabletennis-eureka-server). To check Eureka status, try http://localhost:1111/
3. Start web server (microservice, project tabletennis-web). 
4. Start data server (microservice, project tabletennis-data). 

To check app, try http://localhost:3333/players.

Completed functionalities:

1. Players list - http://localhost:3333/players.
2. Competitions list - http://localhost:3333/competition
3. Add/update competition - http://localhost:3333/competition/{ID} (link in the first column of the list)
4. Add players to competition (in progress). 
  Click to 'Add players' button. 
