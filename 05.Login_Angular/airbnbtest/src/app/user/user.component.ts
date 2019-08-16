import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { AuthService } from '../services/auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  board: string;
  errorMessage: string;
  newPass: string;
  oldPass: string;
  message: string;
  passWord: Array<string> = [this.newPass, this.oldPass];
  idUser: number;

  constructor(private userService: UserService,
    private reactivedRouter: ActivatedRoute, private authService: AuthService) { }

  ngOnInit() {
    this.userService.getUserBoard().subscribe(
      data => {
        this.board = data;
      },
      error => {
        this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
      }
    );
  }

  updatePassword() {
    this.reactivedRouter.params.subscribe(data => {
      const idUser = data.id;
      console.log(idUser);
      console.log(this.newPass);
      this.authService.updatePassword(idUser, this.newPass, this.oldPass).subscribe();
    });
  }
}
