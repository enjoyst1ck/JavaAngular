import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StuffRoutingModule } from './stuff-routing.module';
import { StuffComponent } from './stuff/stuff.component';


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
