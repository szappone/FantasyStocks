import React, { Component } from 'react';
import logo from '../logo.svg';
import '../App.css';
import {Route, Link, Switch} from 'react-router-dom'
import Dashboard from './Dashboard'

const API_PREFIX = "http://localhost:8080";
const API_HELLO = API_PREFIX + "/hello";

class Login extends Component {

  constructor() {
    super();
    this.state = {handle: ""};
  }

  componentDidMount() {
    setTimeout(this.getHelloWorld, 3000);
  }

  render() {
    return (

      <div className="App">
      <Route path='/dashboard' component={Dashboard}/>

        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
            <h1 className="App-title">Welcome to FantasyStocks!</h1>
        </header>

          <p className="App-intro">
          Enter Handle
          </p>

          <div>
            <input className="App-text-field" onChange={this.onInputChange}
                type="handle" placeholder="@handle" name="handle" height="20px">
            </input>
          </div>

          <br></br>
          <div className="App-main-login">
            <button className="App-button">
                <Link to='/dashboard' onClick={this.createAccountLaunchDash}>Create Account</Link>
           </button>
          </div>
          <br></br>
          <div className="App-main-login">
            <button className="App-button">
                <Link to='/dashboard' onClick={this.loginLaunchDash}>Login</Link>
           </button >
          </div>
  </div>

    );
  }

  createAccountLaunchDash = () => {
    this.props.globalService.playerHandleExists().then(
      (exists) => {
        if (!exists) {
          //create account and route to dashboard
        }
      }
    );
  }

  loginLaunchDash = () => {
    this.props.globalService.handle = this.state.handle;
  }

  onInputChange = (handleInput) => {
    this.setState({handle:handleInput.target.value});
    this.props.globalService.handle = handleInput.target.value;
  }


  getHelloWorld = () => {
    var that = this;
    fetch(API_HELLO).then(function(response) {
      return response.text()
    }).then(function(jsonData) {
      that.setState({helloText: jsonData});
    });
  }
}

export default Login;
