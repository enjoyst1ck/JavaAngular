import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, ReplaySubject, map, of } from 'rxjs';
import { EventApi } from 'src/app/api/event.api';
import { LoginDto } from 'src/app/dtos/loginDto';
import { RegisterDto } from 'src/app/dtos/registerDto';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private _username!: string;
  private _password!: string;

  constructor(private _http: HttpClient, private _eventApi: EventApi) { }

  setCredentials(username: string, password: string) {
    this._username = username;
    this._password = password;
  }

  /*getAuthorizationHeader(): string {
    return 'Basic ' + btoa(this._username + ':' + this._password);
  }*/

  login(dto: LoginDto) {        
    this._http.put<string>("http://localhost:8080/account/login", dto).subscribe(
      res => {
        console.log(res);
        return res;
      },
      error => {
        console.error(error);
      }
    );
  }

  register(dto: RegisterDto) {        
    this._http.put<string>("http://localhost:8080/account/register", dto).subscribe(
      res => {
        console.log(res);
        return res;
      },
      error => {
        console.error(error);
      }
    );
  }
}