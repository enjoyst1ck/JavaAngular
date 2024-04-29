import { HttpClient, HttpHeaders } from "@angular/common/http";
import { GenericApi } from "./generic.api";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { StuffDto } from "../dtos/stuffDto";

@Injectable({
  providedIn: 'root'
})
export class StuffApi extends GenericApi<StuffDto> {
  
  controllerName: string = "stuff";

  constructor(public http: HttpClient) {
      super(http);
  }

  override getAll(methodName: string = "getall"): Observable<StuffDto[]> {

    let stuff = super.getAll(this.controllerName, methodName);
    return stuff;
  }

  override getById(controllerName: string = this.controllerName, id: number): Observable<StuffDto> {
    return super.getById(this.controllerName, id);
  }

  override insert(controllerName: string = this.controllerName, stuff: StuffDto): Observable<StuffDto[]> {
    return super.insert(this.controllerName, stuff);
  }

  override update(controllerName: string = this.controllerName, stuff: StuffDto): Observable<StuffDto[]> {
    return super.update(this.controllerName, stuff);
  }

  override delete(controllerName: string = this.controllerName, id: number): Observable<void> {
    return super.delete(this.controllerName, id);
  }
}