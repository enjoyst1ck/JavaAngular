import { Injectable, OnDestroy, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { EventApi } from 'src/app/api/event.api';
import { EventDto } from 'src/app/dtos/eventDto';
import { MatDialog } from '@angular/material/dialog';
import { EventFormComponent } from './event-form/event-form.component';

@Injectable({
  providedIn: 'root'
})
export class EventService implements OnInit, OnDestroy {

  constructor(private _api: EventApi, public dialog: MatDialog) { }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    throw new Error('Method not implemented.');
  }

  getAllEvents(): Observable<EventDto[]> {
    return this._api.getAll();
  }

  getById(id: number): Observable<EventDto> {
    return this._api.getById(undefined, id);
  }

  deleteById(id: number): Observable<void> {
    return this._api.delete(undefined, id);
  }

  initCreateForm(event?: EventDto) {
    let isNew = true;
    if (event) {
      isNew = false;
    }

    const dialogRef = this.dialog.open(EventFormComponent, {
      width: '500px',
      height: '700px',
      data: { 
        event: event,
        isNew: isNew 
    }
    }).afterClosed().subscribe(result => {     
      console.log(result);
      if (result) {
        //this._api.insert(undefined, result);        
        console.log(result);
      }
    });
  }
}
