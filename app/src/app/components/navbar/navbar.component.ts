import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {
  faBoxArchive,
  faBuilding,
  faFileLines,
  faGear,
  faHouse,
  faKey,
  faList,
  faRightToBracket,
  faShop,
  faStore,
  faTable,
  faTableList,
  faTags,
  faUser,
  faUsers,
} from '@fortawesome/free-solid-svg-icons';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent {
  logoutMessage: string = '';

  faBoxArchive = faBoxArchive;
  faBuilding = faBuilding;
  faFileLines = faFileLines;
  faGear = faGear;
  faHouse = faHouse;
  faKey = faKey;
  faList = faList;
  faRightToBracket = faRightToBracket;
  faShop = faShop;
  faStore = faStore;
  faTable = faTable;
  faTableList = faTableList;
  faTags = faTags;
  faUser = faUser;
  faUsers = faUsers;

  constructor(private router: Router, private authService: AuthService) {}

  isCurrentRoute(route: string): boolean {
    return route === this.router.url;
  }

  isRouteActive(route: string): boolean {
    return this.router.url.includes(route);
  }

  isAuthenticated() {
    return this.authService.isAuthenticated();
  }

  confirmLogout() {
    this.logoutMessage = `<div>¿Está seguro de que desea cerrar sesión?</div>`;
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
