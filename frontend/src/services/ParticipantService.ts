import axios from 'axios';
import { OmitId } from 'brackets-manager';
import Participant from '../types/Participant';

const API_URL = 'http://localhost:8080/participant';

class ParticipantService {
  async create(value: OmitId<Participant> | OmitId<Participant>[]){
    
    value = Array.isArray(value) ? value : [value];
    await axios.post(API_URL + '/create/', value).then(
          response => {return response} 
        )
  }

  async select(filter?: number | Partial<Participant>): Promise<Participant | Participant[] | null> {
    if(filter == undefined) {
      return  await axios.get(API_URL + "/all/").then(response => {
        return response.data as Participant[];
      });
    } else if(typeof filter == 'number') {
      return await axios.get(API_URL + "/find/" + filter).then(response => {
        return response.data;
      })
    } else {
      return await axios.get(API_URL + "/all/").then(response => {
        const allParticipant: Participant[] = response.data;
        return allParticipant.filter(participant => 
         participant.tournamentId == filter.tournamentId);
      })
    }
  }

  async delete(filter?: Participant | Partial<Participant>){
    if (filter == undefined) return;
  }
}

export default new ParticipantService();
