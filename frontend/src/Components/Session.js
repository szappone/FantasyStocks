import React, { Component } from 'react';
import logo from '../logo.svg';
import '../App.css';
import '../Sessions.css';
import {Route, Links} from 'react-router-dom'

const API_PREFIX = "http://localhost:8080";

class Session extends Component {

  constructor() {
    super();
    this.state = {handle: "handle"};

  }

  componentDidMount() {
    setTimeout(this.getHelloWorld, 3000);
  }

  render() {
    return (

      <div className="App">

        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
            <h1 className="App-title">Welcome to the Session!</h1>
        </header>


        <button className="Dash-button">
            <Link to='/Session'>Session</Link>
        </button>

        <button className="Dash-button">
          Enter Session 2
        </button>

        <button className="Dash-button">
          Enter Session 3
        </button>



          <p className="helloParagraph">
            {this.state.helloText}
          </p>
          </div>
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

export default Dashboard;
