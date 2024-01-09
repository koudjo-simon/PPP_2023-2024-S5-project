import {Injectable, OnInit} from '@angular/core';
import {environment} from "../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class FoodService implements OnInit{

  apiUrl = "";

  constructor() { }

  ngOnInit() {
    this.apiUrl = environment.apiUrl;
  }

}
