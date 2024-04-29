import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AttachmentDto } from 'src/app/dtos/attachmentDto';
import { PhotoDialogComponent } from '../../shared/photo-dialog/photo-dialog.component';
import { VenueDto } from 'src/app/dtos/venueDto';
import { StuffDto } from 'src/app/dtos/stuffDto';
import { EventService } from '../event.service';
import { ArtistDto } from 'src/app/dtos/artistDto';

@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html',
  styleUrls: ['./event-form.component.scss']
})
export class EventFormComponent implements OnInit {
  @ViewChild('picker') picker: any;

  attachments: AttachmentDto[] = [];
  stuff: StuffDto[] = []
  
  allVenues: VenueDto[] = [];
  selectedVenue?: VenueDto;
  allArtists: ArtistDto[] = [];
  selectedArtists: ArtistDto[] = [];
  allStuff: StuffDto[] = [];
  selectedStuff: StuffDto[] = [];

  selectedAttachmentToShow: AttachmentDto | null = null;

  organizers: StuffDto[] = [{id:1, name:"Bogumił", attachments: [], events: []}, {id:2, name:"Julcia", attachments: [], events: []}, {id:3, name:"Dawid", attachments: [], events: []}, {id:4, name:"Bartek", attachments: [], events: []}, {id:5, name:"Małgorzata", attachments: [], events: []}, {id:6, name:"Anna", attachments: [], events: []}];

  form: FormGroup;
  isNew: boolean;


  constructor(public dialogRef: MatDialogRef<EventFormComponent>, 
              private service: EventService,
              @Inject(MAT_DIALOG_DATA) public data: any, 
              private dialog: MatDialog) {    

    this.form = data.form;
    this.isNew = data.isNew;
    this.selectedArtists = data.form.value.artists;
    this.selectedStuff = data.form.value.stuff;
    this.selectedVenue = data.form.value.venue;
  }

  async ngOnInit() {
    await this.service.getAllArtist().subscribe(artists => {
      if (artists) {
        this.allArtists = artists;
        this.selectedArtists = this.allArtists.filter(artist => {
          return this.selectedArtists.map(selectedArtist => selectedArtist.id).includes(artist.id);
        });
      }
    });
    
    this.service.getAllStuff().subscribe(stuff => {
      if (stuff) {
        this.allStuff = stuff;
        this.selectedStuff = this.allStuff.filter(stuff => {
          return this.selectedStuff.map(selectedStuff => selectedStuff.id).includes(stuff.id);
        })
      }
    });

    this.service.getAllVenues().subscribe(venues => {
      if (venues) {
        this.allVenues = venues;
        this.selectedVenue = this.allVenues.find(venue => this.selectedVenue?.id === venue.id);
      }
    })
  }

  update() {
    if (this.form.valid) {
      const formData = this.form.value;
      formData.artists = this.selectedArtists;
      formData.stuff = this.selectedStuff;
      formData.venue = this.selectedVenue;
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
  
  showPhoto(attachment: AttachmentDto) {
    this.selectedAttachmentToShow = attachment;
  }

  openPhoto(base64: string, filename: string): void {
    this.dialog.open(PhotoDialogComponent, {
      disableClose: false,
      data: { image: "data:image/jpeg;base64," + base64, filename: filename }
    }).afterClosed();
  }

  deleteAttachment(attachment: AttachmentDto) {
    this.form.value.attachments.pop(attachment);
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
