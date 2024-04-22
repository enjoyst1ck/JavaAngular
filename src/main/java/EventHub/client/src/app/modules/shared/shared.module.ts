import { NgModule } from '@angular/core';
import { HomePageComponent } from './home-page/home-page.component';
import { NavigationComponent } from './navigation/navigation.component';
import { RouterModule } from '@angular/router';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { NgxMatDatetimePickerModule } from '@angular-material-components/datetime-picker';

@NgModule({
  declarations: [
    HomePageComponent,
    NavigationComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatSlideToggleModule,
    MatPaginatorModule,
    MatTableModule,
    MatIconModule,
  ],
  exports: [
    HomePageComponent,
    NavigationComponent,
  ]
})
export class SharedModule { }
