import React, { Component } from 'react';
import './App.css';
import Home from './components/Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import TaskEdit from './components/TaskEdit';

class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
            {<Route path='/' exact={true} component={Home} />}
            {<Route path='/task/:id' exact={true} component={TaskEdit} />}
          </Switch>
        </Router>
    )
  }
}

export default App;