import apiClient from "../utils/apiClient";
import User from "../types/User";

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