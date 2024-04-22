import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { EventDto } from 'src/app/dtos/eventDto';

@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html',
  styleUrls: ['./event-form.component.scss']
})
export class EventFormComponent {
  
  event: EventDto;
  isNew: boolean;

  constructor(public dialogRef: MatDialogRef<EventFormComponent>, @Inject(MAT_DIALOG_DATA) public data: any) {    
    this.event = data.event;
    this.isNew = data.isNew;
  }
  
  update(event: EventDto) {
    this.dialogRef.close(event);
  }

  close() {
    this.dialogRef.close();
  }
}
