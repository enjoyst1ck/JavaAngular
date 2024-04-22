import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventDto } from 'src/app/dtos/eventDto';
import { EventService } from '../event.service';

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.scss']
})
export class EventDetailsComponent implements OnInit {

  eventId?: number;
  eventDto?: EventDto;

  constructor(private _service: EventService,
              private _route: ActivatedRoute) { }
  
  ngOnInit(): void {
    this._route.params.subscribe(params => {
      this.eventId = +params['id'];
    })

    if(this.eventId) {
      this._service.getById(this.eventId).subscribe(data => 
        this.eventDto = data
      );
    }
  }

}
