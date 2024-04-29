import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-photo-dialog',
  templateUrl: './photo-dialog.component.html',
  styleUrls: ['./photo-dialog.component.scss']
})
export class PhotoDialogComponent {
  byte64!: string;
  file!: string;

  constructor(public dialogRef: MatDialogRef<PhotoDialogComponent>, 
    @Inject(MAT_DIALOG_DATA) public data: { image: string, filename: string }) {    
    this.byte64 = data.image;
    this.file = data.filename;
  }

  close() {
    this.dialogRef.close();
  }
}
