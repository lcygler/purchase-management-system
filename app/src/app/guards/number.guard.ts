import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class NumberGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const params = route.paramMap;

    for (const key of params.keys) {
      const value = params.get(key);

      if (!isNaN(Number(value))) {
        continue;
      } else {
        this.router.navigate(['/not-found']);
        return false;
      }
    }

    return true;
  }
}
