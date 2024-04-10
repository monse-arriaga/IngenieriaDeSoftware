import apiClient from "../utils/apiClient";
import User from "../types/User";
import authHeader from "./auth-header";

const API_URL = 'http://localhost:8080/'

class UserService {

    getUser = (password: string, email: string): Promise<User> => {
        return apiClient.post("/user/login/", {
            password: password,
            email: email
        }).then((response) => response.data);
    };

    save = (emp: User): Promise<User> => {
        return apiClient.post("/user/create/", emp).then((response) => response.data);
    };
}

export default new UserService();