import axios from 'axios';

const API_URL = 'http://localhost:8080';

class AuthService {
  login(username: string, password: string) {
    return axios
      .post(API_URL + '/user/login/', {
        username, 
        password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(emp : any) {
    return axios.post(API_URL + '/user/create/', emp);
  }
}

export default new AuthService();
