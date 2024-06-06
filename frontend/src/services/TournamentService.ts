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

  updateTournament(tournament: any) {
    return axios.post(`${API_URL}/tournament/edit/`, tournament, { headers: authHeader() });
  }

  async tournament(emp : any) {
    await axios.post(API_URL + '/tournament/create/', emp).then()
    await axios.post(API_URL + "/tournamentAdmin/create/" + authHeader().UserId + "/" + emp.name)
  }

  getUserTournaments(userId: number) {
    return axios.get(`${API_URL}/tournamentAdmin/user_tournaments/${userId}`, { headers: authHeader() })
      .then(response => {
        return response.data;
      });
  }



}

export default new TournamentService();
