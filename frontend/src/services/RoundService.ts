import axios from 'axios';
import RoundT from '../tranformers/Round';
import { Round } from 'brackets-model';

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
        const allrounds: Round[] = response.data;
        return allrounds.filter(round => 
          Object.keys(filter).every(key => filter[key as keyof Round] === round[key as keyof Round])
        );
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
