import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { EventService } from '../event.service';
import { EventDto } from 'src/app/dtos/eventDto';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { LiveAnnouncer } from '@angular/cdk/a11y';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.scss']
})
export class EventListComponent implements OnInit, AfterViewInit {
  events: EventDto[] = [];
  displayedColumns: string[] = ['Id', "Start Date", "End Date", "Description"]
  dataSource = new MatTableDataSource<EventDto>([]);
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatPaginator) paginator!: MatPaginator;


  pageSizeOptions: number[] = [1, 2, 4, 8];
  pageSize: number = 2;
  constructor(private _service: EventService, private _liveAnnouncer: LiveAnnouncer) {
  }

  ngOnInit(): void {
    this.fetchData();
  }
  
  private fetchData() {
    this._service.getAllEvents().subscribe(data => {
      if(data) {
        this.dataSource.data = data;
      }
    });
  }

  update(dto?: EventDto) {
    this._service.initCreateForm(dto);
  }

  deleteEvent(id: number) {
    this._service.deleteById(id).subscribe(() => {
      this.fetchData();
    });
  }

  initForm(eventDto?: EventDto) {
    this._service.initCreateForm(eventDto);
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
    this.paginator.pageSizeOptions = this.pageSizeOptions;
    this.dataSource.paginator = this.paginator;
    
  }

  announceSortChange(sortState: Sort) {
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }
}
