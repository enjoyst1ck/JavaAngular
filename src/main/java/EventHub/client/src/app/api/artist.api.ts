import { HttpClient, HttpHeaders } from "@angular/common/http";
import { GenericApi } from "./generic.api";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { ArtistDto } from "../dtos/artistDto";

@Injectable({
  providedIn: 'root'
})
export class ArtistApi extends GenericApi<ArtistDto> {
  
  controllerName: string = "artists";

  constructor(public http: HttpClient) {
      super(http);
  }

  override getAll(methodName: string = "getall"): Observable<ArtistDto[]> {
    //this.authService.setCredentials('admin', 'admin');
    
    const headers = new HttpHeaders({
      'Authorization': 'Basic admin:admin'//this.authService.getAuthorizationHeader()
    });

    let artists = super.getAll(this.controllerName, methodName, headers);
    return artists;
  }

  override getById(controllerName: string = this.controllerName, id: number): Observable<ArtistDto> {
    return super.getById(this.controllerName, id);
  }

  override insert(controllerName: string = this.controllerName, artist: ArtistDto, headers?: HttpHeaders): Observable<ArtistDto[]> {
    return super.insert(this.controllerName, artist, headers);
  }

  override update(controllerName: string = this.controllerName, artist: ArtistDto, headers?: HttpHeaders): Observable<ArtistDto[]> {
    return super.update(this.controllerName, artist, headers);
  }

  override delete(controllerName: string = this.controllerName, id: number): Observable<void> {
    return super.delete(this.controllerName, id);
  }
}