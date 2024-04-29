import { HttpClient } from "@angular/common/http";
import { GenericApi } from "./generic.api";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { VenueDto } from "../dtos/venueDto";
import { LoginDto } from "../dtos/loginDto";
import { RegisterDto } from "../dtos/registerDto";

@Injectable({
  providedIn: 'root'
})
export class UserApi {
  
  controllerName: string = "account";

  constructor(private _http: HttpClient) { }

  login(dto: LoginDto) {
    return this._http.put("http://localhost:8080/account/login", dto, { responseType: 'text' });
  }

  register(dto: RegisterDto) {
    return this._http.put<string>("http://localhost:8080/account/register", dto);
  }

}