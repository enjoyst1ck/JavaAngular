import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { ArtistApi } from 'src/app/api/artist.api';
import { ArtistDto } from 'src/app/dtos/artistDto';

@Component({
  selector: 'app-artist-list',
  templateUrl: './artist-list.component.html',
  styleUrls: ['./artist-list.component.scss']
})
export class ArtistListComponent {
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  
  private _sub?: Subscription;

  displayedColumns: string[] = ['Id', "Name", "Edit"]
  dataSource = new MatTableDataSource<ArtistDto>();

  pageSizeOptions: number[] = [1, 2, 4, 8];
  pageSize: number = 8;
  
  constructor(private api: ArtistApi) {
    
  }

  ngOnInit(): void {
    this.fetchData();
  }
  
  ngOnDestroy(): void {
    this._sub?.unsubscribe();
  }

  private fetchData() {
    this._sub = this.api.getAll().subscribe(data => {
      if(data) {
        this.dataSource = new MatTableDataSource<ArtistDto>(data);
        this.ngAfterViewInit();
      }
    })
  }
  
  private compare (a: any, b: any, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
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