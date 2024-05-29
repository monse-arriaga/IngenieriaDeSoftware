import axios from 'axios';
import StageT from '../tranformers/Stage';
import { Stage } from 'brackets-model';
import MyStage from '../types/MyStage';
import MyRoundRobinMode from '../types/MyRoundRobinMode';
import MyGrandFinalType from '../types/MyGrandFinalType';

const API_URL = 'http://localhost:8080/stage';
const tranformer = new StageT()

class StageService {

  async create(value: Stage | Stage[]){
    value = Array.isArray(value) ? value : [value];
    const valueT:MyStage[] = []

    value.forEach( stage => {
        const myStage = tranformer.from(stage);
        const settings = myStage.stageSettings;
        myStage.stageSettings =
        {
          size: settings.size != undefined ? settings.size : 0,
          balanceByes: settings.balanceByes != undefined ? settings.balanceByes :  false,
          matchesChildCount: settings.matchesChildCount != undefined ? settings.matchesChildCount : 0,
          groupCount: settings.groupCount != undefined ? settings.groupCount : 0,
          roundRobinMode: settings.roundRobinMode != undefined ? settings.roundRobinMode : MyRoundRobinMode.SIMPLE,
          consolationFinal: settings.consolationFinal != undefined ? settings.consolationFinal : false,
          skipFirstRound: settings.skipFirstRound != undefined ? settings.skipFirstRound : false,
          grandFinal: settings.grandFinal != undefined ? settings.grandFinal : MyGrandFinalType.NONE,
        }
        valueT.push(myStage)
  })
  return await axios.post(API_URL + '/create/', valueT).then(response =>{
    if (valueT.length == 1) return response.data
    return true
  })
}

  async select(filter?: number | Partial<Stage>): Promise<Stage | Stage[] | null> {
    if(filter == undefined) {
      return  await axios.get(API_URL + "/all/").then(response => {
         const data:MyStage[] = response.data; 
         const toReturn:Stage[] = []; 
         data.forEach(element => {
          toReturn.push(tranformer.to(element))
         });
         return toReturn;
      });
    } else if(typeof filter == 'number') {
      return await axios.get(API_URL + "/find/" + filter).then(response => {
        return tranformer.to(response.data[0]);
      })
    } else {
      return await axios.get(API_URL + "/all/").then(response => {
        const allStages: MyStage[] = response.data;
        const stages: Stage[] = []
        allStages.forEach(element => {
          stages.push(tranformer.to(element))
        });
        const stagesf = stages.filter(stage => 
          stage.tournament_id == filter.tournament_id);
        console.log(stagesf)
        return stagesf 
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
