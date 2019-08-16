import { Component, OnInit,Input } from '@angular/core';
import { TokenStorageService } from '../services/token-storage.service';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  info: any;

  constructor(private token: TokenStorageService, public router: Router, private authService: AuthService) { }

  ngOnInit() {
    if (this.token.getToken()) {
      this.info = {
        token: this.token.getToken(),
        username: this.token.getUsername(),
        authorities: this.token.getAuthorities()
      };
    } else {
      this.info = {
        token: this.token.getTokenSession(),
        username: this.token.getUsernameSession(),
        authorities: this.token.getAuthoritiesSession()
      };
    }
  }

  logout() {
    if (this.token.getToken()) {
      this.token.signOut();
      this.router.navigate(['/homepage/auth/login'])
        .then(() => {
          window.location.reload();
        });
    } else {
      this.token.signOut1();
      this.router.navigate(['/homepage/auth/login'])
        .then(() => {
          window.location.reload();
        });
    }
  }

}
