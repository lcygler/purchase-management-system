import { Injectable } from '@angular/core';
import { User } from 'src/app/models/user/IUser';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private isLoggedIn = false;

  constructor() {
    this.loadAuthState();
  }

  login(user: User) {
    this.isLoggedIn = true;
    this.saveAuthState();
    this.saveUser(user);
  }

  logout() {
    this.isLoggedIn = false;
    this.saveAuthState();
  }

  isAuthenticated() {
    return this.isLoggedIn;
  }

  private saveAuthState() {
    localStorage.setItem('isLoggedIn', String(this.isLoggedIn));
  }

  private loadAuthState() {
    const storedAuthState = localStorage.getItem('isLoggedIn');
    this.isLoggedIn = storedAuthState ? JSON.parse(storedAuthState) : false;
  }

  private saveUser(user: User) {
    localStorage.setItem('user', JSON.stringify(user));
  }

  loadUser() {
    const storedUser = localStorage.getItem('user');
    return storedUser ? JSON.parse(storedUser) : null;
  }
}
