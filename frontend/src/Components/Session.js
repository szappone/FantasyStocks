import React, { Component } from 'react';
import logo from '../logo.svg';
import '../App.css';

import {Route, Link} from 'react-router-dom'
import Dashboard from './Dashboard'

const API_PREFIX = "http://localhost:8080";
const API_HELLO = API_PREFIX + "/hello";

class Session extends Component {

  constructor(props) {
    super(props);
    this.state = {
      currentSession: {players: ["a", "b", "c"]},
      currentSessionId: 0
    };

  }

  componentWillMount(){
    this.setCurrentSessionId();
  }


  componentDidMount() {
    setTimeout(this.getHelloWorld, 3000);
  }

  setCurrentSessionId() {
    this.setState({
      currentSessionId: parseInt(this.props.match.params.sessionId)
    },
      this.setSessionState);
  }

  setSessionState() {
    if (this.props && this.props.globalService) {
      console.log("all sessions: " + this.props.globalService.getSessions());

      let currSession = this.props.globalService.getSessions().find(s => s.sessionId == this.state.currentSessionId);
      if (currSession) {
         this.setState({currentSession: currSession});
      } else {
          console.log("couldnt get currentSession");
      }
      this.setState({handle: this.props.globalService.handle});
      console.log(this.props.globalService.handle);
      console.log("successfully set our state from global service");
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
            <h1 className="App-title">Welcome to session #{this.props.match.params.sessionId}</h1>
        </header>

        <ul>
        {this.state.currentSession.players.map((player) => (
          console.log(player),
            <li>{player}</li>
          ))}
        </ul>
      </div>

    );
  }
}

export default Session;
