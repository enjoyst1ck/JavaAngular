import { Injectable, OnDestroy, OnInit } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { EventApi } from 'src/app/api/event.api';
import { EventDto } from 'src/app/dtos/eventDto';
import { MatDialog } from '@angular/material/dialog';
import { EventFormComponent } from './event-form/event-form.component';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EventService implements OnInit, OnDestroy {
  form?: FormGroup;
  private dataUpdated: BehaviorSubject<void> = new BehaviorSubject<void>(undefined);
  dataUpdated$ = this.dataUpdated.asObservable();


  dataUpdatedSignal() {
    return this.dataUpdated$;
  }
  constructor(private _api: EventApi, public fb: FormBuilder, public dialog: MatDialog, public http: HttpClient) { }

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

  initCreateForm(isNew: boolean, dto?: EventDto) {
    this.form = this.fb.group({
      isNew: new FormControl(isNew),
      id: new FormControl(dto?.id),
      name: new FormControl(dto?.name),
      description: new FormControl(dto?.description),
      startDate: new FormControl(dto?.startDate),
      endDate: new FormControl(dto?.endDate),
      attachments: new FormControl(dto?.attachments),
      stuff: new FormControl(dto?.stuff)
    });
  }

  openDialog(isNew: boolean, event?: EventDto) {
    this.initCreateForm(isNew, event);

    const dialogRef = this.dialog.open(EventFormComponent, {
      width: '580px',
      height: '525px',
      data: { 
        form: this.form,
        isNew: isNew 
    }
    }).afterClosed().subscribe(result => {     
      console.log(result);
      if (result && isNew) {
        //something not working
        /*this._api.insert(undefined, result).subscribe(newEvent => {
          if (newEvent) {
            console.log("udalo sie dodac obiekt")
          }
        })*/

        this.http.post<EventDto[]>("http://localhost:8080/events/addEvent", result).subscribe(eventList => {
          if (eventList) {
            console.log("udalo sie dodac obiekt")
              this.dataUpdated.next();
          }
        })
      }
      else if (result && !isNew) {
        this._api.update(undefined, result).subscribe(events => {
          console.log("udalo sie zaaktualizowac obiekt")
        this.dataUpdated.next();
        })
      };
    });
  }
}
