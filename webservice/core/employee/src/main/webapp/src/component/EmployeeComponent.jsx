import React, { Component } from 'react';
import { Formik, Form, Field} from 'formik';
import EmployeeDataService from "../service/EmployeeDataService";

class EmployeeComponent extends Component {
    constructor(props) {
        super(props)

        this.onSubmit = this.onSubmit.bind(this)

        this.state = {
            id: this.props.match.params.id,
            summary: 0,
            name: ''
        }

    }

    componentDidMount() {

        console.log(this.state.id)

        if (this.state.id == -1) {
            return
        }

        EmployeeDataService.retrieveEmployee(this.state.id)
            .then(response => this.setState({
                name: response.data.name,
                summary: response.data.salary.summary
            }))
    }

    onSubmit(values) {
        let employee = {
            name: values.name,
            sum: values.summary
        }
        console.log(employee)

        console.log(this.state.id )
        if (this.state.id == -1) {
            EmployeeDataService.createEmployee(employee)
                .then(() => this.props.history.push('/employees'))

        } else {
            EmployeeDataService.updateEmployee(this.state.id, values.summary)
                .then(() => this.props.history.push('/employees'))

        }

        console.log(values);
    }

    render() {
        let field;
        if (this.state.id == -1) {
            field = <Field className="form-control" type="text" name="name" enabled="true"/>
        } else {
            field = <Field className="form-control" type="text" name="name" disabled />
        }
            return (
                <div>
                    <div className="container">
                        <h3>Employee</h3>
                        <Formik onSubmit={this.onSubmit}
                                initialValues={this.state}
                                enableReinitialize={true}
                        >
                            {
                                (props) => (
                                    <Form>
                                        <fieldset className="form-group">
                                            <label>Name</label>
                                            {field}
                                        </fieldset>

                                        <fieldset className="form-group">
                                            <label>Summary</label>
                                            <Field className="form-control" type="text" name="summary" enabled="true"/>
                                        </fieldset>
                                        <button className="btn btn-info" type="submit">Save</button>
                                    </Form>
                                )
                            }
                        </Formik>
                    </div>
                </div>
            )
        }
}

export default EmployeeComponent