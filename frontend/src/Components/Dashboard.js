import React, { Component } from 'react';
import fsLogo from '../fsLogo.svg';
import '../App.css';
import './Dashboard.css';
import FantasyStocksBaseComponent from './FantasyStocksBaseComponent';
import {Route, Link} from 'react-router-dom';
import NavBar from './NavBar';
import NewSessionDraft from './NewSessionDraft';

const API_PREFIX = "http://localhost:8080";

class Dashboard extends FantasyStocksBaseComponent {

  constructor(props) {
    super(props);
    this.setSessionDataInState = this.setSessionDataInState.bind(this);
    this.state = {
      sessions: ["a", "b", "c"]
  };
    // console.log("logging props from constructor");
    // console.log(this.props);

  }

  componentDidMount(){
    super.componentDidMount();
    this.setSessionDataInState();
  }

  setSessionDataInState() {
    this.props.globalService.getSessions()
      .then(
        (response) => {
          return response.json();
        }
      ).then(
        (jsonData) => {
          this.setState({sessions: jsonData});
        }
      );
  }

  render() {
    let currHandle = this.props.globalService.getHandle();
    let navBar = "";
    if (this.props && this.props.globalService) {
      currHandle = this.props.globalService.getHandle();
      navBar = <NavBar globalService={this.props.globalService}
                       history={this.props.history}/>;
    }

    return (

      <div className="App">
        {navBar}
     <Route path='/session' component={Dashboard}/>

        <header className="App-header">
          <img src={fsLogo} className="App-logo" alt="logo" />

        </header>

        <h1 className="App-title">Welcome to the dashboard {currHandle}!</h1>

        <h2> Your Sessions </h2>


          {this.state.sessions.map((session) => (
              <button className="Dash-button">
                <Link to={'/session/' + session.sessionId}>{session.sessionName}</Link>
            </button >

          ))}

          <h2> Start New Session </h2>
          <button className="Add-new-button">
              <Link to='/newsession'> + </Link>
              </button>
      </div>

    );
  }
}

export default Dashboard;
