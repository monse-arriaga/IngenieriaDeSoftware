import axios from 'axios';

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

  tournament(emp : any) {
    return axios.post(API_URL + '/tournament/create/', emp);
  }



}

export default new TournamentService();
