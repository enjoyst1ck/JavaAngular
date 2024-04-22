import { Component } from '@angular/core';
import { SharedService } from '../shared.service';
import { EventDto } from 'src/app/dtos/eventDto';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent {
  public events: EventDto[] = [];

  constructor(private _service: SharedService) {
  }

  ngOnInit(): void {
    this.fetchData();
  }
  
  private fetchData() {
    this._service.getFiveLastEvents().subscribe(data => {
      this.events = data;
      console.log(this.events)
    });
  }

  klikniecie() {
    this.fetchData();
    console.log(this.events);
  }

  range(start: number, end: number) {
    
  }
}
