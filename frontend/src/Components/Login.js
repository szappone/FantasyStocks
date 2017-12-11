import React, { Component } from 'react';
import '../App.css';
import {Route} from 'react-router-dom';
import Dashboard from './Dashboard';
import fsLogo from '../fsLogo.svg';

class Login extends Component {

  constructor() {
    super();
    this.state = {
      handle: "",
      errorMessage: ""
    };
  }

  componentDidMount() {
    if (this.props.globalService.isLoggedIn()) {
      this.props.history.push("/dashboard");
    }
  }

  render() {

    return (

      <div className="App">
      <Route path='/dashboard' component={Dashboard}/>

        <header className="App-header">
          <img src={fsLogo} className="App-logo" alt="logo" />
        </header>

        <h1 className="App-title">Welcome to FantasyStocks!</h1>

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
        console.log("logging create account response");
        console.log(response);
        if (response.ok) {
          service.setIsLoggedIn(true);
          this.props.history.push("/dashboard");
        } else {
          this.setState({errorMessage: "Handle already exists"});
        }
      }
    ).catch(
      (error) => {
        this.setState({errorMessage: "Error: could not create account"});
        console.log(error);
      }
    );
  }

  loginLaunchDash = () => {
    let service = this.props.globalService;
    service.getPlayerByPlayerName(service.getHandle()).then(
      (response) => {
        if (response.ok) {
          service.setIsLoggedIn(true);
          this.props.history.push("/dashboard");
        } else {
          this.setState({errorMessage: "Invalid handle"})
        }
      }
    ).catch(
      (error) => {
        this.setState({errorMessage: "Error: could not log in"})
      }
    );
  }

  onInputChange = (handleInput) => {
    let handleText = handleInput.target.value;
    this.setState({handle:handleText});
    this.props.globalService.setHandle(handleText);
    // console.log(this.props.globalService.getHandle());
  }
}

export default Login;
