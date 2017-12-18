import React, { Component } from 'react';
import fsLogo from '../fsLogo.svg';
import '../App.css';
import {Route, Link, Switch} from 'react-router-dom'
import Dashboard from './Dashboard'
import FantasyStocksBaseComponent from './FantasyStocksBaseComponent';
import NavBar from './NavBar';

class NewSession extends FantasyStocksBaseComponent {

  constructor() {
    super();
    this.state = {
      name: "",
      allPlayers: ["player1", "player2", "player3",
                   "player4", "player5", "player6",
                   "player7", "player8", "player9"],
      checkedPlayers: [],
      errorMessage: "",
      errorMessageStyle: "errorMessage"
    };
  }


  componentDidMount() {
    super.componentDidMount();
    let service = this.props.globalService;
    service.getAllPlayers()
      .then((data) => {
        return data.json()
      }).then((jsonData) => {
        let playerArray = jsonData.playerNames;
        // remove logged in player from playerArray
        playerArray.splice(playerArray.indexOf(service.getHandle()), 1);
        this.setState({allPlayers: playerArray}); //--> commented out for now
      });
  }

  render() {
    let navBar = "";
    if (this.props && this.props.globalService) {
      navBar = <NavBar globalService={this.props.globalService}
                       history={this.props.history}/>;
    }
    return (

      <div className="App">
        {navBar}
      <Route path='/dashboard' component={Dashboard}/>

        <header className="App-header">
          <img src={fsLogo} className="App-logo" alt="logo" />
            <h1 className="App-title">Create new session</h1>
        </header>

        <br/><br/><br/><br/>

        <p className="App-intro">
          Step 1 of 2: Invite 9 friends to play
        </p>

          Session Name &nbsp;
            <input className="App-text-field-long" onChange={this.onInputChange}
                type="name" placeholder="enter session name" name="name" height="20px">

            </input>
          <br/><br/>
          Select 9 friends
          <div>
            <ul>
              {this.state.allPlayers.map((friend) => (
                  <li>
                    <p>
                      <input type="checkbox" onClick={(cb) => {
                        this.clearMessage();
                        if (this.state.checkedPlayers.includes(friend)) {
                          this.state.checkedPlayers.splice(this.state.checkedPlayers.indexOf(friend), 1);
                        } else {
                          this.state.checkedPlayers.push(friend);
                        }
                        console.log(this.state.checkedPlayers);
                      }
                    }></input>
                      {friend}
                    </p>
                  </li>
                ))}
              </ul>
            </div>

          <div className="App-main-login">
            <button className="App-button" onClick={this.handleCreate}>
                Create
           </button >
           <div className={this.state.errorMessageStyle}>
             {this.state.errorMessage}
           </div>
          </div>
   </div>

    );
  }

  handleCreate = () => {
    let service = this.props.globalService;
    let invitedPlayers = this.state.checkedPlayers.slice();
    
    if (invitedPlayers.length != 9) {
      this.setErrorMessage("Must select exactly 9 players to play with");
      return;
    } else if (this.state.name === "") {
      this.setErrorMessage("Session must have a name");
      return;
    }
    //add logged in player to invited players
    invitedPlayers.push(service.getHandle());
    service.createSession(this.state.name, invitedPlayers)
      .then((response) => {
        if (response.ok) {
          this.props.history.push("/dashboard");
        } else {
          this.setState({errorMessage: "Error: Could not create session"});
        }
      })
      .catch((error) => {
        let displayErrorMessage = "Could not create session";
        if (error.message) {
          displayErrorMessage = error.message;
        }
        this.setState({errorMessage: displayErrorMessage});
      });
  }

  onInputChange = (newSessionInput) => {
    this.setState({name:newSessionInput.target.value});
  }
  
  //set the message and class name
  setErrorMessage = (msg) => {
    this.setMessageState(msg);
    this.setState({errorMessageStyle: "errorMessage"});
  }
  //set the message and class name
  setSuccessMessage = (msg) => {
    this.setMessageState(msg);
    this.setState({errorMessageStyle: "successMessage"});
  }
  clearMessage = () => {
    this.setMessageState("");
  }
  setMessageState = (msg) => {
    if (typeof(msg) === 'object') {
      msg = JSON.stringify(msg);
    }
    this.setState({errorMessage: msg});
  }

}

export default NewSession;
