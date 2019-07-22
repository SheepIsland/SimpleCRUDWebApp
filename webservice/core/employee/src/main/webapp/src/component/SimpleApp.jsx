import React, { Component } from 'react';
import ListEmployeesComponent from "./ListEmployeesComponent";
import EmployeeComponent from "./EmployeeComponent";
import { Switch } from "react-router-dom"
import { BrowserRouter as Router, Route } from 'react-router-dom'
class SimpleApp extends Component {
    render() {
        return (
            <Router>
                <>
                    <Switch>
                        <Route path="/" exact component={ListEmployeesComponent} />
                        <Route path="/employees" exact component={ListEmployeesComponent} />
                        <Route path="/employees/:id" component={EmployeeComponent} />
                    </Switch>
                </>
            </Router>
        )
    }
}

export default SimpleApp