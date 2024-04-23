import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { LoginDto } from 'src/app/dtos/loginDto';
import { ErrorStateMatcher}  from '@angular/material/core';
import { AuthService } from '../auth.service';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}
@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {
  form!: FormGroup;
  loginDto!: LoginDto;

  constructor(private _fb: FormBuilder, 
              private _service: AuthService,
              public matcher: MyErrorStateMatcher) { }

  ngOnInit() {
    this.initForm();
  }

  private initForm() {
    this.form = this._fb.group({
      username: new FormControl('', [ Validators.required ]),
      password: new FormControl('', [ Validators.required ]),
    });
  }

  login(form: FormGroup) {
    this.loginDto = form.value;
    if (!this.form.invalid) {
      this.loginDto = this.form.value;
      this._service.login(this.loginDto);
    }
  }

  check() {
    
  }

  registerPage() {

  }
}
