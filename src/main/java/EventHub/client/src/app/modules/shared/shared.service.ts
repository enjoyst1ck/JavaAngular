import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EventDto } from 'src/app/dtos/eventDto';
import { EventApi } from 'src/app/api/event.api';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  constructor(private _api: EventApi) { }

  getFiveLastEvents(): Observable<EventDto[]> {
    return this._api.getFiveLast()
  }
}
