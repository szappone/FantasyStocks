import React, {Component} from 'react';
import fsLogo from '../fsLogo.svg';
import '../App.css';
import NavBar from './NavBar';


class Portfolio extends Component {

  constructor(props) {
    super(props);
    this.state = {
      portfolioId: props.portfolioId,
      portfolioObj: null
    };
    console.log("portfolio props");
    console.log(props);
  }

  componentWillMount(){
    this.props.globalService.getPortfolioById(this.state.portfolioId)
      .then((response) => {
        return response.json();
      }).then((json) => {
        console.log("json");
        console.log(json);
        this.setState({portfolioObj: json});
        
        if (this.props.callbackParentGetStocks) {
          this.props.callbackParentGetStocks(json);
        }
      });
  }

  render() {
    
    if (this.state.portfolioObj !== null) {
      return (

        <div className="portfolioBox">
        <table width="300" align="center">
          <tbody>
          <tr>
           <th className="App-green">
              Longs:
              <ul>
                {this.state.portfolioObj.longs.map((stockId) => (
                  <li key={stockId}>{stockId}</li>
                ))}
              </ul>
          </th>
          <th className="App-red">
          Shorts:
            <ul>
              {this.state.portfolioObj.shorts.map((stockId) => (
                <li key={stockId}>{stockId}</li>
              ))}
            </ul>
          </th>
          <th className="App-grey">
          Bench:
            <ul>
              {this.state.portfolioObj.bench.map((stockId) => (
                <li key={stockId}>{stockId}</li>
              ))}
            </ul>
              </th>
        </tr>
        </tbody>
      </table>
        </div>
      );
    } else {
      return (
        <div>
          Empty Portfolio
        </div>
      );
    }
  }


}

export default Portfolio;
