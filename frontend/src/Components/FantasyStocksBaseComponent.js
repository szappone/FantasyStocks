import { Component } from 'react';
import getCurrentService from '../services/currentService'



class FantasyStocksBaseComponent extends Component {
  constructor(props) {
    super(props);
  }

  // componentWillMount() {
  //   if (!this.props.globalService) {
  //     console.log("grabbing service singleton from component");
  //     this.state.globalService = getCurrentService();
  //   } else {
  //     this.state.globalService = this.props.globalService;
  //   }
  // }

  componentDidMount() {
    if (!this.props.globalService.isLoggedIn()) {
      this.props.history.push("/");
    }
  }
}

export default FantasyStocksBaseComponent;
