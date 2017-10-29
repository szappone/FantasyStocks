import React, { Component } from 'react';
import logo from '../logo.svg';
import '../App.css';
import './Dashboard.css';
import {Route, Link} from 'react-router-dom'

const API_PREFIX = "http://localhost:8080";
const API_HELLO = API_PREFIX + "/hello";

class Dashboard extends Component {

  constructor() {
    super();
    this.state = {sessions: []};

  }

  componentWillReceiveProps() {
    console.log(this.props.globalService);
    this.setState({sessions: this.props.globalService.getSessions()});
  }

  render() {

    return (

      <div className="App">

        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
            <h1 className="App-title">Welcome to the dashboard!</h1>
        </header>

        <h2> Your Sessions </h2>



        {this.state.sessions.map((session) => (
            <p>Session # {session.sessionId}</p>
        ))}

{
/*

        <button className="Dash-button">
            <Link to='/Session'>Session</Link>
        </button><br/>

        <button className="Dash-button">
          Enter Session 2
        </button><br/>

        <button className="Dash-button">
          Enter Session 3
        </button>
*/
}

  </div>

    );
  }
}

export default Dashboard;
