import {Component, OnInit} from '@angular/core';
import {environment} from "../environments/environment";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'FreshFoodsOnline';
  apiUrl = environment.apiUrl;

  ngOnInit() {
    console.log("API URL => ", this.apiUrl);
  }

}