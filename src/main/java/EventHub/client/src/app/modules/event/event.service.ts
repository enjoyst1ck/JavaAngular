import { Injectable, OnDestroy, OnInit } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { EventApi } from 'src/app/api/event.api';
import { EventDto } from 'src/app/dtos/eventDto';
import { MatDialog } from '@angular/material/dialog';
import { EventFormComponent } from './event-form/event-form.component';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';

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
  constructor(private _api: EventApi, public fb: FormBuilder, public dialog: MatDialog) { }

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
      startDate: new FormControl(dto?.startDate),
      endDate: new FormControl(dto?.endDate),
      description: new FormControl(dto?.description)
    });
  }

  openDialog(isNew: boolean, event?: EventDto) {
    this.initCreateForm(isNew, event);

    const dialogRef = this.dialog.open(EventFormComponent, {
      width: '580px',
      height: '500px',
      data: { 
        form: this.form,
        isNew: isNew 
    }
    }).afterClosed().subscribe(result => {     
      console.log(result);
      if (result && isNew) {
        this._api.insert(undefined, result).subscribe(event => {
          if (event) {
            if (event != null) {
              this.dataUpdated.next();
            }
            
          }})
      }
      else if (result && !isNew) {
        this._api.update(undefined, result).subscribe(events => {
        this.dataUpdated.next();
        })
      };
    });
  }
}
