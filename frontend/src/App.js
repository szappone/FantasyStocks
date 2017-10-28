import React, { Component } from 'react';
import logo from './logo.svg';
import Login from './Components/Login.js';
import './App.css';
import {Switch, Route} from 'react-router-dom'
import Dashboard from './Components/Dashboard'

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
      <div>
      <Login className="App-Login" handle="dummyHandle"></Login>
        <Switch>
            <Route exact path='/dashboard' component={Dashboard}/>
        </Switch>
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
