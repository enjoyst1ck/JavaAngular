import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginDto } from 'src/app/dtos/loginDto';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {
  form!: FormGroup;
  loginDto!: LoginDto;

  constructor(private _fb: FormBuilder, 
              private _service: AuthService) { }

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