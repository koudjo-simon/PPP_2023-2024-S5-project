import { Component } from '@angular/core';

@Component({
  selector: 'app-command-list',
  templateUrl: './command-list.component.html',
  styleUrls: ['./command-list.component.scss']
})
export class CommandListComponent {
  searchProducts: any;
  searchKeyword: any;
  errorMessage: any;

  handleDeleteFood(product: any) {

  }

  handleEditFood(product: any) {

  }
}
