import { inject } from '@angular/core';
import { AuthService } from './auth.service';
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

export const AuthGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot): Observable<boolean | UrlTree> 
    | Promise<boolean | UrlTree> 
    | boolean 
    | UrlTree => {

  const expectedRole = route.data['expectedRole'];

  if (!inject(AuthService).isAuthenticated(expectedRole)) {
    return inject(Router).createUrlTree(['/login'])
  }

  
  //const currentUserRole = inject(AuthService).getUserRole(); 

  console.log("expectedRole")
  console.log(expectedRole)
  return true;

};