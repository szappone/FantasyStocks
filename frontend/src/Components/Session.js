import React, { Component } from 'react';
import fsLogo from '../fsLogo.svg';
import '../App.css';
import FantasyStocksBaseComponent from './FantasyStocksBaseComponent';


import {Route, Link} from 'react-router-dom'
import Dashboard from './Dashboard'

class Session extends FantasyStocksBaseComponent {

  constructor(props) {
    super(props);
    this.state = {
      currentSession: {
        players: [],
        sessionName: ""
      },
      currentSessionId: 0,
      playerPortfolio: undefined
    };

  }

  componentWillMount(){
    this.setCurrentSessionId();
  }

  setCurrentSessionId() {
    this.setState({
      currentSessionId: parseInt(this.props.match.params.sessionId)
    },
      this.setSessionState);
  }

  setSessionState = () => {
    if (this.props && this.props.globalService) {
      this.props.globalService.getSessions().then(
        (response) => {
          return response.json();
        }
      ).then(
        (jsonData) => {
          let currSession = jsonData.find(s => s.sessionId === this.state.currentSessionId);
          if (currSession) {
             this.setState({currentSession: currSession});
             return currSession;
          } else {
              let error = "couldnt get currentSession";
              console.log("couldnt get currentSession");
              throw error;
          }
        }
      ).then((currSession) => {
        let service = this.props.globalService;
        let handle = service.getHandle();
        let playerPortfolioId = currSession.portfolios[handle];
        // console.log("portfolio id: " + playerPortfolioId);
        // console.log(currSession);
        service.getPortfolioById(playerPortfolioId)
          .then((portfolioObj) => {
            this.setState({playerPortfolio: portfolioObj});
          });
      });
    } else {
      console.log("cant access global service yet!");
    }
  }

  render() {
    //console.log(this.state.currentSession);
    let matchupInfo = "You have not drafted yet!";
    // this if statement depends on how backend will return data when the player hasn't drafted yet
    if (this.state.playerPortfolio) {
      matchupInfo = "Yay";
    }

    return (

      <div className="App">
        <header className="App-header">
          <img src={fsLogo} className="App-logo" alt="logo" />
            <h1 className="App-title">Welcome to Session: {this.state.currentSession.sessionName}</h1>
        </header>

        <div id="portfolioDiv">
          {
            matchupInfo
          }
        </div>

        <h4> Players in this Session </h4>
        <ul>
        {this.state.currentSession.players.map((player) => (
          console.log(player),
            <li><p>{player}</p></li>
          ))}
        </ul>
      </div>

    );
  }
}

export default Session;
