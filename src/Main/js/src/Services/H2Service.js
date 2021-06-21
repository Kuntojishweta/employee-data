import axios from 'axios';

const ROOT = '/api'
const HOST_NAME = 'http://localhost:8080'

const EMP_REST_API_URL = 'http://localhost:8080/api/employees';
const DEP_REST_API_URL = 'http://localhost:8080/api/departments';

class H2Service {

    getEmployees(){
        return axios.get(EMP_REST_API_URL);
    }
    getDepartments(){
        return axios.get(DEP_REST_API_URL);
    }
    getRequest(dbName, perPage, currentPage){
        const queryURL = `${HOST_NAME}${ROOT}/${dbName}?page=${currentPage}&size=${perPage}`;
        console.log(queryURL)
        return axios.get(queryURL);
    }

    async onCreate(dbName, newEmployee) {
        const queryURL = `${HOST_NAME}${ROOT}/${dbName}`;
        try {
            const response = await axios.post(queryURL, newEmployee);
            console.log(response);
        }
        catch (error) {
            console.log(error);
        }
    }
}

export default new H2Service();
