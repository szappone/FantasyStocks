import FantasyStocksBaseComponent from './FantasyStocksBaseComponent';
import React from 'react';
import '../App.css';
import fsLogo from '../fsLogo.svg';
import NavBar from './NavBar';
import Portfolio from './Portfolio';
import RecommendedPortfolio from './RecommendedPortfolio';


class UpdatePortfolio extends FantasyStocksBaseComponent {

  constructor(props) {
    super(props);
    this.state = {
      portfolioId: parseInt(this.props.match.params.portfolioId),
      stocks: [],
      stockArrays: {
        longs: [],
        shorts: [],
        bench: []
      },
      recommendedStockArrays: {
        longs: [],
        shorts: [],
        bench: []
      },
      errorMessage: "",
      errorMessageStyle: ".errorMessage",
      updatedTimes: 0,
    }
  }

  setStocksFromPortfolioObj = (portfolioObj) => {
    let stocks = [];
    stocks = stocks.concat(portfolioObj.longs);
    stocks = stocks.concat(portfolioObj.shorts)
    stocks = stocks.concat(portfolioObj.bench)
    this.setState({stocks: stocks});
  }

  render() {

    let navBar = "";
    if (this.props && this.props.globalService) {
      navBar = <NavBar globalService={this.props.globalService}
                       history={this.props.history}/>;
    }
    return (
      <div className="App">
        {navBar}

        <header className="App-header">
          <img src={fsLogo} className="App-logo" alt="logo" />
        </header>

        <br></br>

        <h2>Update your portfolio. </h2>
        <h4> You can only do this on weekends </h4>

        <div className="matchupBox">
          <table width="320">
            <tr>
              <td>
              <h2>Current Portfolio: </h2>
                <Portfolio
                  key={this.state.updatedTimes}
                  portfolioId={this.state.portfolioId}
                  globalService={this.props.globalService}
                  callbackParentGetStocks={this.setStocksFromPortfolioObj}
                  />
              <td></td>
              <h2>Recommended Portfolio: </h2>
                <RecommendedPortfolio
                  portfolioId={this.state.portfolioId}
                  globalService={this.props.globalService}
                  callbackParentGetStocks={this.setStocksFromPortfolioObj}
                  />
              </td>
            </tr>

            </table>
        </div>

        <br/><br/>
        <div className="descriptionBox">
          <h2>Why are these recommended for me?</h2>
          We draw your recommendations by consolidating past market perfomance of the stocks in your portfolio to consider the optimal<br></br>
          play for the upcoming week. We use two market concepts here: Golden Cross and Death Cross. The Golden Cross is the first day that the<br></br>
          25 day simple moving average is greater the 100 day moving average, and the Death Cross is the first day that it falls below.<br></br><br></br>

          Golden Crosses are a strong indications to buy a stock and Death Crosses are strong indications to short it. We score attractive<br></br>
          Golden Crossed stocks at the top of your portfolio, and Death Crosses at the bottom. The resultant ordering is fine tuned in order of the %<br></br>
          gain of each stocks 15 day moving average over their 50 day moving average. <br></br><br></br>

          The algorithm uses these metrics to provide great guidance on which stocks you should consider play long, short, and bench this week.<br></br>
          But dont stop here. Read the news, follow the markets, and choose a slate you like before you settle on next weeks roster.<br></br>
        </div>

        <br/><br/>
        <h3>New Portfolio: </h3>
        <ul className="navul">
                {this.state.stocks.map((stock) => (
                    <li key={stock} className="navli">
                      <h4>{stock}</h4>
                        <form>
                        <input type="radio" name={stock} onClick={this.handleRadio(stock, "longs")}></input>
                          <label>long </label>
                          <input type="radio" name={stock} onClick={this.handleRadio(stock, "shorts")}></input>
                          <label >short </label>
                          <input type="radio" name={stock} onClick={this.handleRadio(stock, "bench")}></input>
                          <label >bench </label>
                          <input type="reset" onClick={this.handleRadio(stock, "reset")}></input>
                        </form>
                    </li>

                ))}
          </ul>
          <div className={this.state.errorMessageStyle}>
            {this.state.errorMessage}
          </div>
          <button className="App-button-tall"  onClick={this.handleUpdatePortfolio}>
            UPDATE
         </button >


      </div>
    )
  }


  clearStockFromArrays = (stock) => {
    for (let arrayName in this.state.stockArrays) {
      let array = this.state.stockArrays[arrayName];
      if (array.includes(stock)) {
        array.splice(array.indexOf(stock), 1);
      }
    }
  }

  //returns a function: the onclick handler of radio button of stock
  //target array is one of: long short bench
  handleRadio = (stock, targetArray) => {
    //this.clearMessage();
    let currStock = stock;
    let currArray = targetArray;
    let handleIt = () => {
      this.clearStockFromArrays(currStock);
      if (targetArray != "reset") {
        this.state.stockArrays[targetArray].push(stock);
      }
      console.log(this.state.stockArrays);
    }
    return handleIt;
  }

  handleUpdatePortfolio = () => {

    //check valid first
    for (let arrayName in this.state.stockArrays) {
      let array = this.state.stockArrays[arrayName];
      if (array.length !== 3) {
        this.setErrorMessage("You must select exactly three " + arrayName);
        return;
      }
    }

    let service = this.props.globalService;
    service.updatePortfolio(this.state.portfolioId, this.state.stockArrays)
      .then((response) => {
        if (response.ok) {
          this.setSuccessMessage("Successfully Updated!");
          this.setState({updatedTimes: this.state.updatedTimes + 1});
        } else {
          response.json().then((json) => {
            this.setErrorMessage(json);
          });
        }
        console.log("response:");
        console.log(response);
      }).catch((err) => {
        this.setErrorMessage("Couldn't update portfolio");
        console.log(err.message);
      });
  }

  //set the message and class name
  setErrorMessage = (msg) => {
    this.setMessageState(msg);
    this.setState({errorMessageStyle: "errorMessage"});
  }
  //set the message and class name
  setSuccessMessage = (msg) => {
    this.setMessageState(msg);
    this.setState({errorMessageStyle: "successMessage"});
  }
  clearMessage = () => {
    this.setMessageState("");
  }
  setMessageState = (msg) => {
    if (typeof(msg) === 'object') {
      msg = JSON.stringify(msg);
    }
    this.setState({errorMessage: msg});
  }


}

export default UpdatePortfolio;
