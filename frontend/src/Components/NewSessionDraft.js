import React from 'react';
import fsLogo from '../fsLogo.svg';
import '../App.css';
import FantasyStocksBaseComponent from './FantasyStocksBaseComponent';
import NavBar from './NavBar';


class NewSessionDraft extends FantasyStocksBaseComponent {

  constructor(props) {
    super(props);
    this.state = {
      portfolioId: -1,
      stocks: ["stock1", "stock2", "stock3","stock4", "stock5",
              "stock6","stock7", "stock8", "stock9", "stock10",
              "stock11", "stock12", "stock13"],
      stockArrays: {
        longs: [],
        shorts: [],
        bench: []
      },
      errorMessage: "",
      errorMessageStyle: ".errorMessage"
    };
  }

  componentWillMount(){
    this.setPortfolioId();
    this.setStocks();
  }


  componentDidMount() {
    super.componentDidMount();
  }

  setPortfolioId = () => {
    this.setState(
      {portfolioId: parseInt(this.props.match.params.portfolioId)},
      () => {
        console.log("portfolio id: " + this.state.portfolioId);
      });
  }
  
  setStocks = () => {
    this.props.globalService.getAllStocks().then(
      (response) => {
        return response.json();
      }).then(
        (json) => {
          this.setState({stocks: json});
      })
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
            <h1 className="App-title">Draft</h1>
        </header>

        <br></br><br></br><br></br><br></br>
        <p className="App-intro">
          Draft 9 stocks for your portfolio.
        </p>

        <div style={{display: 'flex', justifyContent: 'center'}}>
          <div>
           <p className="App-key" style={{display: 'flex', justifyContent: 'center'}}>
            Play 3 stocks long: make money when their prices go up <br></br>
            Play 3 stocks short: make money when their prices fall <br></br>
            Put 3 stocks on your bench: play them another week
            </p>
          </div>
        </div>
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
          <button className="App-button-tall"  onClick={this.handleDraft}>
            DRAFT
         </button >

   </div>

    );
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

  handleDraft = () => {
    // check valid first
    // for (let arrayName in this.state.stockArrays) {
    //   let array = this.state.stockArrays[arrayName];
    //   if (array.length !== 3) {
    //     this.setErrorMessage("You must select exactly three " + arrayName);
    //     return;
    //   }
    // }

    let service = this.props.globalService;
    service.draftPortfolio(this.state.portfolioId, this.state.stockArrays)
      .then((response) => {
        if (response.ok) {
          this.setSuccessMessage("Successfully Drafted!")
        } else {
          response.json().then((json) => {
            this.setErrorMessage(json);
          });
        }
        console.log("response:");
        console.log(response);
      }).catch((err) => {
        this.setErrorMessage("Couldn't draft");
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
