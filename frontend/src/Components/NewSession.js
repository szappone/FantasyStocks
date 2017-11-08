import React, { Component } from 'react';
import logo from '../logo.svg';
import '../App.css';
import {Route, Link, Switch} from 'react-router-dom'
import Dashboard from './Dashboard'

const API_PREFIX = "http://localhost:8080";
const API_HELLO = API_PREFIX + "/hello";

class NewSession extends Component {

  constructor() {
    super();
    this.state = {
      name: "",
      friends: ["friend1", "friend2", "friend3"]
    };
  }

  componentDidMount() {
    setTimeout(this.getHelloWorld, 3000);
  }


  setSessionDataInState() {
    if (this.props && this.props.globalService) {
      this.props.globalService.getSessions().then(
        (data) => {
          this.setState({sessions: data})
        }
      );
      this.setState({name: this.props.globalService.name});
      console.log("successfully set our state from global service");
    } else {
      console.log("cant access global service yet!");
    }
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
            {this.state.friends.map((friend) => (
              console.log(friend),
                <li><p>{friend}</p></li>
              ))}
            </ul>
          </header>

          <br></br>
          <br></br>
          <br></br>
          <div className="App-main-login">
            <button className="App-button">
                <Link to='/newsessiondraft' onClick={this.launchNewSessionDraft}>Next</Link>

           </button >
          </div>
   </div>

    );
  }

  createNewSession = () => {

  }

  launchNewSessionDraft = () => {
    this.props.globalService.name = this.state.name;
  }

  onInputChange = (newSessionInput) => {
    this.setState({name:newSessionInput.target.value});
    this.props.globalService.name = newSessionInput.target.value;
  }

}

export default NewSession;
