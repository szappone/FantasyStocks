import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  render() {
    return (
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <h1 className="App-title">Welcome to FantasyStocks</h1>
          </header>
          <p className="App-intro">
            credit to: codeburst.io/building-your-first-react-app-c1f6eb814205
          </p>
          <button className="App-create-account">
            Create Account
          </button>
          <button className="App-login-to-existing">
            Login
          </button >
        </div>
    );
  }
}

export default App;
