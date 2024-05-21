import axios from 'axios';
import MatchGameT from '../tranformers/MatchGame';
import { MatchGame } from 'brackets-model';

const API_URL = 'http://localhost:8080/matchgame';
const tranformer = new MatchGameT()

class matchgameService {

  async create(value: MatchGame | MatchGame[]){
    
    value = Array.isArray(value) ? value : [value];

    value.forEach( async matchgame => {
        const mymatchgame = tranformer.from(matchgame);

        await axios.post(API_URL + '/create/', {
          matchgame: {
            id: mymatchgame.id,
            matchStatus: mymatchgame.matchStatus,
            stage: mymatchgame.stage,
            group: mymatchgame.match,
            number: mymatchgame.number
          },
          result1: mymatchgame.opponentOneResult,
          result2: mymatchgame.opponentTwoResult
        })
    } )

  }

  async select(filter?: number | Partial<MatchGame>): Promise<MatchGame | MatchGame[] | null> {
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
        const allmatchgames: MatchGame[] = response.data;
        return allmatchgames.filter(matchgame => 
          Object.keys(filter).every(key => filter[key as keyof MatchGame] === matchgame[key as keyof MatchGame])
        );
      });
    }
  }

  async update(filter: number | Partial<MatchGame>, value: MatchGame | Partial<MatchGame>){ 
    if (typeof filter == "number" && ((value: MatchGame): value is MatchGame => !!value.id)) {
      await axios.post(API_URL + "/edit/", tranformer.from(value as MatchGame))
    } else {
      const matchgames = await this.select(filter);
      const matchgamesArray = matchgames == null ? [] : Array.isArray(matchgames) ? matchgames : [matchgames];

      for (const matchgame of matchgamesArray) {
        const updatedmatchgame = { ...matchgame, ...value };
        await axios.post(API_URL + "/edit/", tranformer.from(updatedmatchgame));
      }
    }
  }

  async delete(filter?: MatchGame | Partial<MatchGame>){
    if (filter == undefined) return;
    if(((value: MatchGame): value is MatchGame => !!value.id)) {
      return await axios.post(API_URL + "/delete/", filter)
    } else {
      let matchgames = await this.select(filter);
      matchgames = matchgames == null ? [] : Array.isArray(matchgames) ? matchgames : [matchgames] 
      matchgames.forEach(element => {
        this.delete(element);
      });
    }
  }

}

export default new matchgameService();
