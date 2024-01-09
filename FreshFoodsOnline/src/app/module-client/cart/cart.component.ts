import {Component, OnInit} from '@angular/core';
import {CartService} from '../../services/cart.service';
import {Food} from 'src/app/shared/model/food';
import {CommandLine} from 'src/app/shared/model/command-line';
import {CommandService} from '../../services/command.service';
import {Product} from "../../shared/model/Product";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  public selectedFoods: Product[] = [];
  public totalPrice: number = 0;
  public commandLines: CommandLine[] = [];

  constructor(private cartService: CartService, private cmdService: CommandService) {
  }

  ngOnInit(): void {
    this.cartService.getCartCmdLinesFoods().subscribe({
      next: value => {
        this.commandLines = value;
        this.cartService.getTotalPrice().subscribe({
          next: value => {
            this.totalPrice = value;
          },
          error: err => console.log("Error")
        });
      },
      error: err => console.log(err)
    })
  }

  handleDeleteFood(c: CommandLine) {
    let conf = confirm("Are you sure to delete this food ?");
    if (conf == false) return;
    this.cartService.deleteComandeLine(c.commandLineId).subscribe({
      next: (data) => {
        let index = this.commandLines.indexOf(c);
        this.commandLines.splice(index, 1);
      }
    })
  }

  command() {

  }

}
