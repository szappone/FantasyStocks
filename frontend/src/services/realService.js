import 'whatwg-fetch';


const API_PREFIX = "http://localhost:8080";
const GET_SESSIONS = API_PREFIX + "/sessions?playerName=";
const CREATE_SESSION = API_PREFIX + "/session";
const CREATE_PLAYER = API_PREFIX + "/player";
const GET_ALL_PLAYERS = API_PREFIX + "/players";
const GET_THIS_PLAYER = API_PREFIX + "/players?playerName=";


function getRealService() {

  const defaultHeaders = new Headers({'Content-Type': 'application/json'});
  return {

        handle: "not set",
        playerIsLoggedIn: false,

        setHandle: function(input) {
          this.handle = input;
        },

         getHandle: function() {
          return this.handle;
         },

         isLoggedIn: function() {
           return this.playerIsLoggedIn;
         },

         setIsLoggedIn: function(input) {
           this.playerIsLoggedIn = input;
         },

         getSessions: function() {
            return fetch(GET_SESSIONS + this.handle, {
            });
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
            requestBody = JSON.stringify(requestBody);

            return fetch(CREATE_PLAYER, {
              headers: new Headers({'Content-Type': 'application/json'}),
              method: "POST",
              body: requestBody
            });
          },

          createSession: function(sessionName, playerIds) {
            let requestBody = {
            	players: playerIds,
            	sessionName: sessionName
            };
            requestBody = JSON.stringify(requestBody);

            return fetch(CREATE_SESSION, {
              headers: new Headers({'Content-Type': 'application/json'}),
              method: "POST",
              body: requestBody
            });
          }
    }
}

export default getRealService;
