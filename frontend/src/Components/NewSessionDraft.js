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
              "stock6","stock7", "stock8", "stock9", "stock10",
              "stock11", "stock12", "stock13"],
      checkedStocks: [],
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

        <br></br><br></br><br></br><br></br>
        <p className="App-intro">
          Draft 9 stocks for your portfolio
        </p>

        <ul>
          L S B
          {this.state.stocks.map((stock) => (
              <li>
                <p>


                  <label for="long">long</label>
                  <input type="radio" id="long" ></input>
                  <label for="short">short</label>
                  <input type="radio" id="short" ></input>
                  <label for="bench">bench</label>
                  <input type="radio" id="bench" ></input>

                    <input type="checkbox" onClick={(cb) => {

                    //console.log(friend);
                    if (this.state.checkedStocks.includes(stock)) {
                      this.state.checkedStocks.splice(this.state.checkedStocks.indexOf(stock), 1);
                    } else {
                      this.state.checkedStocks.push(stock);
                    }
                    console.log(this.state.checkedStocks);
                  }
                }></input>
                  {stock}
                </p>
              </li>
            ))}
          </ul>



          <button className="App-button-tall"  onClick={this.handleDraft}>
            START SESSION
         </button >

   </div>

    );
  }

  handleDraft = () => {
    let service = this.props.globalService;
    let draftedStocks = this.state.checkedStocks.slice();
    //add drafted stock to portfolio

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
