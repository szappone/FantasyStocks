function getMockService() {
  return {
    handle: "not set",

    setHandle: function(input) {
      this.handle = input;
    },

    getHandle: function() {
      return this.handle;
    },

    getSessions: function() {
      return new Promise((resolve, reject) => {
        resolve(  [
                {
                  sessionId: 1,
                  players: ["handle1", "handle2", "handle3", "handle4", "handle4", "handle1", "handle2", "handle3", "handle4", "handle4"],
                  matchups: [],
                  portfolios: {}
                },
                {
                  sessionId: 2,
                  players: [],
                  matchups: [],
                  portfolios: {}
                },
                {
                  sessionId: 3,
                  players: [],
                  matchups: [],
                  portfolios: {}
                }
          ]);
        }
      );
    }



    }; //end of return object
}

export default getMockService;
