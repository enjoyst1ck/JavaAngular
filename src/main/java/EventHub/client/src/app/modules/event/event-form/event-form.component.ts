import { Component, Inject, NgZone, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AttachmentDto } from 'src/app/dtos/attachmentDto';
import { PhotoDialogComponent } from '../../shared/photo-dialog/photo-dialog.component';
import { VenueDto } from 'src/app/dtos/venueDto';
import { ArtistDto } from 'src/app/dtos/artistDto';

@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html',
  styleUrls: ['./event-form.component.scss']
})
export class EventFormComponent {
  @ViewChild('picker') picker: any;

  attachments: AttachmentDto[] = [];
  venue?: VenueDto;
  artists?: ArtistDto;

  form: FormGroup;
  isNew: boolean;

  constructor(public dialogRef: MatDialogRef<EventFormComponent>, 
    @Inject(MAT_DIALOG_DATA) public data: any, 
    private dialog: MatDialog) {    
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
      formData.attachments = null;
      formData.id = 15;
      formData.venue = null;
      formData.artists = null;


      this.dialogRef.close(formData);
    }
  }

  onFileChange(event: any) {
    const files = event.target.files;
    for (let i = 0; i < files.length; i++) {
      const file = files[i];
      const reader = new FileReader();

      reader.onloadend = () => {
        const base64 = reader.result as string;
        const photo: AttachmentDto = new AttachmentDto();
        photo.id = i + 1; // Ustawiasz tu swoje wartości
        photo.fileName = files[i].name; // Ustawiasz tu swoje wartości
        photo.image = base64;
        this.attachments.push(photo);
      };

    reader.readAsDataURL(file);
  }
  }
  
  openPhoto(base64: string, filename: string): void {
    console.log(base64);
    const dialogRef = this.dialog.open(PhotoDialogComponent, {
      data: { image: base64, filename: filename }
    });
  }

  clear() {
    const input = document.getElementById('formFileMultiple') as HTMLInputElement;
    input.value = '';
    this.attachments = [];
  }

  close() {
    this.dialogRef.close();
  }
}
