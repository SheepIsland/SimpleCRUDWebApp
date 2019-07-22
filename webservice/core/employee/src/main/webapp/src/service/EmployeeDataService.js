import axios from 'axios'

class EmployeeDataService {

    retrieveAllEmployees() {
        return axios.get(`http://localhost:9000/employees`);
    }

    retrieveEmployee(id) {
        return axios.get(`http://localhost:9000/employees/${id}`);
    }

    updateEmployee(id, sum) {
        return axios.put(`http://localhost:9000/updateEmployee/${id}`,  null, { params: {
                sum,
            }});
    }

    createEmployee(employee) {
        return axios.post(`http://localhost:9000/createEmployee`, employee);
    }

    deleteAllEmployees(employee) {
        return axios.delete(`http://localhost:9000/deleteEmployees`);
    }
}

export default new EmployeeDataService()