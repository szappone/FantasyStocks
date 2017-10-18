import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

const API_PREFIX = "http://localhost:8080";
const API_HELLO = API_PREFIX + "/hello";

class App extends Component {

  constructor() {
    super();
    this.state = {helloText: "about to send a request to " + API_HELLO + "..."};
  }

  componentDidMount() {
    setTimeout(this.getHelloWorld, 3000);
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to FantasyStocks!</h1>
        </header>
        <p className="App-intro">
          New Here?
        </p>
        <button className="App-create-account">
            Create Account
        </button>
        <p className="App-intro">
          Already have an account.
        </p>
        <button className="App-login-to-existing">
           Login
       </button >
        <p className="helloParagraph">
          {this.state.helloText}
        </p>
      </div>
    );
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

export default App;
