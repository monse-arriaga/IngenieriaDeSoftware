import axios from 'axios';
import RoundT from '../tranformers/Round';
import { Round } from 'brackets-model';
import MyRound from '../types/Round';

const API_URL = 'http://localhost:8080/round';
const tranformer = new RoundT;

class roundService {
  async create(value: Round | Round[]){
    
    value = Array.isArray(value) ? value : [value];

    value.forEach( async round => {
        const myround = tranformer.from(round);
        await axios.post(API_URL + '/create/', myround)
    } )

  }

  async select(filter?: number | Partial<Round>): Promise<Round | Round[] | null> {
    if(filter == undefined) {
      return  await axios.get(API_URL + "/all/").then(response => {
        return response.data;
      });
    } else if(typeof filter == 'number') {
      return await axios.get(API_URL + "/find/" + filter).then(response => {
        return response.data;
      })
    } else {
      return await axios.get(API_URL + "/all/").then(response => {
        const allrounds: MyRound[] = response.data;
        const rounds: Round[] = []
        allrounds.forEach(element => {
          rounds.push(tranformer.to(element))
        });
        if (filter.group_id == undefined) {
          return rounds.filter(round => round.stage_id == filter.stage_id)
        } else {
          return rounds.filter(round => round.group_id == filter.group_id)
        }
      });
    }
  }

  async update(filter: number | Partial<Round>, value: Round | Partial<Round>){ 
    if (typeof filter == "number" && ((value: Round): value is Round => !!value.id)) {
      await axios.post(API_URL + "/edit/", tranformer.from(value as Round))
    } else {
      const rounds = await this.select(filter);
      const roundsArray = rounds == null ? [] : Array.isArray(rounds) ? rounds : [rounds];

      for (const round of roundsArray) {
        const updatedround = { ...round, ...value };
        await axios.post(API_URL + "/edit/", tranformer.from(updatedround));
      }
    }
  }

  async delete(filter?: Round | Partial<Round>){
    if (filter == undefined) return;
    if(((value: Round): value is Round => !!value.id)) {
      return await axios.post(API_URL + "/delete/", filter)
    } else {
      let rounds = await this.select(filter);
      rounds = rounds == null ? [] : Array.isArray(rounds) ? rounds : [rounds] 
      rounds.forEach(element => {
        this.delete(element);
      });
    }
  }
}

export default new roundService();
