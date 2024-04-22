import { Component, Inject, NgZone, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html',
  styleUrls: ['./event-form.component.scss']
})
export class EventFormComponent {
  @ViewChild('picker') picker: any;

  form: FormGroup;
  isNew: boolean;

  constructor(public dialogRef: MatDialogRef<EventFormComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private zone: NgZone) {    
    this.form = data.form;
    this.isNew = data.isNew;
  }
  
  przyciskDoSprawdzania() {
    const formData = this.form.value;
    console.log('formdata:', formData);
  }

  update() {
    if (this.form.valid) {
      const formData = this.form.value;
      this.dialogRef.close(formData);
    }
  }

  close() {
    this.dialogRef.close();
  }
}
