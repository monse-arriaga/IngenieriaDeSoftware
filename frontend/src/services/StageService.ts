import axios from 'axios';
import StageT from '../tranformers/Stage';
import { Stage } from 'brackets-model';
import MyStage from '../types/MyStage';

const API_URL = 'http://localhost:8080/stage';
const tranformer = new StageT()

class StageService {

  async create(value: Stage | Stage[]){
    
    value = Array.isArray(value) ? value : [value];

    value.forEach( async stage => {
        const myStage = tranformer.from(stage);

        await axios.post(API_URL + '/create/', {
          stage: {
            id: myStage.id,
            tournamentId: myStage.tournamentId,
            name: myStage.name,
            type: myStage.type,
            number: myStage.number
          },
          settings: myStage.stageSettings
        })
    } )

  }

  async select(filter?: number | Partial<Stage>): Promise<Stage | Stage[] | null> {
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
        const allStages: Stage[] = response.data;
        return allStages.filter(stage => 
          stage.id == filter.tournament_id);
      });
    }
  }

  async update(filter: number | Partial<Stage>, value: Stage | Partial<Stage>){
    const obj: MyStage = tranformer.from(value as Stage);
    obj.id = filter as number;
    await axios.post(API_URL + "/edit/", obj)
  }

  async delete(filter?: Stage | Partial<Stage>){
    if (filter == undefined) return;
    if(((value: Stage): value is Stage => !!value.id)) {
      return await axios.post(API_URL + "/delete/", filter)
    } else {
      let stages = await this.select(filter);
      stages = stages == null ? [] : Array.isArray(stages) ? stages : [stages] 
      stages.forEach(element => {
        this.delete(element);
      });
    }
  }

}

export default new StageService();
