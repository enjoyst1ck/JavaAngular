import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { EventService } from '../event.service';
import { EventDto } from 'src/app/dtos/eventDto';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.scss']
})
export class EventListComponent implements OnInit, AfterViewInit, OnDestroy {
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  
  private _sub?: Subscription;

  displayedColumns: string[] = ['Id', "Start Date", "End Date", "Description"]
  dataSource = new MatTableDataSource<EventDto>();

  pageSizeOptions: number[] = [1, 2, 4, 8];
  pageSize: number = 2;
  
  constructor(private _service: EventService) {this.fetchData();
  }

  ngOnInit(): void {
    
  }
  
  ngOnDestroy(): void {
    this._sub?.unsubscribe();
  }

  private fetchData() {
    this._service.getAllEvents().subscribe(data => {
      if(data) {
        this.dataSource = new MatTableDataSource<EventDto>(data);
        this.ngAfterViewInit();
      }
    })

    /*this._subs.push(this._service.getAllEvents().subscribe(data => {
      if(data) {
        this.dataSource = new MatTableDataSource<EventDto>(data);
        this.ngAfterViewInit();
      }
    }));*/

    /*this._subs.push(this._service.dataUpdated$.subscribe(() => {
      this.fetchData();
    }));*/

  }
  
  private compare (a: any, b: any, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

  deleteEvent(id: number) {
    this._service.deleteById(id).subscribe(() => {
      this.fetchData();
    });
  }

  openDialog(isNew: boolean, eventDto?: EventDto) {
    this._service.openDialog(isNew, eventDto);
  }

  ngAfterViewInit() {
    this.paginator.pageSizeOptions = this.pageSizeOptions;
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;  
  }

  sortData(sort: Sort) {
    const data = this.dataSource.data.slice();

    this.dataSource.data = data.sort((item1, item2) => item1.id - item2.id)
    
    this.ngAfterViewInit();
  }


}
