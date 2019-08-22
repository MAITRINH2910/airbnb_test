import { Component, OnInit, Output, EventEmitter } from '@angular/core';

import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { TokenStorageService } from '../services/token-storage.service';
import { AuthLoginInfo } from '../models/login-info';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  private loginInfo: AuthLoginInfo;
  formLogin: FormGroup
  public status = false;
  submitted = false;
  k: number;
  h: number;
  loading = false;

  constructor(
    private router: Router, private authService: AuthService,
    private tokenStorage: TokenStorageService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.createForm();

    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
    }
  }
  createForm() {
    this.formLogin = this.formBuilder.group({
      username: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9]*$')]],
      password: ['', [Validators.required]],
    });

    //^[a-zA-Z0-9]*$: validate dấu cách,, this.noWhitespaceValidator,
  }
  public noWhitespaceValidator(control: FormControl) {
    const isWhitespace = (control.value || '').trim().length === 0;
    const isValid = !isWhitespace;
    return isValid ? null : { 'whitespace': true };
  }
  keyDownAccount(event) {
    this.k = event.keyCode;
    if ((this.k === 32) || (this.k > 105 && this.k < 112)
      || (this.k >= 186 && this.k <= 192)
      || (this.k === 231) || (this.k >= 220 && this.k <= 222)
    ) {
      event.preventDefault();
    }
  }

  keyDownPassword(event) {
    this.h = event.keyCode;
    if (this.h === 32) {
      event.preventDefault();
    }
  }


  isChecked = true;

  sendStatus() {
    this.isChecked = !this.isChecked;
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.formLogin.invalid) {
      return;
    }
    this.loading = true;

    this.loginInfo = new AuthLoginInfo(
      this.formLogin.value.username,
      this.formLogin.value.password);
    // this.loginInfo.username = this.loginInfo.username.replace(/\s/g, "");: cắt dấu cách 

    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        if (this.isChecked) {
          this.tokenStorage.saveToken(data.accessToken);
          this.tokenStorage.saveUsername(data.username);
          this.tokenStorage.saveAuthorities(data.authorities);
          this.isLoginFailed = false;
          this.isLoggedIn = true;
          this.roles = this.tokenStorage.getAuthorities();
          this.reloadPage();
        } else {
          this.tokenStorage.saveTokenSession(data.accessToken);
          this.tokenStorage.saveUsernameSession(data.username);
          this.tokenStorage.saveAuthoritiesSession(data.authorities);
          this.isLoginFailed = false;
          this.isLoggedIn = true;
          this.roles = this.tokenStorage.getAuthoritiesSession();
          this.reloadPage();
        }
      },
      error => {
        this.isLoginFailed = true;
        this.loading = false;
      }
    );
  }

  reloadPage() {
    this.router.navigate(['/homepage'])
      .then(() => {
        window.location.reload();
      });
  }
}

