import React, {Component} from 'react';
import fsLogo from '../fsLogo.svg';
import '../App.css';
import NavBar from './NavBar';
import Portfolio from './Portfolio';


class RecommendedPortfolio extends Portfolio {

  componentWillMount(){
    this.props.globalService.getRecommendedStocksByPortfolioId(this.state.portfolioId)
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


}

export default RecommendedPortfolio;
