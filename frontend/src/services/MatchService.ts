import axios from 'axios';
import MatchT from '../tranformers/Match';
import { Match } from 'brackets-model';
import MyMatch from '../types/MyMatch';

const API_URL = 'http://localhost:8080/match';
const tranformer = new MatchT()

class matchService {

  async create(value: Match | Match[]){
    value = Array.isArray(value) ? value : [value];
    const valueT:MyMatch[] = []
    value.forEach(element => {
      valueT.push(tranformer.from(element))
    });
    return await axios.post(API_URL + '/create/', valueT).then(response =>{
      if (valueT.length == 1) return response.data
      return true
    })
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
        const allmatchs: MyMatch[] = response.data;
        const matches: Match[] = []
        allmatchs.forEach(element => {
          matches.push(tranformer.to(element))
        });
        if (filter.stage_id != undefined) {
          return matches.filter(match => match.stage_id == filter.stage_id)
        } else if (filter.group_id != undefined) {
          return matches.filter(match => match.group_id == filter.group_id)
        } else {
          return matches.filter(match => match.round_id == filter.round_id)
        };
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
