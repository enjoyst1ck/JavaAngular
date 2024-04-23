import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { RegisterDto } from 'src/app/dtos/registerDto';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.scss']
})
export class RegisterPageComponent {
  form!: FormGroup;
  registerDto!: RegisterDto;

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

  register(form: FormGroup) {
    this.registerDto = form.value;
    if (!this.form.invalid) {
      this.registerDto = this.form.value;
      this._service.register(this.registerDto);
    }
  }

  check() {
    
  }

  registerPage() {

  }
}