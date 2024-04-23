import axios from 'axios';

const API_URL = 'http://localhost:8080';

class UserService {
  getPublicContent() {
    return axios.get(API_URL + 'all');
  }

  tournament(emp : any) {
    return axios.post(API_URL + '/tournament/create/', emp);
  }



}

export default new UserService();
