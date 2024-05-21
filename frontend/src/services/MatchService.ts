import axios from 'axios';
import MatchT from '../tranformers/Match';
import { Match } from 'brackets-model';

const API_URL = 'http://localhost:8080/match';
const tranformer = new MatchT()

class matchService {

  async create(value: Match | Match[]){
    
    value = Array.isArray(value) ? value : [value];

    value.forEach( async match => {
        const mymatch = tranformer.from(match);

        await axios.post(API_URL + '/create/', {
          match: {
            id: mymatch.id,
            childcount: mymatch.childCount,
            matchStatus: mymatch.matchStatus,
            stage: mymatch.stage,
            group: mymatch.group,
            round: mymatch.round,
            number: mymatch.number
          },
          result1: mymatch.opponentOneResult,
          result2: mymatch.opponentTwoResult
        })
    } )

  }

  async select(filter?: number | Partial<Match>): Promise<Match | Match[] | null> {
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
        const allmatchs: Match[] = response.data;
        return allmatchs.filter(match => 
          Object.keys(filter).every(key => filter[key as keyof Match] === match[key as keyof Match])
        );
      });
    }
  }

  async update(filter: number | Partial<Match>, value: Match | Partial<Match>){ 
    if (typeof filter == "number" && ((value: Match): value is Match => !!value.id)) {
      await axios.post(API_URL + "/edit/", tranformer.from(value as Match))
    } else {
      const matchs = await this.select(filter);
      const matchsArray = matchs == null ? [] : Array.isArray(matchs) ? matchs : [matchs];

      for (const match of matchsArray) {
        const updatedmatch = { ...match, ...value };
        await axios.post(API_URL + "/edit/", tranformer.from(updatedmatch));
      }
    }
  }

  async delete(filter?: Match | Partial<Match>){
    if (filter == undefined) return;
    if(((value: Match): value is Match => !!value.id)) {
      return await axios.post(API_URL + "/delete/", filter)
    } else {
      let matchs = await this.select(filter);
      matchs = matchs == null ? [] : Array.isArray(matchs) ? matchs : [matchs] 
      matchs.forEach(element => {
        this.delete(element);
      });
    }
  }

}

export default new matchService();
