import React, { Component } from 'react';
import logo from '../logo.svg';
import '../App.css';
import {Route, Link, Switch} from 'react-router-dom'
import Dashboard from './Dashboard'
import FantasyStocksBaseComponent from './FantasyStocksBaseComponent';


class NewSession extends FantasyStocksBaseComponent {

  constructor() {
    super();
    this.state = {
      name: "",
      allPlayers: [],
      checkedPlayers: [],
      errorMessage: ""
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
        this.setState({allPlayers: playerArray});
      });
  }

  render() {
    return (

      <div className="App">
      <Route path='/dashboard' component={Dashboard}/>

        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
            <h1 className="App-title">Create new session</h1>
        </header>


        <p className="App-intro">
          Step 1 of 2: Invite 9 friends to play
        </p>
        <header className="App-header">
          Session Name &nbsp;
            <input className="App-text-field-long" onChange={this.onInputChange}
                type="name" placeholder="enter session name" name="name" height="20px">

            </input>
          <br></br><br></br><br></br>
          Select 9 friends
          <ul>
            {this.state.allPlayers.map((friend) => (
                <li>
                  <p>
                    <input type="checkbox" onClick={(cb) => {
                      //console.log(friend);
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
          </header>

          <br/><br/><br/><br/><br/><br/>
          <div className="App-main-login">
            <button className="App-button" onClick={this.handleCreate}>
                Create
           </button >
            <p>{this.state.errorMessage}</p>
          </div>
   </div>

    );
  }

  handleCreate = () => {
    let service = this.props.globalService;
    let invitedPlayers = this.state.checkedPlayers.slice();
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

}

export default NewSession;
