import { Component } from 'react';


class FantasyStocksBaseComponent extends Component {
  componentDidMount() {
    if (!this.props.globalService.isLoggedIn()) {
      this.props.history.push("/");
    }
  }
}

export default FantasyStocksBaseComponent;
