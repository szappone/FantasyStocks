const API_PREFIX = "http://localhost:8080";
const GET_SESSIONS = API_PREFIX + "?playerName=";
const CREATE_SESSION = API_PREFIX + "/session"
const CREATE_PLAYER = API_PREFIX + "/player"
const GET_ALL_PLAYERS = API_PREFIX + "/players"
const GET_THIS_PLAYER = API_PREFIX + "/players?playerName="


function getRealService() {
  return {

        handle: "not set",

        setHandle: function(input) {
          this.handle = input;
        },

         getHandle: function() {
          return this.handle;
         },

         getSessions: function() {
            return fetch(GET_SESSIONS + this.handle);
         },

          getPlayerByPlayerName: function(inputName) {
              return fetch(GET_THIS_PLAYER + inputName);
          },

          getAllPlayers: function() {
              return fetch(GET_ALL_PLAYERS);
          },

          createPlayer: function(inputName) {
            let requestBody = {
              playerName: inputName
            };

            return fetch(CREATE_PLAYER, {
              method: "POST",
              body: requestBody
            });
          },

          createSession: function(sessionName, playerIds) {
            let requestBody = {
            	players: playerIds,
            	sessionName: sessionName
            };

            return fetch(CREATE_SESSION, {
              method: "POST",
              body: requestBody
            });
          }
    }
}

export default getRealService;
