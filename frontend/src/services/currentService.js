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

          getPortfolioById: realService.getPortfolioById,

          draftPortfolio: realService.draftPortfolio,

          getMatchupById: realService.getMatchupById,
          
          updatePortfolio: realService.updatePortfolio,
                    
          getAllStocks: realService.getAllStocks,
          
          getRecommendedStocksByPortfolioId: realService.getRecommendedStocksByPortfolioId
    }
}

export default getCurrentService;
