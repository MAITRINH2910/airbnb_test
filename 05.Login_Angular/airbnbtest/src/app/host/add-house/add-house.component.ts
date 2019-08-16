import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { House } from 'src/app/models/house.model';
import { HouseService } from 'src/app/services/house.service';
import { AuthService } from 'src/app/services/auth.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import * as jwt_decode from 'jwt-decode'

@Component({
  selector: 'app-add-house',
  templateUrl: './add-house.component.html',
  styleUrls: ['./add-house.component.css']
})
export class AddHouseComponent implements OnInit {
  sub: Subscription;
  submitted = false;
  loading = false;
  formAddHouse: FormGroup;
  house: House

  constructor(private authService: AuthService
    , private houseService: HouseService,
    private routerService: Router,
    private formBuilder: FormBuilder, private activatedRoute: ActivatedRoute, private token: TokenStorageService
  ) { }

  public noWhitespaceValidator(control: FormControl) {
    const isWhitespace = (control.value || '').trim().length === 0;
    const isValid = !isWhitespace;
    return isValid ? null : { 'whitespace': true };
  }

  ngOnInit() {
    this.house = new House();
    this.createForm();
  }
  createForm() {
    this.formAddHouse = this.formBuilder.group({
      title: ['', [Validators.required, this.noWhitespaceValidator]],
      description: [''],
      typeHouse: ['', [Validators.required]],
      typeRoom: ['', [Validators.required]],
      address: ['', [Validators.required, this.noWhitespaceValidator]],
      bedRoomNumber: ['', [Validators.required, Validators.pattern('^[1-9]{1}[0-9]*')]],
      bathRoomNumber: ['', [Validators.required, Validators.pattern('^[1-9]{1}[0-9]*')]],
      pricePerNight: ['', [Validators.required, Validators.pattern('^[1-9]{1}[0-9]{5,11}')]],
      pricePerMonth: ['', [Validators.required, Validators.pattern('^[1-9]{1}[0-9]{5,11}')]],
    });
  }
  onSubmit() {
    this.submitted = true;
    this.loading = false;
    // if (this.formAddHouse) {
    //   this.loading = false;
    //   return;
    // }
    this.authService.getUser().subscribe(
      user =>{
        this.house.owner = user;
        this.house.title = this.formAddHouse.value.title;
        this.house.description = this.formAddHouse.value.description;
        this.house.typeHouse = this.formAddHouse.value.typeHouse;
        this.house.typeRoom = this.formAddHouse.value.typeRoom;
        this.house.address = this.formAddHouse.value.address;
        this.house.bedRoomNumber = this.formAddHouse.value.bedRoomNumber;
        this.house.bathRoomNumber = this.formAddHouse.value.bathRoomNumber;
        this.house.pricePerNight = this.formAddHouse.value.pricePerNight;
        this.house.pricePerMonth = this.formAddHouse.value.pricePerMonth;
        this.sub = this.houseService.addHouse(this.house).subscribe(data => {
          if (data && data.id) {
            this.routerService.navigate(['host/property']);
          }
        })
      }
    )

    console.log(jwt_decode(this.token.getToken()));


  }

  onDestroy() {
    if (this.sub) {
      this.sub.unsubscribe();
    }
  }

}
