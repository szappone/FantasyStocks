import React, { Component } from 'react';
import logo from '../logo.svg';
import '../App.css';

import {Route, Link} from 'react-router-dom'
import Dashboard from './Dashboard'

class Session extends Component {

  constructor(props) {
    super(props);
    this.state = {
      currentSession: {
        players: [],
        sessionName: ""
      },
      currentSessionId: 0
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

  setSessionState() {
    if (this.props && this.props.globalService) {
      this.setState({handle: this.props.globalService.handle});

      this.props.globalService.getSessions().then(
          (returnedSessions) => {
            let currSession = returnedSessions.find(s => s.sessionId === this.state.currentSessionId);
            if (currSession) {
               this.setState({currentSession: currSession});
            } else {
                console.log("couldnt get currentSession");
            }
          }
      );


    } else {
      console.log("cant access global service yet!");
    }
  }

  render() {
    console.log(this.state.currentSession)
    return (

      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
            <h1 className="App-title">Welcome to Session: {this.state.currentSession.sessionName}</h1>
        </header>

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
