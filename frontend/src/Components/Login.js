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
          New Here?
        </p>
        <button className="App-button">
          Create Account
          </button>

          <p className="App-intro">
          Already have an account.
          </p>

          <div class="App-main-login">
                <input class="App-text-field" onChange={this.onInput}
                    type="handle" placeholder="@handle" name="handle" height="20px">
                </input>
          <button className="App-button" onClick={this.launchDash}>
              <Link to='/dashboard'>Login</Link>
         </button >
          <p className="helloParagraph">
            {this.state.helloText}
          </p>
          </div>
  </div>

    );
  }

  launchDash = () => {

  }

  onInput = (handleInput) => {
    //console.log(this.state.handle);
    this.setState({handle:handleInput.target.value});
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
