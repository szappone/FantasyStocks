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
    this.state = {helloText: "about to send a request to " + API_HELLO + "...",
      globalSession: this.getGlobalSession()
    };
  }

  componentDidMount() {
    setTimeout(this.getHelloWorld, 3000);
  }

  render() {
    return (
      <div>
      <Login className="App-Login" handle="dummyHandle" globalSession={this.state.globalSession}></Login>
        <Switch>
            <PropsRoute exact path='/dashboard' component={Dashboard} globalSession={this.state.globalSession}/>
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

  getGlobalSession() {
    // ensure global session is a singleton
    if (this.state && this.state.globalSession) {
      return this.state.globalSession;
    }
    return {
        handle: "not set"
    };
  }
}

// This is how we pass properties to routes
const renderMergedProps = (component, ...rest) => {
  const finalProps = Object.assign({}, ...rest);
  return (
    React.createElement(component, finalProps)
  );
}

// This is how we pass properties to routes
const PropsRoute = ({ component, ...rest }) => {
  return (
    <Route {...rest} render={routeProps => {
      return renderMergedProps(component, routeProps, rest);
    }}/>
  );
}


export default App;
