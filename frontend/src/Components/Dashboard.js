import React, { Component } from 'react';
import logo from '../logo.svg';
import '../App.css';
import './Dashboard.css';
import {Route, Link} from 'react-router-dom'

const API_PREFIX = "http://localhost:8080";

class Dashboard extends Component {

  constructor(props) {
    super(props);
    this.setSessionDataInState = this.setSessionDataInState.bind(this);
    this.state = {
      sessions: ["a", "b", "c"],
      handle: "unknown"
  };
    console.log("logging props from constructor");
    console.log(this.props);

  }

  componentWillMount(){
    this.setSessionDataInState();
  }

  setSessionDataInState() {
    if (this.props && this.props.globalService) {
      this.setState({sessions: this.props.globalService.getSessions()});
      this.setState({handle: this.props.globalService.handle});
      console.log(this.props.globalService.handle);
      console.log("successfully set our state from global service");
    } else {
      console.log("cant access global service yet!");
    }
  }

  render() {
    let currHandle = this.state.handle;
    console.log("curr Handle: " + currHandle);

    return (

      <div className="App">

        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
<<<<<<< HEAD
            <h1 className="App-title">  Welcome to the dashboard!</h1>
=======
            <h1 className="App-title">Welcome to the dashboard {currHandle}!</h1>
>>>>>>> get dashboard data from service
        </header>


        <h2> Your Sessions </h2>


        {console.log("our sessions: " + this.state.sessions)}
        {console.log("our handle: " + currHandle)}
          {this.state.sessions.map((session) => (
              <p>Session # {session.sessionId} . </p>
          ))}

      </div>

    );
  }
}

export default Dashboard;
