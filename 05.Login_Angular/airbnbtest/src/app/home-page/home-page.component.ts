import { Component, OnInit, Input } from '@angular/core';
import { TokenStorageService } from '../services/token-storage.service';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  private roles: string[];
  public authority: string;

  constructor(private tokenStorage: TokenStorageService, private authService: AuthService,
    public router: Router) { }

  @Input() receiveStatus: boolean;

  ngOnInit() {

    if (this.tokenStorage.getToken()) {
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        }
        this.authority = 'user';
        return true;
      });
    }
    else {
      this.roles = this.tokenStorage.getAuthoritiesSession();
      this.roles.every(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        }
        this.authority = 'user';
        return true;
      });
    }
  }
  logout() {
    if (this.tokenStorage.getToken()) {
      this.tokenStorage.signOut();
      this.router.navigate(['/homepage/auth/login'])
        .then(() => {
          window.location.reload();
        });
    } else {
      this.tokenStorage.signOut1();
      this.router.navigate(['/homepage/auth/login'])
        .then(() => {
          window.location.reload();
        });
    }
  }
}

