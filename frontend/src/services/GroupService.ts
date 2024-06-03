import axios from 'axios';
import { Group } from 'brackets-model';
import GroupT from '../tranformers/Group';
import MyGroup from '../types/MyGroup';

const API_URL = 'http://localhost:8080/group';
const tranformer = new GroupT;

class GroupService {
  async create(value: Group | Group[]){
    value = Array.isArray(value) ? value : [value]
    const valueT:MyGroup[] = []
    value.forEach(element => {
      valueT.push(tranformer.from(element))
    });
    return await axios.post(API_URL + '/create/', valueT).then( response =>{
      if (valueT.length == 1) return response.data
      return true
    }
    )
  }

  async select(filter?: number | Partial<Group>): Promise<Group | Group[] | null> {
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
        const allGroups: MyGroup[] = response.data;
        const groups: Group[] = []
        allGroups.forEach(element => {
          groups.push(tranformer.to(element))
        });
        return groups.filter(group => 
          group.stage_id == filter.stage_id
        );
      });
    }
  }

  async update(filter: number | Partial<Group>, value: Group | Partial<Group>){ 
    if (typeof filter == "number" && ((value: Group): value is Group => !!value.id)) {
      await axios.post(API_URL + "/edit/", tranformer.from(value as Group))
    } else {
      const groups = await this.select(filter);
      const groupsArray = groups == null ? [] : Array.isArray(groups) ? groups : [groups];

      for (const group of groupsArray) {
        const updatedgroup = { ...group, ...value };
        await axios.post(API_URL + "/edit/", tranformer.from(updatedgroup));
      }
    }
  }

  async delete(filter?: Group | Partial<Group>){
    if (filter == undefined) return;
    if(((value: Group): value is Group => !!value.id)) {
      return await axios.post(API_URL + "/delete/", filter)
    } else {
      let groups = await this.select(filter);
      groups = groups == null ? [] : Array.isArray(groups) ? groups : [groups] 
      groups.forEach(element => {
        this.delete(element);
      });
    }
  }
}

export default new GroupService();
