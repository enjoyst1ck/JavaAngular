import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EventRoutingModule } from './event-routing.module';
import { EventDetailsComponent } from './event-details/event-details.component';
import { EventFormComponent } from './event-form/event-form.component';
import { EventListComponent } from './event-list/event-list.component';
import { MatDialogModule } from '@angular/material/dialog';
import { SharedModule } from '../shared/shared.module';
import { MatSortModule } from '@angular/material/sort';
import { MatNativeDateModule } from '@angular/material/core';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';

@NgModule({
  declarations: [
    EventListComponent,
    EventDetailsComponent,
    EventFormComponent
  ],
  imports: [
    CommonModule,
    EventRoutingModule,
    MatDialogModule,
    SharedModule,
    MatSortModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatTableModule,
    MatIconModule
  ]
})
export class EventModule { }
