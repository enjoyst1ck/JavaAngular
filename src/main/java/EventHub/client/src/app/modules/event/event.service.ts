import { Injectable, OnDestroy, OnInit } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { EventApi } from 'src/app/api/event.api';
import { EventDto } from 'src/app/dtos/eventDto';
import { MatDialog } from '@angular/material/dialog';
import { EventFormComponent } from './event-form/event-form.component';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { ArtistApi } from 'src/app/api/artist.api';
import { StuffApi } from 'src/app/api/stuff.api';
import { venueApi } from 'src/app/api/venue.api';

@Injectable({
  providedIn: 'root'
})
export class EventService implements OnInit, OnDestroy {
  form?: FormGroup;
  private dataUpdated: BehaviorSubject<void> = new BehaviorSubject<void>(undefined);
  dataUpdated$ = this.dataUpdated.asObservable();
  
  get dataUpdate() {
    return this.dataUpdated.asObservable();
  }
  
  constructor(private _api: EventApi, 
              private _artistApi: ArtistApi,
              private _stuffApi: StuffApi,
              private _venueApi: venueApi, 
              public fb: FormBuilder, public dialog: MatDialog, public http: HttpClient) { }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    throw new Error('Method not implemented.');
  }

  getAllArtist() {
    return this._artistApi.getAll();
  }

  getAllStuff() {
    return this._stuffApi.getAll();
  }

  getAllVenues() {
    return this._venueApi.getAll();
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

  initCreateForm(isNew: boolean, dto?: EventDto) {
    this.form = this.fb.group({
      isNew: new FormControl(isNew),
      id: new FormControl(dto?.id),
      name: new FormControl(dto?.name, Validators.required),
      description: new FormControl(dto?.description, Validators.required),
      startDate: new FormControl(dto?.startDate, Validators.required),
      endDate: new FormControl(dto?.endDate, Validators.required),
      venue: new FormControl(dto?.venue),
      artists: new FormControl(dto?.artists ? dto.artists : []),
      stuff: new FormControl(dto?.stuff ? dto?.stuff : []),
      attachments: new FormControl(dto?.attachments ? dto?.attachments : []),
    });
  }

  openDialog(isNew: boolean, event?: EventDto) {
    this.initCreateForm(isNew, event);

    this.dialog.open(EventFormComponent, {
      width: '60%',
      maxWidth: '60vw',
      height: '560px',

      disableClose: true,
      data: { 
        form: this.form,
        isNew: isNew 
    }
    }).afterClosed().subscribe(result => {
      if (result && isNew) {
        this._api.insert(undefined, result).subscribe(eventList => {
          if (eventList) {
            this.dataUpdated.next();
          }
        })
      }
      else if (result && !isNew) {
        this._api.updateEvent(undefined, result).subscribe(eventList => {
          if (eventList)
            this.dataUpdated.next();
        })
      };
    });
  }
}
