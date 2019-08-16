import { Component, OnInit } from '@angular/core';
import { HouseService } from 'src/app/services/house.service';
import { Subscription } from 'rxjs';
import { House } from 'src/app/models/house.model';

@Component({
  selector: 'app-property',
  templateUrl: './property.component.html',
  styleUrls: ['./property.component.css']
})
export class PropertyComponent implements OnInit {
  sub: Subscription;
  houses: House[];
  constructor(private houseService: HouseService) { }

  ngOnInit() {
    this.sub = this.houseService.getAllHouses().subscribe((data: House[])=> {
      this.houses = data;
    })

  }

  onDestroy() {
    if (this.sub) {
      this.sub.unsubscribe();
    }
  }
}
