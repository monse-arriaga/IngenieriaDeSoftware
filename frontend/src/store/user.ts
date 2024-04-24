import { defineStore } from 'pinia';
import User from '../types/User';
import AuthService from '../services/AuthService';

const storedUser = localStorage.getItem('user');

interface UserState {
  status: { loggedIn: boolean };
  user: User | null; 
}

export const useUserStore = defineStore({
    id: 'user',
    state: (): UserState => ({
      status: storedUser ? { loggedIn: true } : { loggedIn: false },
      user: storedUser ? JSON.parse(storedUser) : null,
    }),
    getters: {
      isLoggedIn(state): boolean {
        return state.status.loggedIn;
      },
    },
    actions: {
      loginSuccess(user: User): void {
        this.status.loggedIn = true;
        this.user = user;
      },
      loginFailure(): void {
        this.status.loggedIn = false;
        this.user = null;
      },
      logout(): void {
        this.status.loggedIn = false;
        this.user = null;
      },
      registerSuccess(): void {
        this.status.loggedIn = false;
      },
      registerFailure(): void {
        this.status.loggedIn = false;
      },
    
      async login(username: string, password: string): Promise<any> {
        try {
          const user = await AuthService.login(username, password);
          this.loginSuccess(user);
          return user;
        } catch (error) {
            this.loginFailure();
            let message: string;
            if (typeof error === "string") {
                message = error;
            } else if (error instanceof Error) {
                message = error.message;
            } else {
            message = "An unknown error occurred";
          }
          throw new Error(message);
        }
      },
      signOut(): void {
        AuthService.logout();
        this.logout();
      },
      async register(data: any): Promise<any> {
        try {
          const response = await AuthService.register(data);
          this.registerSuccess();
          return response.data;
        } catch (error) {
            this.loginFailure();
            let message: string;
            if (typeof error === "string") {
                message = error;
            } else if (error instanceof Error) {
                message = error.message;
            } else {
            message = "An unknown error occurred";
          }
          throw new Error(message);
        }
      },
      
    },
  });
  
