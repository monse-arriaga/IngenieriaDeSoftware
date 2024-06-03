import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080';

class TournamentService {
  getPublicContent() {
    return axios.get(API_URL + '/tournament/all/')
        .then(response =>  {
          return response.data
        } )
  }

  getTournamentByName(name: String) {
      return axios.get(API_URL + "/tournament/find/" + name)
          .then(response => {
            return response.data
          })
  }

  async tournament(emp : any) {
    await axios.post(API_URL + '/tournament/create/', emp).then()
    await axios.post(API_URL + "/tournamentAdmin/create/" + authHeader().UserId + "/" + emp.name)
  }



}

export default new TournamentService();
