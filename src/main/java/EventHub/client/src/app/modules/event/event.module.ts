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
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatButtonModule } from '@angular/material/button';
import { StuffModule } from '../stuff/stuff.module';
import { MatSelectModule } from '@angular/material/select';
import { MatListModule } from '@angular/material/list';

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
    MatNativeDateModule,
    MatPaginatorModule,
    MatTableModule,
    MatIconModule,
    MatSortModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatButtonModule,
    StuffModule,
    MatSelectModule,
    MatListModule
  ]
})
export class EventModule { }
