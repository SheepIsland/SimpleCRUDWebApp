import React, { Component } from 'react';
import EmployeeDataService from "../service/EmployeeDataService";

class ListEmployeesComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            employees: [],
            message: null
        }
        this.updateEmployeeClicked = this.updateEmployeeClicked.bind(this)
        this.addEmployeeClicked = this.addEmployeeClicked.bind(this)
        this.refreshEmployees = this.refreshEmployees.bind(this)
        this.deleteAllEmployeesClicked = this.deleteAllEmployeesClicked.bind(this)
    }

    componentDidMount() {
        this.refreshEmployees();
    }

    refreshEmployees() {
        EmployeeDataService.retrieveAllEmployees()
            .then(
                response => {
                    console.log(response);
                    this.setState({ employees: response.data })
                }
            )
    }

    updateEmployeeClicked(id) {
        console.log('update ' + id)
        this.props.history.push(`/employees/${id}`)
    }

    addEmployeeClicked() {
        this.props.history.push(`/employees/-1`)
    }

    deleteAllEmployeesClicked() {
        EmployeeDataService.deleteAllEmployees();
        window.location.reload();
    }

    render() {
        return (
            <div className="container">
                <h3>Employees</h3>
                <div className="container">
                    <table className="table">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Summary</th>
                            <th>Update</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.employees.map(
                                employee =>
                                    <tr key={employee.id}>
                                        <td>{employee.name}</td>
                                        <td>{employee.salary.summary}</td>
                                        <td><button className="btn btn-info"
                                                    onClick={() => this.updateEmployeeClicked(employee.id)}>Update</button></td>
                                    </tr>
                            )
                        }
                        </tbody>
                        <div className="row">
                            <button className="btn btn-light" onClick={this.addEmployeeClicked}>Add Employee </button>
                            <button className="btn btn-light" onClick={this.deleteAllEmployeesClicked}>Delete All</button>
                        </div>
                    </table>
                </div>
            </div>
        )
    }
}

export default ListEmployeesComponent