import axios from 'axios';
import User from '../types/User';

const API_URL = 'http://localhost:8080';

class UserService {
    enroll(user: String, tournament: String){
        return axios.post(API_URL + '/user/enroll/' + user + "/" +  tournament)}
    
    async tournaments_enrolled(id: number){
        return axios.get(API_URL + '/user/tournaments_enrolled/' + id.toString())
        .then(response => {
            return response.data
        })
    }

    async tournaments_owned(id: number):Promise<any[]>{
        return axios.get(API_URL + '/tournamentAdmin/user_tournaments/' + id).then( response =>
            {return response.data}
        )
    }
    
    async find(user_id: number){
        return axios.get(API_URL + '/user/find/' + user_id).then(response => {
            return response.data
        })
    }

    async update(user: Partial<User> | User, id: number){
        const old_user = await this.find(id)
        const new_user:User = {
            id: id,
            bio: user.bio == undefined || old_user.bio == user.bio ? old_user.bio : user.bio,
            bornDate: user.bornDate == undefined || old_user.bornDate == user.bornDate ? old_user.bornDate : user.bornDate,
            email: user.email == undefined || old_user.email == user.email ? old_user.email : user.email,
            password: user.password == undefined || old_user.password == user.password ? old_user.password : user.password,
            name: user.name == undefined || old_user.name == user.name ? old_user.name : user.name,            
        }
        return axios.post(API_URL + 'user/edit/', new_user)

    }

}

export default new UserService();
