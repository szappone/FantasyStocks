import React, { Component } from 'react';
import '../App.css';
import {Route} from 'react-router-dom';
import Dashboard from './Dashboard';
import fsLogo from '../fsLogo.svg';

class Matchup extends Component {

  constructor(props) {
    super(props);
    this.state = {
      matchupId: props.matchupId,
      matchupObj: {
        player1Name: "",
        player2Name: "",
        p1Score: {
          longs: {},
          shorts: {},
          total: {total: 0}
        },
        p2Score: {
          longs: {},
          shorts: {},
          total: {total: 0}
        },
        activeWeek: 0,
      }
    };
  }

  componentDidMount() {
    this.props.globalService.getMatchupById(this.state.matchupId).then(
      (response) => {
        return response.json();
      }).then(
        (json) => {
          console.log("Matchup: ");
          console.log(json);
          this.setState({matchupObj: json});
      });
  }

  createDivArrayFromScoreObj = (scoreObj) => {
    let divArray = [];
    for (let stockId in scoreObj) {
      let score = scoreObj[stockId];
      score = parseFloat(score);
      score = score.toFixed(4);//Math.round(score, -2);

      let displayColor = "App-green";
      if (score.includes("-")) {
        displayColor = "App-red";
      }
      divArray.push(
        <div className={displayColor}> {stockId} {score} </div>
        //<div className="App-red"> {stockId} {score} </div>
      );
    }
    return divArray;
  }
  
  pushHasntDraftedIfEmpty = (array) => {
    if (array.length===0) {
      array.push(
        <div className="App-red">{"Hasn't drafted"}</div>
      );
    }
  }

  render() {
    let p1Name= this.state.matchupObj.player1Name;
    let p2Name= this.state.matchupObj.player2Name;

    let p1LongArray = this.createDivArrayFromScoreObj(this.state.matchupObj.p1Score.longs);
    let p1ShortArray = this.createDivArrayFromScoreObj(this.state.matchupObj.p1Score.shorts);
    let p2LongArray = this.createDivArrayFromScoreObj(this.state.matchupObj.p2Score.longs);
    let p2ShortArray = this.createDivArrayFromScoreObj(this.state.matchupObj.p2Score.shorts);
    
    this.pushHasntDraftedIfEmpty(p1LongArray);
    this.pushHasntDraftedIfEmpty(p1ShortArray);
    this.pushHasntDraftedIfEmpty(p2LongArray);
    this.pushHasntDraftedIfEmpty(p2ShortArray);

    return (

    <div className="matchupBox">

      <table width="320">
        <tr>
          <th>
            <h2>{p1Name}</h2>
              <h4> Longs </h4>
              <ul className="navul2">
                {p1LongArray.map((val) => (
                  <li key={val}>{val}</li>
                ))}
              </ul>
              <ul className="navul2">
                <h4> Shorts </h4>
                {p1ShortArray.map((val) => (
                  <li key={val}>{val}</li>
                ))}
              </ul>
              <br/>
              <b className="App-total">Total: {this.state.matchupObj.p1Score.total.total}</b>
          </th>
          <th> VS </th>
          <th>
            <h2>{p2Name}</h2>
            <h4> Longs </h4>
            <ul className="navul">
              {p2LongArray.map((val) => (
                <li key={val}>{val}</li>
              ))}
              </ul>
              <ul className="navul">
              <h4> Shorts </h4>
              {p2ShortArray.map((val) => (
                <li key={val}>{val}</li>
              ))}
              </ul>
              <br/>
              <b className="App-total">Total: {this.state.matchupObj.p1Score.total.total}</b> 
          </th>
        </tr>
    </table>
</div>

    );
  }
}

export default Matchup;
