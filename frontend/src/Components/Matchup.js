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
        p1Score: {},
        p2Score: {},
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
      divArray.push(
        <div> {stockId} {score} </div>
      );
    }
    return divArray;
  }

  render() {
    let p1Name= this.state.matchupObj.player1Name;
    let p2Name= this.state.matchupObj.player2Name;
    
    let p1ScoreArray = this.createDivArrayFromScoreObj(this.state.matchupObj.p1Score);
    let p2ScoreArray = this.createDivArrayFromScoreObj(this.state.matchupObj.p2Score);

    return (

      <div>
        <div>
          <ul className="navul">
            <h2>{p1Name}</h2>
            {p1ScoreArray.map((val) => (
              <li key={val}>{val}</li>
            ))}
          </ul>
        </div>
        
        <div>
          <ul className="navul">
          <h2>{p2Name}</h2>
            {p2ScoreArray.map((val) => (
              <li key={val}>{val}</li>
            ))}
          </ul>
        </div>
      </div>

    );
  }
}

export default Matchup;
