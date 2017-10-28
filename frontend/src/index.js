import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
import { BrowserRouter, Route, Switch} from 'react-router-dom'
import Dashboard from './Components/Dashboard'

ReactDOM.render(
  <BrowserRouter>
    <App/>
  </BrowserRouter>
, document.getElementById('root'))
registerServiceWorker();
