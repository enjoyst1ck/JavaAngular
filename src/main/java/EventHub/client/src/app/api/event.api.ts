import { HttpClient, HttpHeaders } from "@angular/common/http";
import { EventDto } from "../dtos/eventDto";
import { GenericApi } from "./generic.api";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
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
    const body = {
      username: dto.username,
      password: dto.password
    }

    return this.http.put<string>("http://localhost:8080/account/login", body);
  }
  
  getFiveLast(methodName: string = "getfivelast"): Observable<EventDto[]> {
    //this.authService.setCredentials('admin', 'admin');e
    
    // const headers = new HttpHeaders({
    //   'Authorization': 'Basic admin:admin'//this.authService.getAuthorizationHeader()
    // });

    let events = super.getAll(this.controllerName, methodName);
    return events;
  }

  override getAll(methodName: string = "getall"): Observable<EventDto[]> {
    //this.authService.setCredentials('admin', 'admin');
    
    // const headers = new HttpHeaders({
    //   'Authorization': 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsb2dpbiIsImlhdCI6MTcxOTE3Njk2NSwiZXhwIjoxNzE5NzgxNzY1fQ.BO5tfekUw6j3ERBlrBBntofqnFuYNVhAwCwg8Tnur9M'//this.authService.getAuthorizationHeader()
    // });

    let events = super.getAll(this.controllerName, methodName);
    return events;
  }

  override getById(controllerName: string = this.controllerName, id: number): Observable<EventDto> {
    return super.getById(this.controllerName, id);
  }

  override insert(controllerName: string = this.controllerName, event: EventDto, headers?: HttpHeaders): Observable<EventDto[]> {
    return this.http.post<EventDto[]>("http://localhost:8080/events/addEvent", event, { headers: headers });
  }
  
  updateEvent(controllerName: string = this.controllerName, event: EventDto, headers?: HttpHeaders): Observable<boolean> {
    return this.http.put<boolean>("http://localhost:8080/events/editEvent", event, { headers: headers });
  }

  override delete(controllerName: string = this.controllerName, id: number): Observable<void> {
    return super.delete(this.controllerName, id);
  }
}