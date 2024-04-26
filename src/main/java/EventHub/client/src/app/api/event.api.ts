import { HttpClient, HttpHeaders } from "@angular/common/http";
import { EventDto } from "../dtos/eventDto";
import { GenericApi } from "./generic.api";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { AuthService } from "../modules/auth/auth.service";
import { LoginDto } from "../dtos/loginDto";

@Injectable({
  providedIn: 'root'
})
export class EventApi extends GenericApi<EventDto> {
  
  controllerName: string = "events";

  constructor(public http: HttpClient) {
      super(http);
  }

  chwilowe(dto: LoginDto): Observable<string> {
    console.log(dto);

    const body = {
      username: dto.username,
      password: dto.password
    }

    return this.http.put<string>("http://localhost:8080/account/login", body);
  }
  
  getFiveLast(methodName: string = "getfivelast"): Observable<EventDto[]> {
    //this.authService.setCredentials('admin', 'admin');e
    
    const headers = new HttpHeaders({
      'Authorization': 'Basic admin:admin'//this.authService.getAuthorizationHeader()
    });

    let events = super.getAll(this.controllerName, methodName, headers);
    return events;
  }

  override getAll(methodName: string = "getall"): Observable<EventDto[]> {
    //this.authService.setCredentials('admin', 'admin');
    
    const headers = new HttpHeaders({
      'Authorization': 'Basic admin:admin'//this.authService.getAuthorizationHeader()
    });

    let events = super.getAll(this.controllerName, methodName, headers);
    return events;
  }

  override getById(controllerName: string = this.controllerName, id: number): Observable<EventDto> {
    return super.getById(this.controllerName, id);
  }

  override insert(controllerName: string = this.controllerName, event: EventDto, headers?: HttpHeaders): Observable<EventDto[]> {
    return this.http.post<EventDto[]>("http://localhost:8080/events/addEvent", { body: event, headers: headers });
  }

  override update(controllerName: string = this.controllerName, event: EventDto, headers?: HttpHeaders): Observable<EventDto[]> {
    console.log("to dto jest wysy;ane")
    console.log(event)
    return this.http.post<EventDto[]>("http://localhost:8080/events/editEvent", { body: event, headers: headers });
    //return super.update(this.controllerName, event);
  }

  override delete(controllerName: string = this.controllerName, id: number): Observable<void> {
    return super.delete(this.controllerName, id);
  }
}