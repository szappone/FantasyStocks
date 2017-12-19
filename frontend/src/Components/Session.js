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
        matchupIds: [],
        currentWeek: 1

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
              <Link to={'/updatePortfolio/' + this.state.playerPortfolioId}>Click to change your portfolio</Link>
            </button>
          </div>

        </div>
      } else {
        matchupInfo = <button className="Dash-button">
          <Link to={'/draft/' + this.state.playerPortfolioId}>{"Time to draft your stocks! Enter the draft phase here."}</Link>
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
    <div>
      {navBar}
      <div className="App somePadding">

        <div>
        <header className="App-header">
          <img src={fsLogo} className="App-logo" alt="logo" />
            <h1>Welcome to Session: {this.state.currentSession.sessionName}</h1>
            <h3>{"Here you can see who you are head to head with this week, and how everyone is doing in their matchups!"}</h3>
        </header>
        </div>

        <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>

        <div>
        <div className="App-border">
            <h2>Your Portfolio</h2>
            {
              matchupInfo
            }
        </div>
        </div>


        <div id="matchupDiv">
          <h1>{"This Week's Matchups"}</h1>

          Your score represents your rosters gain in the market since Monday in this weeks matchup.<br></br>
          Remember, your roster benefits when your long stocks go up and your short stocks fall.<br></br>
          Your effective gain = the increase in your long stocks + the decrease in your short stocks.<br></br>
          This weeks matchup winners will be announced after market close on Friday.

          <ul id="matchupList">
            {this.state.currentSession.matchupIds.map((matchupId) => (
              <li key={matchupId}> <Matchup globalService={this.props.globalService} matchupId={matchupId}/> </li>
            ))}
          </ul>
        </div>

        <div className="App-border-box">
          <h1>Leaderboard: Week {this.state.currentSession.currentWeek} of 10</h1>
          <h4>Represents cumulative number of matchups won</h4>
          <ul>
          {this.state.currentSession.players.map((player) => (
              <li key={player}><p>{player}: 0</p></li>
            ))}
          </ul>
        </div>
        </div>
      </div>
    );
  }
}

export default Session;
