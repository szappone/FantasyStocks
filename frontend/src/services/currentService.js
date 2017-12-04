import getMockService from './mockService'
import getRealService from './realService'

function getCurrentService() {

  // closure!
  let mockService = getMockService();
  let realService = getRealService();

  return {

          setHandle: realService.setHandle,

          getHandle: realService.getHandle,

          isLoggedIn: realService.isLoggedIn,

          setIsLoggedIn: realService.setIsLoggedIn,

          getSessions: realService.getSessions,

          getPlayerByPlayerName: realService.getPlayerByPlayerName,

          getAllPlayers: realService.getAllPlayers,

          createPlayer: realService.createPlayer,

          createSession: realService.createSession,

          getPortfolioById: mockService.getPortfolioById
    }
}

export default getCurrentService;
