import { HttpClient } from "@angular/common/http";
import { EventDto } from "../dtos/eventDto";
import { GenericApi } from "./generic.api";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class EventApi extends GenericApi<EventDto> {
  
  controllerName: string = "events";

  constructor(http: HttpClient) {
      super(http);
  }

  getFiveLast(methodName: string = "getfivelast"): Observable<EventDto[]> {
    let events = super.getAll(this.controllerName, methodName);
    console.log(events);
    return events;
  }

  override getAll(methodName: string = "getall"): Observable<EventDto[]> {
    let events = super.getAll(this.controllerName, methodName);
    console.log(events);
    return events;
  }

  override getById(controllerName: string = this.controllerName, id: number): Observable<EventDto> {
    return super.getById(this.controllerName, id);
  }

  override insert(controllerName: string = this.controllerName, event: EventDto): Observable<EventDto> {
    return super.insert(this.controllerName, event);
  }

  override delete(controllerName: string = this.controllerName, id: number): Observable<void> {
    return super.delete(this.controllerName, id);
  }
}