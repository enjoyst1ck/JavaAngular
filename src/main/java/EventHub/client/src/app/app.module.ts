import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EventApi } from './api/event.api';
import { EventModule } from './modules/event/event.module';
import { SharedModule } from './modules/shared/shared.module';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AuthModule } from './modules/auth/auth.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    AppRoutingModule,
    EventModule,
    SharedModule,
    MatSlideToggleModule,
    HttpClientModule,
    BrowserAnimationsModule,
    AuthModule
  ],
  providers: [
    EventApi
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
