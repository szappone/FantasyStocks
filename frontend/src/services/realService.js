import 'whatwg-fetch';


const API_PREFIX = "http://localhost:8080";
const GET_SESSIONS = API_PREFIX + "/sessions?playerName=";
const CREATE_SESSION = API_PREFIX + "/session";
const CREATE_PLAYER = API_PREFIX + "/player";
const GET_ALL_PLAYERS = API_PREFIX + "/players";
const GET_THIS_PLAYER = API_PREFIX + "/players?playerName=";
const DRAFT_PORTFOLIO = API_PREFIX + "/portfolio/";
const GET_PORTFOLIO = API_PREFIX + "/portfolio/";
const GET_MATCHUP = API_PREFIX + "/matchups/";
const GET_STOCKS= API_PREFIX + "/stocks";
const UPDATE_PORTFOLIO = API_PREFIX + "/portfolio/";

function getRealService() {

  const defaultHeaders = new Headers({'Content-Type': 'application/json'});
  return {

        handle: null,
        playerIsLoggedIn: false,

        setHandle: function(input) {
          this.handle = input;
        },

         getHandle: function() {
           if (this.handle !== null && this.handle !== undefined) {
             return this.handle;
           }
          if (this.isLoggedIn()) {
            return window.localStorage.getItem("playerHandle");
          }
          return null;
         },

         isLoggedIn: function() {
           if (this.playerIsLoggedIn === true) {
             return true;
           }
           let storedHandle = window.localStorage.getItem("playerHandle");
           // console.log("stored handle: " + storedHandle);
           // console.log(typeof(storedHandle));
           if (storedHandle === null || storedHandle === undefined || storedHandle === "null") {
             return false;
           }
           return true;
         },

         setIsLoggedIn: function(input) {
           if (input === true) {
             window.localStorage.setItem("playerHandle", this.handle);
           } else {
             window.localStorage.setItem("playerHandle", null);
           }
         },

         getSessions: function() {
            return fetch(GET_SESSIONS + this.getHandle(), {
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
          },

          draftPortfolio: function(portfolioId, body) {
            body = JSON.stringify(body);
            return fetch(DRAFT_PORTFOLIO + portfolioId, {
              headers: new Headers({'Content-Type': 'application/json'}),
              method: "POST",
              body: body
            });
          },
          
          updatePortfolio: function(portfolioId, body) {
            body = JSON.stringify(body);
            return fetch(UPDATE_PORTFOLIO + portfolioId, {
              headers: new Headers({'Content-Type': 'application/json'}),
              method: "PUT",
              body: body
            });
          },

          getPortfolioById: function(portfolioId) {
            return fetch(GET_PORTFOLIO + portfolioId);
          },
          
          getMatchupById: function(matchupId) {
            return fetch(GET_MATCHUP + matchupId);
          },
          
          getAllStocks: function() {
            return fetch(GET_STOCKS);
          }


    //end return statement
    }
}

export default getRealService;
