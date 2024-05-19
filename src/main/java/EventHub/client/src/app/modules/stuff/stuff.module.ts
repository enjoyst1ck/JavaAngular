import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StuffRoutingModule } from './staff-routing.module';
import { StuffComponent } from './staff/staff.component';


@NgModule({
  declarations: [
    StuffComponent
  ],
  imports: [
    CommonModule,
    StuffRoutingModule
  ]
})
export class StuffModule { }
