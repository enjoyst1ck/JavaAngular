import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, ReplaySubject, map, of } from 'rxjs';
import { EventApi } from 'src/app/api/event.api';
import { UserApi } from 'src/app/api/user.api';
import { LoginDto } from 'src/app/dtos/loginDto';
import { RegisterDto } from 'src/app/dtos/registerDto';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private _username!: string;
  private _password!: string;

  constructor(private _api: UserApi, private _router: Router) { }

  setCredentials(username: string, password: string) {
    this._username = username;
    this._password = password;
  }

  /*getAuthorizationHeader(): string {
    return 'Basic ' + btoa(this._username + ':' + this._password);
  }*/

  canActivate(): boolean {
    if (localStorage.getItem("auth")) {
      return true
    } 
    else {
      //this._router.navigate(['/login']);
      return false
    }
  }
  
  isAuthenticated(expectedRole: String) {
    const authToken = localStorage.getItem('auth');
    const role = authToken?.split(':')[2];
    if (expectedRole === role) {
      return true;
    }
    return false;
  }

  login(dto: LoginDto) {        
    this._api.login(dto).subscribe(data => {
      
        //const data = res.split(':');
        console.log(data);
        localStorage.setItem("auth", data);
        return data;
    });
  }

  /*register(dto: RegisterDto) {        
    this._http.put<string>("http://localhost:8080/account/register", dto).subscribe(
      res => {
        console.log(res);
        return res;
      },
      error => {
        console.error(error);
      }
    );
  }*/
}