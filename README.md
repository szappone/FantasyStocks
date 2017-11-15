# FantasyStocks
## Dependencies
maven
npm
mysql
a mysql database connection available on
    localhost:3306
    username: root
    password: admin

This can be accomplished on Macs with no mysql configuration by installing mysql and running "make db".


## Build
* The project root is split up into two subdirectories: backend/ and frontend/
* Building the backend starts up a server that listens on localhost:8080
* Building the frontend starts up a server that listens on localhost:3000


## Backend

Currently our database is operated locally on the machine. This means to run
the backend the user must have a mysql database connection available on
localhost:3306
username: root
password: admin

To build the backend:
```bash
cd backend
make
```
This will start the Spring app on localhost:8080

To test the backend:
```bash
cd backend
make test
```
This will run JUnit tests in ```backend/src/test/java/```


## Frontend
To build the frontend:
```bash
cd frontend
make
```
This should open your browser to localhost:3000 and start the application. It also uses **hot-reload**. This means that when you save a file you are working on, the browser will refresh.

To test the frontend:
```bash
cd frontend
make test
```
This will run react tests

## Development Environment
### Backend
Import /backend as a maven project into your favorite IDE (IntelliJ, Eclipse)

### Frontend
Anything works, really. AK is using VSCode. Take advantage of **hot reloading**.
