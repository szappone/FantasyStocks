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

        <br></br><br></br><br></br>
        
        <h2>Update your portfolio. </h2> 
        <h4> You can only do this on weekends </h4>
        
        <div>
          <h2>Current Portfolio: </h2>
            <Portfolio
              key={this.state.updatedTimes}
              portfolioId={this.state.portfolioId}
              globalService={this.props.globalService}
              callbackParentGetStocks={this.setStocksFromPortfolioObj}
              />
              
          <h2>Recommended Portfolio: </h2>
            <RecommendedPortfolio
              portfolioId={this.state.portfolioId}
              globalService={this.props.globalService}
              callbackParentGetStocks={this.setStocksFromPortfolioObj}
              />
              
            
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