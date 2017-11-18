import getMockService from './mockService'
import getRealService from './realService'

function getCurrentService() {

  let mockService = getMockService();
  let realService = getRealService();

  return {
        handle: "not set",

        setHandle: function(input) {
          return realService.setHandle(input);
        },

         getHandle: function() {
            return realService.getHandle();
         },

         getSessions: function() {
           return realService.getHandle();
         },

          getPlayerByPlayerName: function(inputName) {
            return realService.getPlayerByPlayerName(inputName);
          },

          getAllPlayers: function() {
            return realService.getAllPlayers();
          },

          createPlayer: function(inputName) {
            return realService.createPlayer(inputName);
          },

          createSession: function(sessionName, playerIds) {
            return mockService.createSession(sessionName, playerIds);
          }
    }
}

export default getCurrentService;
