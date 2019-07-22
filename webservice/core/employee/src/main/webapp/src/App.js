import React, { Component } from 'react';
import './App.css';
import SimpleApp from './component/SimpleApp';

class App extends Component {
  render() {
    return (
        <div className="container">
          <SimpleApp />
        </div>
    );
  }
}

export default App;