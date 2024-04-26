import { Component, Inject, NgZone, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AttachmentDto } from 'src/app/dtos/attachmentDto';
import { PhotoDialogComponent } from '../../shared/photo-dialog/photo-dialog.component';
import { VenueDto } from 'src/app/dtos/venueDto';
import { ArtistDto } from 'src/app/dtos/artistDto';
import { StuffDto } from 'src/app/dtos/stuffDto';

@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html',
  styleUrls: ['./event-form.component.scss']
})
export class EventFormComponent {
  @ViewChild('picker') picker: any;

  attachments: AttachmentDto[] = [];
  stuff: StuffDto[] = []
  venue?: VenueDto;
  artists: ArtistDto[] = [];


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
      formData.venue = null;
      formData.artists = this.artists;
      formData.stuff = this.stuff;


      console.log("formData")
      console.log(formData);

      this.dialogRef.close(formData);
    }
  }

  onFileChange(event: any) {
    const files = event.target.files;
    for (let i = 0; i < files.length; i++) {
      const file = files[i];
      const reader = new FileReader();
  
      reader.onloadend = () => {
        const base64DataUrl = reader.result as string;
        let base64: string;
  
        if (base64DataUrl.startsWith('data:image/')) {
          base64 = base64DataUrl.split(',')[1];
        } else {
          console.error(`It's not base64 format`);
          return;
        }
  
        const photo: AttachmentDto = new AttachmentDto();
        photo.fileName = file.name;
        photo.image = base64;
  
        this.form.value.attachments.push(photo);
        //WARNING
        //tutaj obsluzyc zeby mozna usuwac zdjecia z tej tablicy!!!
        
        //this.attachments.push(photo);
      };
  
      reader.readAsDataURL(file);
    }
  }
  
  openPhoto(base64: string, filename: string): void {
    this.dialog.open(PhotoDialogComponent, {
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
