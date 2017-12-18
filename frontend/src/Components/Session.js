import React, { Component } from 'react';
import fsLogo from '../fsLogo.svg';
import '../App.css';
import FantasyStocksBaseComponent from './FantasyStocksBaseComponent';
import Portfolio from './Portfolio';
import Matchup from './Matchup';


import {Route, Link} from 'react-router-dom';
import Dashboard from './Dashboard';
import NavBar from './NavBar';


class Session extends FantasyStocksBaseComponent {

  constructor(props) {
    super(props);
    this.state = {
      currentSession: {
        players: [],
        sessionName: "",
        matchupIds: []
      },
      currentSessionId: 0,
      playerPortfolio: undefined,
      playerPortfolioId: -1
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
        this.setState({playerPortfolioId, playerPortfolioId});
        // console.log("portfolio id: " + playerPortfolioId);
        // console.log(currSession);
        service.getPortfolioById(playerPortfolioId)
          .then((response) => {
            return response.json();
          })
          .then((portfolioObj) => {
            this.setState({playerPortfolio: portfolioObj});
          });
      });
    } else {
      console.log("cant access global service yet!");
    }
  }

  render() {
    console.log(this.state.playerPortfolio);
    let matchupInfo = "";
    if (this.state.playerPortfolio) {
      if (this.state.playerPortfolio.longs.length > 0) {
        matchupInfo = 
        <div>
          <Portfolio
            portfolioId={this.state.playerPortfolioId}
            globalService={this.props.globalService}
            />
            
            <div>
            <button className="App-button-big">
              <Link to={'/updatePortfolio/' + this.state.playerPortfolioId}>Click to Change your portfolio</Link>
            </button>
          </div>
            
        </div>
      } else {
        matchupInfo = <button className="Dash-button">
          <Link to={'/draft/' + this.state.playerPortfolioId}>{"ENTER DRAFT"}</Link>
        </button >;
      }
    } else {
      //console.log("waiting");
    }

    // NavBar stuff
    let navBar = "";
    if (this.props && this.props.globalService) {
      navBar = <NavBar globalService={this.props.globalService}
                       history={this.props.history}/>;
    }

    return (

      <div className="App">
        {navBar}
        <div>
        <header className="App-header">
          <img src={fsLogo} className="App-logo" alt="logo" />
            <h1 className="App-title">Welcome to Session: {this.state.currentSession.sessionName}</h1>
        </header>
        </div>

        <br/><br/><br/><br/>
        <div><h4> Players in this Session </h4></div>

        <ul>
        {this.state.currentSession.players.map((player) => (
            <li key={player}><p>{player}</p></li>
          ))}
        </ul>

        <div className="portfolioBox" id="portfolioDiv" >
          {
            matchupInfo
          }
        </div>

        <div className="matchupBox" id="matchupDiv">
          <h2>Matchups in this Session</h2>
          <ul id="matchupList">
            {this.state.currentSession.matchupIds.map((matchupId) => (
              <li key={matchupId}> <Matchup globalService={this.props.globalService} matchupId={matchupId}/> </li>
            ))}
          </ul>
        </div>
      </div>

    );
  }
}

export default Session;
