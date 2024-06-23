import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AttachmentDto } from 'src/app/dtos/attachmentDto';
import { PhotoDialogComponent } from '../../shared/photo-dialog/photo-dialog.component';
import { VenueDto } from 'src/app/dtos/venueDto';
import { StaffDto } from 'src/app/dtos/staffDto';
import { EventService } from '../event.service';
import { ArtistDto } from 'src/app/dtos/artistDto';
import {ErrorStateMatcher} from '@angular/material/core';

@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html',
  styleUrls: ['./event-form.component.scss']
})
export class EventFormComponent implements OnInit {
  @ViewChild('picker') picker: any;

  attachments: AttachmentDto[] = [];
  staff: StaffDto[] = []

  allVenues: VenueDto[] = [];
  selectedVenue?: VenueDto;
  allArtists: ArtistDto[] = [];
  selectedArtists: ArtistDto[] = [];
  allStaff: StaffDto[] = [];
  selectedStaff: StaffDto[] = [];

  selectedAttachmentToShow: AttachmentDto | null = null;

  organizers: StaffDto[] = [{id:1, name:"Bogumił", attachments: [], events: []}, {id:2, name:"Julcia", attachments: [], events: []}, {id:3, name:"Dawid", attachments: [], events: []}, {id:4, name:"Bartek", attachments: [], events: []}, {id:5, name:"Małgorzata", attachments: [], events: []}, {id:6, name:"Anna", attachments: [], events: []}];

  form: FormGroup;
  isNew: boolean;
  matcher = new ErrorStateMatcher();

  constructor(public dialogRef: MatDialogRef<EventFormComponent>,
              private service: EventService,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private dialog: MatDialog) {

    this.form = data.form;
    this.isNew = data.isNew;
    this.selectedArtists = data.form.value.artists;
    this.selectedStaff = data.form.value.staff;
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

    this.service.getAllStaff().subscribe((staff : any) => {
      if (staff) {
        this.allStaff = staff;
        this.selectedStaff = this.allStaff.filter(staff => {
          return this.selectedStaff.map(selectedStaff => selectedStaff.id).includes(staff.id);
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
      formData.staff = this.selectedStaff;
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
    const index = this.form.value.attachments.indexOf(attachment);
    if (index !== -1) {
        this.form.value.attachments.splice(index, 1);
    }
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
