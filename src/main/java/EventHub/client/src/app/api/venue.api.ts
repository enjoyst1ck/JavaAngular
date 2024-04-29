import { HttpClient, HttpHeaders } from "@angular/common/http";
import { GenericApi } from "./generic.api";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { VenueDto } from "../dtos/venueDto";

@Injectable({
  providedIn: 'root'
})
export class venueApi extends GenericApi<VenueDto> {
  
  controllerName: string = "venues";

  constructor(public http: HttpClient) {
      super(http);
  }

  override getAll(methodName: string = "getall"): Observable<VenueDto[]> {

    let venue = super.getAll(this.controllerName, methodName);
    return venue;
  }

  override getById(controllerName: string = this.controllerName, id: number): Observable<VenueDto> {
    return super.getById(this.controllerName, id);
  }

  override insert(controllerName: string = this.controllerName, venue: VenueDto): Observable<VenueDto[]> {
    return super.insert(this.controllerName, venue);
  }

  override update(controllerName: string = this.controllerName, venue: VenueDto): Observable<VenueDto[]> {
    return super.update(this.controllerName, venue);
  }

  override delete(controllerName: string = this.controllerName, id: number): Observable<void> {
    return super.delete(this.controllerName, id);
  }
}