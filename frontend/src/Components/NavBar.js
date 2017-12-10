import React, { Component } from 'react';
import './NavBar.css';
import {Route, Link, Switch} from 'react-router-dom';
import fsLogo from '../fsLogo.svg';
import Dashboard from './Dashboard';
import { withRouter } from 'react-router-dom';



class NavBar extends Component {

  constructor(props) {
    super(props);
    this.state = {
    };
  }


  render() {
    let handle = "_";
    if (this.props && this.props.globalService) {
      handle = this.props.globalService.getHandle();
    }

    return (
      <div>
        <ul>
          <li>Hello, {handle}</li>
          <li className="clickable"><Link to={'/dashboard'}>Home</Link></li>
          <li className="clickable" onClick={this.handleLogout}>Logout</li>
        </ul>
      </div>

    );
  }

  handleLogout = () => {
    this.props.globalService.setIsLoggedIn(false);
    console.log(this.props);
    this.props.history.push("/");
  }
}

withRouter(NavBar);

export default NavBar;
