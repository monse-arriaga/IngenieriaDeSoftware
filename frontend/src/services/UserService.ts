import axios from 'axios';

const API_URL = 'http://localhost:8080';

class UserService {
    enroll(user: number, tournament: any){
        return axios.post(API_URL + '/user/enroll/', 
        { "user":  user, 
          "tournament": tournament as number})
    }
    
}

export default new UserService();
