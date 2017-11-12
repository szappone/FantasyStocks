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
    this.state = {
      handle: "",
      errorMessage: ""
    };
  }

  componentDidMount() {
    //setTimeout(this.getHelloWorld, 3000);
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

          <p id="errorMessage">
            {this.state.errorMessage}
          </p>

          <div>
            <input className="App-text-field" onChange={this.onInputChange}
                type="handle" placeholder="@handle" name="handle" height="20px">
            </input>
          </div>

          <br></br>
          <div className="App-main-login">
          <button className="App-button" onClick={this.createAccountLaunchDash}>
            Create Account
          </button>
          </div>
          <br></br>

          <div className="App-main-login">
            <button className="App-button" onClick={this.loginLaunchDash}>
                Login
           </button >
          </div>
  </div>

    );
  }

  createAccountLaunchDash = () => {
    let service = this.props.globalService;
    service.createPlayer(service.getHandle()).then(
      (response) => {
        this.props.history.push("/dashboard");
      }
    ).catch(
      (error) => {
        this.setState({errorMessage: "Handle already exists"})
      }
    );
  }

  loginLaunchDash = () => {
    let service = this.props.globalService;
    service.getPlayerByPlayerName(service.getHandle()).then(
      (response) => {
        this.props.history.push("/dashboard");
      }
    ).catch(
      (error) => {
        this.setState({errorMessage: "Invalid handle"})
      }
    );
  }

  onInputChange = (handleInput) => {
    let handleText = handleInput.target.value;
    this.setState({handle:handleText});
    this.props.globalService.setHandle(handleText);
    // console.log(this.props.globalService.getHandle());
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
