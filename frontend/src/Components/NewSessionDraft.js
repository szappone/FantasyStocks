import React, { Component } from 'react';
import fsLogo from '../fsLogo.svg';
import '../App.css';
import {Route, Link, Switch} from 'react-router-dom'
import Dashboard from './Dashboard'
import FantasyStocksBaseComponent from './FantasyStocksBaseComponent';


class NewSessionDraft extends FantasyStocksBaseComponent {

  constructor() {
    super();
    this.setDraftState = this.setDraftState.bind(this);
    this.state = {
      name: "",
      stocks: ["stock1", "stock2", "stock3","stock4", "stock5",
              "stock6","stock7", "stock8", "stock9"]
    };
  }

  componentWillMount(){
    this.setDraftState();
  }


  componentDidMount() {
    super.componentDidMount();
    console.log("SESSIONNAME: " + this.state.name);
  }

    setDraftState() {
      this.setState({name: this.props.globalService.name});
    }

  render() {
    let sessionName = this.state.name;
    return (


      <div className="App">

        <header className="App-header">
          <img src={fsLogo} className="App-logo" alt="logo" />
            <h1 className="App-title">Welcome to {sessionName}s draft phase!</h1>
        </header>

        <p className="App-intro">
          Step 2 of 2: Draft phase, draft 9 stocks for your portfolio
        </p>

        <ul>
          {this.state.stocks.map((stock) => (
            console.log(stock),
              <li><p>{stock}</p></li>
            ))}
          </ul>

          <button className="App-button-tall">
            START SESSION
         </button >
   </div>

    );
  }

  createNewSession = () => {

  }

  loginLaunchDash = () => {
    this.props.globalService.newSessionName = this.state.newSessionName;
  }

  onInputChange = (handleInput) => {
    this.setState({newSessionName:handleInput.target.value});
    this.props.globalService.handle = handleInput.target.value;
  }

}

export default NewSessionDraft;
