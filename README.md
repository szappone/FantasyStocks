# FantasyStocks 
## Build
* The project root is split up into two subdirectories: backend/ and frontend/
* Building the backend starts up a server that listens on localhost:8080
* Building the frontend starts up a server that listens on localhost:3000


## Backend

To build the backend:
```bash
cd backend
make
```
This should open your browser to localhost:3000 and start the application. It also uses **hot-reload**. This means that when you save a file you are working on, the browser will referesh.

To test the backend:
```bash
cd backend
make test
```
This will run react tests


## Frontend
To build the frontend:
```bash
cd frontend
make
```

To test the frontend:
```bash
cd frontend
make test
```
This will run JUnit tests in ```backend/src/test/java/```

