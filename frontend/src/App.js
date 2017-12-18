import React, { Component } from 'react';
import Login from './Components/Login.js';
import './App.css';
import {Switch, Route} from 'react-router-dom';
import Dashboard from './Components/Dashboard';
import Session from './Components/Session';
import NewSession from './Components/NewSession';
import NewSessionDraft from './Components/NewSessionDraft';
import getMockService from './services/mockService';
import getRealService from './services/realService';
import getCurrentService from './services/currentService';
import UpdatePortfolio from './Components/UpdatePortfolio';

class App extends Component {

  constructor() {
    super();
    this.state = {
      globalService: this.getGlobalService()
    };
  }

  componentDidMount() {
    // setTimeout(this.getHelloWorld, 3000);
  }

  render() {
    //<Login className="App-Login" handle="dummyHandle" globalService={this.state.globalService}></Login>
    return (
      <div>
        <Switch>
            <PropsRoute exact path='/' component={Login} globalService={this.state.globalService}/>
            <PropsRoute exact path='/dashboard' component={Dashboard} globalService={this.state.globalService}/>
            <PropsRoute exact path='/session/:sessionId' component={Session} globalService={this.state.globalService}/>
            <PropsRoute exact path='/newsession' component={NewSession} globalService={this.state.globalService}/>
            <PropsRoute exact path='/draft/:portfolioId' component={NewSessionDraft} globalService={this.state.globalService}/>
            <PropsRoute exact path='/updatePortfolio/:portfolioId' component={UpdatePortfolio} globalService={this.state.globalService}/>
        </Switch>
      </div>
    );
  }
  getGlobalService() {
    // ensure global session is a singleton
    if (this.state && this.state.globalService) {
      return this.state.globalService;
    }

    return getCurrentService();
    //return getRealService();
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
