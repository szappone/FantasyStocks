import React, { Component } from 'react';
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
      globalService: this.getGlobalService()
    };
  }

  componentDidMount() {
    setTimeout(this.getHelloWorld, 3000);
  }

  render() {
    //<Login className="App-Login" handle="dummyHandle" globalService={this.state.globalService}></Login>
    return (
      <div>
        <Switch>
            <PropsRoute exact path='/' component={Login} globalService={this.state.globalService}/>
            <PropsRoute exact path='/dashboard' component={Dashboard} globalService={this.state.globalService}/>
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

  getGlobalService() {
    // ensure global session is a singleton
    if (this.state && this.state.globalService) {
      return this.state.globalService;
    }
    return {
        handle: "not set",
        setHandle: function(input) {
          this.handle = input;
        },

        getHandle: function() {
          return this.handle;
        },

        getSessions: function() {
          return [
                {
                	sessionId: 1,
                	players: [],
                  matchups: [],
                  portfolios: {}
                },
                {
                  sessionId: 2,
                  players: [],
                  matchups: [],
                  portfolios: {}
                },
                {
                  sessionId: 3,
                  players: [],
                  matchups: [],
                  portfolios: {}
                }
          ];
        }
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
