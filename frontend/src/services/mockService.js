function getMockService() {
  return {
    handle: "not set",
    playerIds: ["ak", "shiv", "sarah", "ajo", "archan"],
    allSessions: [
      createSessionObject(1, "first session",
          ["ak", "ajo", "sarah"],
          [1, 2, 3],
          {
            "ak": 1,
            "ajo": 2,
            "sarah": 3
          },
          {
            "ak": 100,
            "ajo": 250,
            "sarah": 324
          }
        ),
        createSessionObject(2, "cool session",
            ["shiv", "ajo", "sarah", "archan"],
            [1, 2, 3, 4, 5],
            {
              "shiv": 1,
              "ajo": 2,
              "sarah": 3,
              "archan": 4
            },
            {
              "shiv": 100,
              "ajo": 250,
              "sarah": 324,
              "archan": 42
            }
          ),
          createSessionObject(3, "fun third session",
              ["shiv", "archan", "sarah", "ajo"],
              [1, 2, 3, 4],
              {
                "shiv": 1,
                "ajo": 2,
                "sarah": 3,
                "archan": 4
              },
              {
                "shiv": 100,
                "ajo": 250,
                "sarah": 324,
                "archan": 42
              }
            ),
          ],


    setHandle: function(input) {
      this.handle = input;
    },

    getHandle: function() {
      return this.handle;
    },

    getSessions: function() {
      //let sessionsPlayerIsIn = [];
      // let i = 0;
      // for (i = 0; i < this.allSessions.length; i++) {
      //   if (this.allSessions[i].players.includes(this.handle)) {
      //     sessionsPlayerIsIn.push(this.allSessions[i]);
      //   }
      // }
      return new Promise((resolve, reject) => {
          // console.log(this.allSessions);
          resolve(this.allSessions);
        }
      );
    },



    getPlayerByPlayerName: function(inputName) {
      return new Promise((resolve, reject) => {
        if (this.playerIds.includes(inputName)) {
          let playerObject = {
            playerName: inputName
          };
          resolve(playerObject);
        } else {
          reject(createErrorMessage("Player does not exist."));
        }
      });
    },

    getAllPlayers: function() {
      return new Promise((resolve, reject) => {
        resolve(this.playerIds);
      })
    },

    createPlayer: function(inputName) {
      return new Promise((resolve, reject) => {
        this.getPlayerByPlayerName(inputName)
          .then(() => reject(createErrorMessage("Player already exists")))
          .catch((error) => {
            this.playerIds.push(inputName);
            resolve(createPlayerObject(inputName));
          });
      });
    },

    createSession: function(sessionName, playerIds) {
      return new Promise((resolve, reject) => {
        if (!sessionName) {
          reject(createErrorMessage("need a session name"));
        }
        let createdSession = createSessionObject(
            this.allSessions.length + 1,
            sessionName,
            playerIds,
            [],
            {},
            {}
          );
        this.allSessions.push(createdSession);
        resolve(createdSession);

      });
    }



    };
}

function createPlayerObject(name) {
  return {
    playerName: name
  };
}

function createErrorMessage(messageString) {
  return {
    message: messageString
  }
}

function createSessionObject(sessionId, sessionName, players,
   matchups, portfolios, playerScores) {
     return {
       sessionId: sessionId,
       sessionName: sessionName,
       players: players,
       matchups: matchups,
       portfolios: portfolios,
       playerScores: playerScores
     }
}

export default getMockService;
