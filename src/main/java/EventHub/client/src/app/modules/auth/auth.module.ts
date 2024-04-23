import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { RegisterPageComponent } from './register-page/register-page.component';
import { LoginPageComponent, MyErrorStateMatcher } from './login-page/login-page.component';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    RegisterPageComponent,
    LoginPageComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    MatInputModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    MyErrorStateMatcher
  ],
  exports: [
  ]
})
export class AuthModule { }
