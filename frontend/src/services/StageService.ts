import axios from 'axios';
import StageT from '../tranformers/Stage';
import { Stage } from 'brackets-model';

const API_URL = 'http://localhost:8080/group';
const tranformer = new StageT()

class UserService {
  async create(value: Stage | Stage[]){
    
    value = Array.isArray(value) ? value : [value];

    value.forEach( async stage => {
        const myStage = tranformer.from(stage);

        await axios.post(API_URL + 'create', {
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

  async select(name: string) {
      return axios.get(API_URL + "/tournament/find/" + name)
          .then(response => {
            return response.data
          })
  }

  async update(){

  }

  async delete(){

  }


}

export default new UserService();

/*

curl -X POST http://localhost:8080/stage/create/ \
     -H "Content-Type: application/json" \
     -d '{
           "stage": {
              "tournamentId": 1,
              "name": "1",
              "type": "ROUND_ROBIN",
              "number": "1"
           },
           "settings": {
             "size": 2,
                  "balanceByes": false,
                  "matchesChildCount": 2,
                  "groupCount": 2,
                  "roundRobinMode": "SIMPLE"
           }
         }'

*/