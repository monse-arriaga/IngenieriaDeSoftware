import axios from 'axios';

const API_URL = 'http://localhost:8080';

class UserService {
    enroll(user: String, tournament: String){
        return axios.post(API_URL + '/user/enroll/' + user + "/" +  tournament)}
    
    tournaments_enrolled(id: number){
        return axios.get(API_URL + '/user/tournaments_enrolled/' + id.toString())
        .then(response => {
            return response.data
        })
    }
    
}

export default new UserService();
